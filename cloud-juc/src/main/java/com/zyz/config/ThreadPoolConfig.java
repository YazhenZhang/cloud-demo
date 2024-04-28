package com.zyz.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/22 17:37:12
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    //在某个项目中看到的一个线程池创建方式
    @Bean
    @Qualifier(value = "threadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                (int) (Runtime.getRuntime().availableProcessors() / (1 - 0.9)),
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(Runtime.getRuntime().availableProcessors()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Bean
    @Qualifier(value = "commonThreadPoolExecutor")
    public ThreadPoolExecutor commonThreadPoolExecutor() {
        return new ThreadPoolExecutor(2,
                2,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(Runtime.getRuntime().availableProcessors()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    //检查业务对应的线程池
    @Bean
    @Qualifier(value = "checkThreadPoolExecutor")
    public ThreadPoolExecutor checkThreadPoolExecutor() {
        return new ThreadPoolExecutor(2, 2,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(Runtime.getRuntime().availableProcessors()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Bean
    @Qualifier(value = "payThreadPoolExecutor")
    public ThreadPoolExecutor payThreadPoolExecutor() {
        return new ThreadPoolExecutor(2, 2,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(Runtime.getRuntime().availableProcessors()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    //通知业务对应的线程池
    @Bean
    @Qualifier(value = "noticeThreadPoolExecutor")
    public ThreadPoolExecutor noticeThreadPoolExecutor() {
        return new ThreadPoolExecutor(2, 2,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(Runtime.getRuntime().availableProcessors()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    //用来模拟线程池使用不当导致死锁的情况
    @Bean
    @Qualifier(value = "deadThreadPoolExecutor")
    public ThreadPoolExecutor deadThreadPoolExecutor() {
        return new ThreadPoolExecutor(1, 1,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(Runtime.getRuntime().availableProcessors()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
