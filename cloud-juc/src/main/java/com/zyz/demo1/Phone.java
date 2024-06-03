package com.zyz.demo1;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/28 14:58:16
 */
public class Phone {
    /**
     * 手机的状态:
     * PRODUCED:已生产
     * PACKED:已打包
     * DELIVERED:已发货
     * 手机的状态只能由PRODUCED->PACKED->DELIVERED转变
     */
    public enum Status {
        PRODUCED, PACKED, DELIVERED
    }

    //默认状态为PRODUCED
    private Status status = Status.PRODUCED;
    private final int id;

    public Phone(int id) {
        this.id = id;
    }

    public void pack() {
        status = Status.PACKED;
    }

    public void deliver() {
        status = Status.DELIVERED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Phone id:" + id + ",status:" + status;
    }
}
