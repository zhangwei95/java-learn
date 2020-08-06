package threadPool;

import org.junit.Test;
import thread.ThreadByCallable;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class T03_CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {



        long start, end;

        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(new Supplier<Double>() {
            @Override
            public Double get() {
                return priceOfTM();
            }
        });
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(()->priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(()->priceOfJD());

        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();
        Double aDouble = futureTM.get();
        Double bDouble = futureTB.get();
        Double cDouble = futureJD.get();

        System.out.println(aDouble);
        System.out.println(bDouble);
        System.out.println(cDouble);

        CompletableFuture.supplyAsync(T03_CompletableFuture::priceOfTM)
                .thenApply(String::valueOf)
                .thenApply(str-> "price " + str)
                .thenAccept(System.out::println);

        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将两个子任务的结果合并处理
     */
    @Test
    public void combine(){
        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> 100d);
        CompletableFuture<Double> futureDiscount = CompletableFuture.supplyAsync(() -> 0.8);
        CompletableFuture<Double> futureResult = futurePrice.thenCombine(futureDiscount, (price, discount) -> price * discount);
        System.out.println("最终价格为:" + futureResult.join()); //最终价格为:80.0
    }

    @Test
    public void completableExecutor(){

//        CompletableFuture<Integer> threadByCallableCompletableFuture = CompletableFuture.supplyAsync(()->{
//            try {
//                Thread.sleep(new Random().nextInt(100));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return new Random().nextInt(100);
//        }, Executors.newFixedThreadPool(50)).thenAccept()
    }

    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    private static void delay() {
        int time = new Random().nextInt(1000);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
