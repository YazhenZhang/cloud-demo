package com.zyz.packet1.inter;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/21 22:01:20
 */

/**
 * 接口其实是一个特殊的抽象类，里面的方法都是抽象方法
 * 接口还可以包含默认方法和静态方法（类方法），默认方法用default修饰，静态方法用static修饰，且这两种方法都允许有方法体
 * 接口还可以包含常量：常量的值在接口中是固定的，不能被修改。接口中的常量默认为公共（public）静态（static）常量
 * 抽象方法，通过接口实现类的实例对象调用
 * 默认方法，通过接口实现类的实例对象调用
 * 静态方法，通过接口名.方法名调用
 */
public interface CommonCar extends Car, Electric{

}
