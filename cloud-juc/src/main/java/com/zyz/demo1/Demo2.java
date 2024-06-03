package com.zyz.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/28 15:25:58
 */
public class Demo2 {
    public static void main(String[] args) {
        PhoneQueue producedQueue = new PhoneQueue();
        PhoneQueue packedQueue = new PhoneQueue();
        PhoneQueue deliveredQueue = new PhoneQueue();

        // todo 换成自定义线程池
        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(new Producer(producedQueue));
        exec.execute(new Packer(producedQueue, packedQueue));
        exec.execute(new Delivery(packedQueue, deliveredQueue));
        exec.execute(new Consumer(deliveredQueue));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        exec.shutdownNow();
    }
}
