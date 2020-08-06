package threadPool;

import java.util.concurrent.*;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/8/6 08:08
 */
public class T05_SchedulePool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        /**
         * schedulePool 调度过程
         * schedule（runnable,delayTime, timeUtil）  调度一个任务 设置延迟时间
         * scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit)  调度一个任务 设置 周期性执行
         * 将任务加入到阻塞队列 DelayedWorkQueue() implement BlockingQueue
         * 假如 工作线程小于核心线程或没有工作线程  添加工作线程
         * 工作线程 执行  runWorker
         * 自旋 getTask     DelayedWorkQueue().take   获取第一个 任务  计算delay <= 0 返回执行  否则 await（delay）
         * 拿到任务后 看是否周期执行  不是 直接执行  是 设置下次执行时间 再放回去
         */
        ScheduledFuture<?> result = scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("aaa");
            }
        }, 10, TimeUnit.SECONDS);
        result.get();
    }

}
