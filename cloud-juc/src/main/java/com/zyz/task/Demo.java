package com.zyz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/5/6 16:20:21
 */
@Slf4j
public class Demo {

    @Scheduled(cron = "0/2 * * * * ?")
    public void test1() {
        log.info("test 1 start ");
        try {
            TimeUnit.SECONDS.sleep(4);
            log.info("test 1===========");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Scheduled(cron = "0/2 * * * * ?")
    public void test2() {
        log.info("test 2 start ");
        try {
            TimeUnit.SECONDS.sleep(6);
            log.info("test 2==========");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
