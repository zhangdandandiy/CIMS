package com.ruoyi.framework.config;

import com.ruoyi.common.utils.Threads;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author Dandan
 **/
@Configuration
public class ThreadPoolConfig {
    // 核心线程池大小
    private int corePoolSize = 50;

    // 最大可创建的线程数
    private int maxPoolSize = 200;

    // 队列最大长度
    private int queueCapacity = 1000;

    // 线程池维护线程所允许的空闲时间
    private int keepAliveSeconds = 300;

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    /**
     * 执行周期性或定时任务
     * 在登录或者退出时创建记录日志的任务，交给线程池执行
     * 这段代码是用来定义一个名为 scheduledExecutorService 的 Bean，它返回一个 ScheduledExecutorService 类型的实例。
     * ScheduledExecutorService 是一个用于定时或延迟执行任务 ExecutorService，具体实现是一 ScheduledThreadPooLExecutor.
     * 可以通过构造函数设置核心线程数、线程工厂和拒绝策略。
     * 其中，BasicThreadFactory，Builder()是用于创建线程的工厂类，设置了线程命名格式，线程是否为守护线程等居性,
     * 这里的命名格式为"schedule-pool-%d"，表示线程名字以"schedule-pool-"开头，%d为数字序列。这样可以方便地识别线程来源
     * 拒绝策略是 CaLLerRunsPolicy，这意味着如果任务队列已满，新提交的任务将由提交任务的线程来执行，即当前线程会执行任务。
     * 这段代码还覆盖了 ScheduledThreadPoolExecutor的afterExecute() 方法，用于在线程执行完毕后打印可能的异常信息，
     * Threads.printException(r，t) 是一个自定义的工具类方法，用于打印异常信息。
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build(),
                new ThreadPoolExecutor.CallerRunsPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };
    }
}
