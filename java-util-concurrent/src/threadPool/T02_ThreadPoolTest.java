package threadPool;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池使用
 * @author zhangwei
 * -Xms60m -Xmx60m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
 */
public class T02_ThreadPoolTest {


    public static void main(String[] args) {
        // 创建一个单线程化的Executor
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        // 创建固定数目的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);


        // 创建一个支持定时及周期性的任务执行的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        // 创建一个可缓存的线程池，调用execute将重用以前构成的线程（如果线程可用）。
        // 如果没有可用的线程，则创建一个新线程并添加到池中。终止并从缓存中移出那些已有60秒钟未被使用的线程。
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


        // 以上方法不推荐使用
        // 默认SingleThreadPool 和 FixedThreadPool 请求队列使用无界队列LinkedBlockingQueue 默认大小为 INTEGER.MAX 当堆积过多的请求时 会导致OOM
        // 默认CacheThreadPool 和scheduledThreadPool 允许创建线程数量 默认大小为INTEGER.MAX 可能会创建大量线程 会导致OOM

        // 手动创建 有界队列的ArrayBlockingQueue  超过最大阻塞队列 抛出RejectedExecutionException 拒绝
        ExecutorService executor = new ThreadPoolExecutor(10, //核心线程数  一直存在
                50, // 最大允许线程数 当前工作线程 超过当前线程数 并且小于最大线程数 线程池会创建新线程
                60L, // 当 当前线程大于核心线程数时，空闲线程最长等待任务时间  超时终止
                TimeUnit.SECONDS, // keepAliveTime 的单位
                new LinkedBlockingQueue<>(1000),// 阻塞队列
                new MyThreadFactory("myThread"),// 线程工厂
                new ThreadPoolExecutor.DiscardOldestPolicy()); // 任务数超过最大阻塞队列时的拒绝策略
        // 拒绝策略 4种
        // DiscardOldestPolicy  丢掉最早的
        // DiscardPolicy 偷偷丢掉新任务
        // AbortPolicy 抛异常
        // CallerRunsPolicy 在调用线程中执行
        T02_ThreadPoolTest.multiThreadExecutorRun(executor);

        CompletableFuture.supplyAsync(()->{
            return 1;
        },executor);


        System.out.println("Main Exit");
        System.exit(0);
    }




    /**
     * 自定义 线程工厂
     */
    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory(String threadName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "MyThread-pool-" +
                    poolNumber.getAndIncrement() +
                    "-"+threadName +"-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }


    public static void multiThreadExecutorRun(ExecutorService executor) {
        CountDownLatch latch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " thread run");
                } catch (InterruptedException e) {

                } finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {

        }
    }





}
