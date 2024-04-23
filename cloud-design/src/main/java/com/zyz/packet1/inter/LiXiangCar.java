package com.zyz.packet1.inter;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/20 22:21:24
 */

/**
 * 接口实现类：根据规范重头制造一个完整的产品
 */
public class LiXiangCar implements Car{
    @Override
    public void fly() {

        System.out.println("我安装了螺旋桨会飞了");
    }

    @Override
    public void run() {

        System.out.println("我安装了6个轮子会跑了");
    }
}
