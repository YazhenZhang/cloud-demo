package com.zyz.demo1;

import java.time.LocalDateTime;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/28 15:34:15
 */
public class OrderVo {
    private long id;
    private long userId;
    private LocalDateTime createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
