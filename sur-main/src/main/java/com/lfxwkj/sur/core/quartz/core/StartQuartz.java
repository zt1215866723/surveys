package com.lfxwkj.sur.core.quartz.core;

import com.lfxwkj.sur.core.quartz.model.params.SysTaskParam;
import com.lfxwkj.sur.core.quartz.model.result.SysTaskResult;
import com.lfxwkj.sur.core.quartz.service.SysTaskService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.util.List;

/**
 * 项目启动之时加载所有的quartz任务
 */
public class StartQuartz implements CommandLineRunner {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysTaskService sysTaskService;

    @Override
    public void run(String... args) throws Exception {
        System.out.print("程序加载完毕，定时任务模块开始装载");
        // 搜索所有定时任务
        List<SysTaskResult> list = sysTaskService.findListBySpec(new SysTaskParam());
        // 启动调度器
        scheduler.start();
        // 状态所有任务
        for (SysTaskResult sysTaskResult : list) {
            Class clzz = Class.forName(sysTaskResult.getBeanClass());
            Object classObj = clzz.newInstance();
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(clzz)
                    .withIdentity(classObj.getClass().getName(), sysTaskResult.getJobGroup()).build();
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysTaskResult.getCronExpression());
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(classObj.getClass().getName(), sysTaskResult.getJobGroup())
                    .withSchedule(scheduleBuilder).build();
            // 加载到定时任务的容器中
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }
}