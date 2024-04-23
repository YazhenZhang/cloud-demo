package com.zyz.packet1.abs;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/20 22:01:10
 */

/**
 * 抽象类Car(产品的半成品)
 * 抽象类里面可以没有抽象方法
 * 抽象类也是类，因此原来类中可以有的成员，抽象类都可以有
 * 那么抽象类不能直接创建对象，为什么还有构造器呢？供子类调用，子类创建对象时，需要为从父类继承的属性初始化
 */
public abstract class Car {

    public void fly(){
        System.out.println("我安装了翅膀会飞了");
    }

    public abstract void run();

}

