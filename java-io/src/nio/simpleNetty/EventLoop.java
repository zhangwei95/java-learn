package nio.simpleNetty;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/17 17:15
 */
public class EventLoop implements Executor {

    private Selector selector;
    /** running thread*/
    private Thread thread = null;

    private String name;

    private static final int NOT_STARTED = 1;
    /** RUNNING*/
    private static final int STARTED = 2;
    /** run state*/
    private AtomicInteger STATE = new AtomicInteger(1);

    private BlockingQueue<Runnable> events = new LinkedBlockingQueue<>();


    public EventLoop(String name){
        this.name = name;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Runnable task) {
        try {
            events.put(task);
            this.selector.wakeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!isEventLoop() && STATE.incrementAndGet() == STARTED) {
            new Thread(() -> {
                thread = Thread.currentThread();
                try {
                    EventLoop.this.run();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    public void run() throws IOException, InterruptedException {
        System.out.println("");
        for (; ; ) {
            int nums = selector.select();
            if (nums > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    Handler handler = (Handler) next.attachment();
                    if (handler instanceof ServerAccepter || handler instanceof ClientReadHandler) {
                        handler.doRead();
                    }

                }
            }
            runTask();
        }
    }

    private void runTask() throws InterruptedException {
        for (int i = 0;i < 5;i++){
            Runnable task = events.poll(10, TimeUnit.MILLISECONDS);
            if (task != null){
                events.remove(task);
                task.run();
            }
        }

    }

    private boolean isEventLoop(){
        return thread == Thread.currentThread();
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
