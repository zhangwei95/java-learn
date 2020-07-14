package nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/13 17:15
 */
public class SelectorThreadGroup {
    private SelectorThread[] threads;

    private ServerSocketChannel server = null;

    private AtomicInteger xid = new AtomicInteger(0);

    private SelectorThreadGroup worker = this;

    public void setWorker(SelectorThreadGroup worker){
        this.worker = worker;
    }

    SelectorThreadGroup(int num){
        threads = new SelectorThread[num];

        for (int i = 0; i < num ;i++){
            threads[i] = new SelectorThread(this);
            new Thread(threads[i]).start();
        }

    }

    public void bind(int port){
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));

            nextSelectorV3(server);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void nextSelectorV3(Channel c){
        try {
            if (c instanceof ServerSocketChannel){
                SelectorThread serverThread = next();
                serverThread.bQueue.put(c);
                serverThread.setWorker(worker);
                serverThread.selector.wakeup();
            } else {
                SelectorThread selectorThread = nextV3();
                selectorThread.bQueue.add(c);
                selectorThread.selector.wakeup();
            }

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void nextSelector(Channel c) {

        SelectorThread serverThread = next();
        serverThread.bQueue.add(c);
        serverThread.selector.wakeup();

    }


    private SelectorThread next(){
        int index = xid.getAndIncrement() % threads.length;
        return threads[index];
    }


    private SelectorThread nextV3() {
        int index = xid.incrementAndGet() % worker.threads.length;  //动用worker的线程分配
        return worker.threads[index];
    }

}
