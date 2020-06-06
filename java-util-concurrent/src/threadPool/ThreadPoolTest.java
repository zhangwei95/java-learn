package threadPool;

import java.util.concurrent.*;

/**
 * 线程池使用
 * @author zhangwei
 * -Xms60m -Xmx60m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
 */
public class ThreadPoolTest {


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
        ExecutorService executor = new ThreadPoolExecutor(10,50,
                60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(1000));
        ;
        ThreadPoolTest.multiThreadExecutorRun(fixedThreadPool);

        System.out.println("Main Exit");
        System.exit(0);
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
