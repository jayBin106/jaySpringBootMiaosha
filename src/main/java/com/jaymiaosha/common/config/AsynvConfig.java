package com.jaymiaosha.common.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 多线程同步类
 * Created by lenovo on 2018/8/15.
 */
@SpringBootConfiguration
@EnableAsync
public class AsynvConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);  //设置ThreadPoolTaskExecutor核心数大小
        executor.setMaxPoolSize(10);//设置ThreadPoolTaskExecutor的最大池大小
        executor.setQueueCapacity(25); ////设置ThreadPoolTaskExecutorBlockingQueue的容量
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
