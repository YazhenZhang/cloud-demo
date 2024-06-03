package com.zyz.demo1;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/28 15:05:59
 */
public class Delivery implements Runnable {
    private PhoneQueue butteredQueue;
    private PhoneQueue finishedQueue;

    public Delivery(PhoneQueue butteredQueue, PhoneQueue finishedQueue) {
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //在取得下一个手机之前会一直阻塞
                Phone phone = butteredQueue.take();
                phone.deliver();
                System.out.println(phone);
                finishedQueue.put(phone);
            }
        } catch (Exception e) {
            System.out.println("Deliverer interrupted...");
        }
        System.out.println("Deliverer done");
    }
}
