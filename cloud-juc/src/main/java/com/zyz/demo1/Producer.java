package com.zyz.demo1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/28 15:05:59
 */
public class Producer implements Runnable{
    private PhoneQueue phoneQueue;
    private int count=0;

    private Random random =new Random(47);

    public Producer(PhoneQueue queue) {
        this.phoneQueue=queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(300 + random.nextInt(500));
                //生产一部手机，这些手机是有序的
                Phone phone = new Phone(count++);
                System.out.println(phone);
                //放到PhoneQueue中
                phoneQueue.put(phone);
            }
        } catch (Exception e) {
            System.out.println("Producer interrupted...");
        }
        System.out.println("Producer done");
    }
}
