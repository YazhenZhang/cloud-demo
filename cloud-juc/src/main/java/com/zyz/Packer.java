package com.zyz;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/28 15:05:59
 */
public class Packer implements Runnable {
    private PhoneQueue producedQueue;
    private PhoneQueue packedQueue;

    public Packer(PhoneQueue producedQueue, PhoneQueue packedQueue) {
        this.producedQueue = producedQueue;
        this.packedQueue = packedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //在取得下一个手机之前会一直阻塞
                Phone phone = producedQueue.take();
                phone.pack();
                System.out.println(phone);
                packedQueue.put(phone);
            }
        } catch (Exception e) {
            System.out.println("Packer interrupted...");
        }
        System.out.println("Packer done");
    }
}
