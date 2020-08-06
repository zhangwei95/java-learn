package threadPool;

import java.util.concurrent.*;

public class T01_CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        // 异步
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello call";
            }
        });
        // future.get阻塞
        System.out.println(future.get());

        service.shutdown();
    }
}
