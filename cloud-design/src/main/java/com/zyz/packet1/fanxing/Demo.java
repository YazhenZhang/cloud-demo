package com.zyz.packet1.fanxing;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/6/3 15:08:27
 */
public class Demo {

    public static void main(String[] args) {
        Generic<String> iGeneric = new GenericImpl();
        String name = iGeneric.getName("zhangsan");
        System.out.println(name);


        Generic<String> iGeneric1 = new GenericImpl2();
        String name1 = iGeneric1.getName("lisi");
        System.out.println(name1);
    }
}
