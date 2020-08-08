package com.zhangwei95.collection.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 同步容器
 * 写时加锁复制
 * 读时不加锁
 * 适用：读多 写少
 * @Author: zhangwei
 * @Date: 2020/7/21 19:54
 */
public class C04_CopyOnWriteList {

    @Test
    public void copyOnWriteList() throws InterruptedException {
        List<String> list = new CopyOnWriteArrayList<>();
        Random random = new Random();
        Thread[] threads = new Thread[100];

        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    list.add("a" + random.nextInt(10000));
                }
                latch.countDown();
            });
        }

        runAndComputeTime(threads,latch);

        System.out.println(list.size());
    }

    static void runAndComputeTime(Thread[] ths,CountDownLatch latch) throws InterruptedException {
        long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        latch.await();
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);

    }
}
