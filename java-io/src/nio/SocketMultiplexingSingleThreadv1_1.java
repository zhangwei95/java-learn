package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: 单线程 多路复用器
 * @Author: zhangwei
 * @Date: 2020/7/12 21:41
 */
public class SocketMultiplexingSingleThreadv1_1 {

    private static final Integer TIME_OUT = 500;

    ServerSocketChannel server = null;

    Selector selector = null;

    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(9090));

            selector = Selector.open();

            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        initServer();
        System.out.println("server started...");

        while (true) {
            Set<SelectionKey> keys = selector.keys();

            try {
                // TIME > 0  等待 TIME_OUT毫秒后返回
                // TIME_OUT = 0 无限期 阻塞
                // epoll_wait(8,[],4096,500)
                while ((selector.select(TIME_OUT) > 0)) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        // 从selectionKeys中删除 否则会重复循环执行
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            // key.cancel();
                            readHandle(key);
                        } else if (key.isWritable()) {
                            // 只要发送队列是空  send-queue 一直会有写事件
                            // 写处理并不是由selector控制，而是由业务控制
                            // 1，你准备好要写什么了，这是第一步
                            // 2，第二步你才关心send-queue是否有空间
                            // 3，so，读 read 一开始就要注册，但是write依赖以上关系，什么时候用什么时候注册
                            // 4，如果一开始就注册了write的事件，进入死循环，一直调起！！！
                            // key.cancel();
                            writeHandle(key);
                        }

                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void writeHandle(SelectionKey key) {
        System.out.println("write Handle call...");
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.flip();

        while (buffer.hasRemaining()){
            try {
                client.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        buffer.clear();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readHandle(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer =(ByteBuffer) key.attachment();
        buffer.clear();
        int read = 0;
        try {
            while (true){
                read = client.read(buffer);
                if (read > 0){
                    client.register(key.selector(),SelectionKey.OP_WRITE,buffer);
                    //关心  OP_WRITE 其实就是关系send-queue是不是有空间
                } else if (read == 0){
                    break;
                } else {
                    client.close();
                    break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }


    }

    private void acceptHandler(SelectionKey key) {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        try {
            SocketChannel client = channel.accept();
            client.configureBlocking(false);

            ByteBuffer buffer = ByteBuffer.allocate(8192);
            client.register(selector, SelectionKey.OP_READ, buffer);

            System.out.println("---------------------------------------------");
            System.out.println("| 新客户端："+client.getRemoteAddress());
            System.out.println("---------------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        SocketMultiplexingSingleThreadv1_1 server = new SocketMultiplexingSingleThreadv1_1();
        server.start();
    }

}
