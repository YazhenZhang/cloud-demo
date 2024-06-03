package com.zyz.completebleFuture;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/5/5 12:58:41
 */
public class Demo {

    public void demo1() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int i = 1 / 0;
            return "hello world";
        });

        future.whenComplete((s, throwable) -> System.out.println(s));

        future.exceptionally(t -> {
            System.out.println("执行失败：" + t.getMessage());
            return "异常xxx";
        });
        future.join();
    }

    public void demo2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 2;
        });

        CompletableFuture<Integer> future = future1.thenCombine(future2, (x, y) -> {
            return x + y;
        });
        Integer result = future.get();
        System.out.println(result);
    }

    public void demo3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            sleepMock(number);
            return number;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            sleepMock(number);
            return number;
        });

        CompletableFuture<Integer> future = future1.applyToEither(future2, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer number) {
                System.out.println("最快结果：" + number);
                return number;
            }
        });
        Integer result = future.get();
        System.out.println(result);
    }

    public void demo4() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            sleepMock(1);
            return 1;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            sleepMock(2);
            return 2;
        });
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            sleepMock(3);
            return 3;
        });

        CompletableFuture.allOf(future1, future2, future3).join();
        Integer min = Stream.of(future1, future2, future3).map(item -> {
            try {
                return item.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).sorted().findFirst().orElseThrow(() -> new RuntimeException("没有最小值"));
        System.out.println(min);
    }

    public void demo5() throws ExecutionException, InterruptedException {
        List<Integer> resultList = CMUtil.parallelFutureJoin(Arrays.asList(1, 3, 5),
                num -> {
                    sleepMock(1);
                    // 模拟程序运行中报错
//                    if (num == 1) {
//                        int i = 1/0;
//                    }
                    return num;
                },
                e -> {
                    if (e instanceof BusinessException) {
                        System.out.println("BusinessException: " + e.getMessage());
                    } else {
                        System.out.println("Exception: " + e.getMessage());
                    }
                }
        );
        System.out.println(resultList);

    }


    private void sleepMock(int i) {

    }

    class BusinessException extends Exception {

        private String msg;

    }

}
