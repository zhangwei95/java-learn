package map;

import java.util.HashMap;
import java.util.Map;

public class HashMapMuitiThread {
    static Map<String,String > map =new HashMap<String, String>(16);//初始化容量

    public static class TestHashMapPutThread implements Runnable{

        int start=0;

        public TestHashMapPutThread(int start){

            this.start=start;
        }

        @Override
        public void run() {
            for (int i = 0; i <100000 ; i+=2) {
                System.out.println("Thread" + Thread.currentThread().getName() + "--putting----"+i);
                map.put(Integer.toString(i),String.valueOf(Math.random()*100));
            }
        }
    }

    public static class TestHashMapGetThread implements Runnable{

        public TestHashMapGetThread(){

        }

        @Override
        public void run() {
            for (int i = 0; i <100000 ; i+=2) {
                String s = map.get(Integer.toString(i));
                System.out.println("Thread" + Thread.currentThread().getName() +" value :"+s);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads =new Thread[100];

        for (int i = 0; i <threads.length ; i++) {
            threads[i]=new Thread(new TestHashMapPutThread(i));
        }

        for (int i = 0; i <100 ; i++) {
            threads[i].start();
            threads[i].join();
        }
        Thread getThread = new Thread(new TestHashMapGetThread());
        getThread.start();
        getThread.join();

        System.out.println("map.size()：" + map.size());
    }
}
