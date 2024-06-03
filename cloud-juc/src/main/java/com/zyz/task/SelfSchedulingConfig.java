package com.zyz.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/5/5 12:47:30
 */
@Configuration
public class SelfSchedulingConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // todo 这里只是举例，可以自定义线程池参数
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(20));
    }

}
