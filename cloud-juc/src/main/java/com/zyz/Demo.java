package com.zyz;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/28 14:42:32
 */
public class Demo {
    //创建一个CountDownLatch实例
    private static volatile java.util.concurrent.CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("子线程1结束!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("子线程2结束!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        });

        //启动子线程
        threadOne.start();
        threadTwo.start();
        System.out.println("等待所有子线程结束---");
        // 等待子线程执行完毕，返回
        countDownLatch.await();
        System.out.println("所有子线程结束!");

    }
}