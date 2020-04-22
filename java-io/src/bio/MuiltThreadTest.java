package bio;


import java.io.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MuiltThreadTest {

    public static void main(String[] args) {
        AtomicBoolean flag = new AtomicBoolean(true);
        for(AtomicInteger i = new AtomicInteger(1); i.get()<=50; i.incrementAndGet()){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    String var = "hai this is a test" + threadName;
                    File ouFile = new File("D:\\gitRepository\\java-learn\\java-io\\src\\bio/b"+threadName+".txt");
                    OutputStream outputStream = null;
                    try {
                        outputStream = new FileOutputStream(ouFile);
                        System.out.println("open stream "+threadName+" "+System.currentTimeMillis());
                        outputStream.write(var.getBytes());
                        System.out.println("open stream write "+threadName+" "+System.currentTimeMillis());

                        Integer random = new Random().nextInt(3)+1;
                        System.out.println(threadName+"sleep "+random+"s");
                        Thread.sleep(random);
                        if(threadName.equals("thread50")){
                            flag.set(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            System.out.println("thread start" + i.get());
            thread.setName("thread"+i.get());
            thread.start();
        }

        while (flag.get()){
            System.out.println("main   "+System.currentTimeMillis());
        }
    }

}
