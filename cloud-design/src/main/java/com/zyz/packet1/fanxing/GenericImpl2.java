package com.zyz.packet1.fanxing;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/6/3 15:06:55
 */
public class GenericImpl2<T> implements Generic<T> {

    @Override
    public T getName(T name) {
        return name;
    }
}
