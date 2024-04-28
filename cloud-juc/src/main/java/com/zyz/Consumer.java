package com.zyz;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/28 15:05:59
 */
public class Consumer implements Runnable {
    private PhoneQueue finishedQueue;

    public Consumer(PhoneQueue finishedQueue) {
        this.finishedQueue = finishedQueue;
    }

    private int count = 0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //在取得下一个手机之前会一直阻塞
                Phone phone = finishedQueue.take();
                //验证取得的手机是有序的，而且状态是DELIVERED的
                if (phone.getId() != count++ ||
                        phone.getStatus() != Phone.Status.DELIVERED) {
                    System.out.println("Error ->" + phone);
                    System.exit(-1);
                } else {
                    //使用手机
                    System.out.println(phone + "->Use");
                }
            }
        } catch (Exception e) {
            System.out.println("Consumer interrupted...");
        }
        System.out.println("Consumer done");
    }
}
