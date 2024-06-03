package com.zyz.packet1.fanxing;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/6/3 15:05:26
 */
public class GenericImpl implements Generic<String> {


    @Override
    public String getName(String name) {
        return name;
    }
}
