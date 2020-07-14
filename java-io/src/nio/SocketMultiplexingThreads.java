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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 多线程
 * @Author: zhangwei
 * @Date: 2020/7/13 14:29
 */
public class SocketMultiplexingThreads {

    private ServerSocketChannel server = null;


    private Selector selector1 = null;

    private Selector selector2 = null;

    private Selector selector3 = null;

    private void initServer() {

        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(9090));

            selector1 = Selector.open();
            selector2 = Selector.open();
            selector3 = Selector.open();

            // select1 处理接收key
            server.register(selector1, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SocketMultiplexingThreads server = new SocketMultiplexingThreads();
        server.initServer();
        NIOThread t1 = new NIOThread(server.selector1, 2);
        NIOThread t2 = new NIOThread(server.selector2);
        NIOThread t3 = new NIOThread(server.selector3);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t1.start();
        t2.start();
        t3.start();
        System.out.println("服务器启动了。。。。。");

        while (true) ;
    }

    static class NIOThread extends Thread {
        private static Integer TIME_OUT = 500;

        Selector selector = null;

        static int selectors = 0;

        int id = 0;

        volatile static LinkedBlockingQueue<SocketChannel>[] queue;

        static AtomicInteger idx = new AtomicInteger();

        NIOThread(Selector selector, int selectorNumber) {
            this.selector = selector;
            selectors = selectorNumber;
            queue = new LinkedBlockingQueue[selectors];
            for (int i = 0; i < selectorNumber; i++) {
                queue[i] = new LinkedBlockingQueue<>();
            }
            System.out.println("Boss 启动");
        }

        NIOThread(Selector selector) {
            this.selector = selector;
            id = idx.getAndIncrement() % selectors;
            System.out.println("worker: " + id + " 启动");
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // TIME > 0  等待 TIME_OUT毫秒后返回
                    // TIME_OUT = 0 无限期 阻塞
                    // epoll_wait(8,[],4096,500)
                    while ((selector.select(10) > 0)) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            // 从selectionKeys中删除 否则会重复循环执行
                            iterator.remove();
                            if (key.isAcceptable()) {
                                acceptHandler(key);
                            } else if (key.isReadable()) {
                                readHandle(key);
                            }

                        }
                    }
                    // 这里boss 也会处理read
                    if (!queue[id].isEmpty()) {
                        ByteBuffer buffer = ByteBuffer.allocate(8192);
                        SocketChannel client = (SocketChannel) queue[id].take();
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println("current Thread :" + currentThread().getName());
                        System.out.println("-------------------------------------------");
                        System.out.println("新客户端：" + client.socket().getPort() + "分配到：" + (id));
                        System.out.println("-------------------------------------------");
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }


        private void acceptHandler(SelectionKey key) {
            try {
                System.out.println("CurrentThread:" + currentThread().getName() + "    accept");
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel client = channel.accept();
                client.configureBlocking(false);
                int num = idx.getAndIncrement() % selectors;
                queue[num].add(client);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void readHandle(SelectionKey key) {


        }

    }
}
