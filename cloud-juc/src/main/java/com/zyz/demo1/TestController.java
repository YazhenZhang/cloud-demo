package com.zyz.demo1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/22 17:36:34
 */
@RestController
public class TestController {

    @Resource
    @Qualifier(value = "commonThreadPoolExecutor")
    private ThreadPoolExecutor commonThreadPoolExecutor;

    @Resource
    @Qualifier(value = "checkThreadPoolExecutor")
    private ThreadPoolExecutor checkThreadPoolExecutor;

    @Resource
    @Qualifier(value = "payThreadPoolExecutor")
    private ThreadPoolExecutor payThreadPoolExecutor;

    @Resource
    @Qualifier(value = "noticeThreadPoolExecutor")
    private ThreadPoolExecutor noticeThreadPoolExecutor;

    @Resource
    @Qualifier(value = "deadThreadPoolExecutor")
    private ThreadPoolExecutor deadThreadPoolExecutor;


    //所有业务使用同一个线程池
    @GetMapping("/onePool")
    public String onePool() {
        //因为每个方法耗时2秒，而线程池中最多只能有两个线程，所有总共耗时4秒
        long start = System.currentTimeMillis();
        Future<Integer> checkSubmit = commonThreadPoolExecutor.submit(() -> {
            return check();
        });
        Future<Integer> paySubmit = commonThreadPoolExecutor.submit(() -> {
            return pay();
        });
        Future<Integer> noticeSubmit = commonThreadPoolExecutor.submit(() -> {
            return notice();
        });
        try {
            int sum = checkSubmit.get() + paySubmit.get() + noticeSubmit.get();
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("onePool用时:" + (end - start));
        return "success";
    }

    //不同业务使用对应的线程池
    @GetMapping("/multiPool")
    public String multiPool() {
        //每个方法耗时2秒，不过使用的是不同的线程池池，因此最终用时2秒
        long start = System.currentTimeMillis();
        Future<Integer> checkSubmit = checkThreadPoolExecutor.submit(() -> {
            return check();
        });
        Future<Integer> paySubmit = payThreadPoolExecutor.submit(() -> {
            return pay();
        });
        Future<Integer> noticeSubmit = noticeThreadPoolExecutor.submit(() -> {
            return notice();
        });
        try {
            int sum = checkSubmit.get() + paySubmit.get() + noticeSubmit.get();
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("multiPool用时:" + (end - start));
        return "success";
    }

    //模拟不同业务使用同一个线程池时可能出现的死锁现象
    @GetMapping("/dead")
    public String deadPool() {
        long start = System.currentTimeMillis();
        Future<Integer> submit = deadThreadPoolExecutor.submit(() -> {
            //线程池中最大线程数为1，这个线程用于处理如下逻辑
            // 但是在deadNotice()方法中，也有用到同一个线程池，但此时该线程池没有线程池可用，因此deadNotice()迟迟无法
            int notice = deadNotice();
            return notice + pay();
        });
        try {
            System.out.println(submit.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("deadPool用时:" + (end - start));
        return "success";
    }

    public int deadNotice() throws ExecutionException, InterruptedException {
        return deadThreadPoolExecutor.submit(() -> {
            return notice();
        }).get();
    }

    //支付前检查
    public int check() {
        try {
            System.out.println("检査余额，选择支付方式=============");
            Thread.sleep(2000);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //支付
    public int pay() {
        try {
            System.out.println("进行支付===================");
            Thread.sleep(2000);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //发送支付消息
    public int notice() {
        try {
            System.out.println("发送通知===============");
            Thread.sleep(2000);
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }
    }


}
