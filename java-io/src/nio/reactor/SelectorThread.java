package nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/13 17:14
 */
public class SelectorThread extends ThreadLocal<LinkedBlockingQueue<Channel>> implements Runnable {


    Selector selector = null;

    LinkedBlockingQueue<Channel> bQueue = get();

    private SelectorThreadGroup group;

    @Override
    protected LinkedBlockingQueue<Channel> initialValue(){return new LinkedBlockingQueue<>();}

    SelectorThread(SelectorThreadGroup group){
        try {
            this.group = group;
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        while (true){

            try {
                // 阻塞
                int nums = selector.select();

                if (nums > 0){
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    while (iter.hasNext()){
                        SelectionKey key = iter.next();
                        iter.remove();

                        if (key.isAcceptable()){
                            acceptHandle(key);
                        } else if (key.isReadable()){
                            // todo handle read
                        } else if (key.isWritable()){
                            // todo handle write
                        }
                    }

                }

                if (!bQueue.isEmpty()) {
                    Channel channel = bQueue.take();
                    if (channel instanceof ServerSocketChannel) {
                        // 注册监听
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) channel;
                        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                        System.out.println(Thread.currentThread().getName() + " register listen");
                    } else if (channel instanceof SocketChannel) {
                        // 注册
                        SocketChannel socketChannel = (SocketChannel) channel;
                        ByteBuffer buffer = ByteBuffer.allocate(4096);
                        socketChannel.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println(Thread.currentThread().getName() + " register client" + socketChannel.getRemoteAddress());
                    }
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    private void acceptHandle(SelectionKey key) {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();

        try {
            SocketChannel client = channel.accept();
            client.configureBlocking(false);
            // register
            group.nextSelector(client);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setWorker(SelectorThreadGroup stgWorker) {
        this.group =  stgWorker;
    }

}
