package com.dts.manager.config;

import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);
    private static final String DATA_EXTRACT_THREAD = "data.extract.thread";

    @Bean(name = "asyncServiceExecutor")
    @Description("init thread pool")
    public Executor asyncServiceExecutor() {
        logger.info("start asyncServiceExecutor");
        return init();
    }

    private ThreadPoolTaskExecutor init(String threadName) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setBeanName(threadName);
        executor.initialize();
        return executor;
    }

    public ThreadPoolTaskExecutor init() {
        return init(DATA_EXTRACT_THREAD);
    }
}
