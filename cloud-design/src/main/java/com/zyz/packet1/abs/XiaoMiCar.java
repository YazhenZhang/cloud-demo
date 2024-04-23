package com.zyz.packet1.abs;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/20 22:12:19
 */

/**
 * 抽象类子类，在半成品的基础上来制造一个完整的产品
 */
public class XiaoMiCar extends Car{
    // 子类完成父类未完成的功能
    @Override
    public void run() {

        System.out.println("我安装了四个轮子会跑了");
    }

    // 扩展功能
    private void changeBody(){
        System.out.println("我还会变身成擎天柱");
    }

    // 如果不满意父类的自带功能，可以重新覆盖父类功能（重写父类方法）
    @Override
    public void fly(){
        System.out.println("我安装了螺旋桨会飞了");
    }
}
