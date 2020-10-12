package com.lfxwkj.sur.config;

import com.lfxwkj.sur.core.quartz.core.StartQuartz;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务自动配置(需要定时任务的可以放开注释)
 *
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {

    /**
     * quartz方式，配置Scheduler实例
     *
     */
    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
        return schedulerFactoryBean.getScheduler();
    }

    /**
     * 启动quartz
     *
     */
    @Bean
    public StartQuartz startQuartzExample() {
        return new StartQuartz();
    }

}
