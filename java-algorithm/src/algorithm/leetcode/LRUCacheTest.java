package algorithm.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LRU 最不常用算法
 * leetCode 146
 */
public class LRUCacheTest {

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(0);
        int andIncrement = integer.incrementAndGet();
        System.out.println(andIncrement);
        LRUCache cache = new LRUCache(32);
        for (int i = 0; i < 100; i++) {
//            int random = new Random().nextInt(100);
            cache.put(i, i);
            if (i % 32 == 0) {
                cache.put(0, i);
            }
//            System.out.println(random);
        }

        int i = cache.get(0);
        System.out.println(i);

    }


    static class LRUCache extends LinkedHashMap<Integer,Integer> {

        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F,true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key,-1);
        }

        public void put(int key, int value) {
            super.put(key,value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest){
            return size() > capacity;
        }
    }


}
