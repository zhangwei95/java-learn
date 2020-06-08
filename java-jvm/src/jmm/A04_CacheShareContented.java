package jmm;

import sun.misc.Contended;

/**
 * 缓存行
 * 缓存-伪共享
 * -XX:-RestrictContended
 */
public class A04_CacheShareContented {

    private static T t = new T();

    private static class T {
        @Contended
        private volatile long x = 0L;
        @Contended
        private volatile long y = 0L;
    }



    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                t.x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                t.y = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }
}
