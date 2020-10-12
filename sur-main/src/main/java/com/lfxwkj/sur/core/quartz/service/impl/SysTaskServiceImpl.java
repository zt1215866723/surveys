package com.lfxwkj.sur.core.quartz.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.core.quartz.entity.SysTask;
import com.lfxwkj.sur.core.quartz.exception.TaskOperationEnum;
import com.lfxwkj.sur.core.quartz.exception.TaskOperationException;
import com.lfxwkj.sur.core.quartz.mapper.SysTaskMapper;
import com.lfxwkj.sur.core.quartz.model.params.SysTaskParam;
import com.lfxwkj.sur.core.quartz.model.result.SysTaskResult;
import  com.lfxwkj.sur.core.quartz.service.SysTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 郭晓东
 * @since 2020-07-14
 */
@Service
public class SysTaskServiceImpl extends ServiceImpl<SysTaskMapper, SysTask> implements SysTaskService {

    @Autowired
    private Scheduler scheduler;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysTaskParam param){
        Class clazz = null;
        String jobName = UUID.randomUUID().toString();
        param.setJobName(jobName);
        param.setCreateTime(new Date());
        try {
            clazz = Class.forName(param.getBeanClass());
        } catch (Exception e) {
            throw new TaskOperationException(TaskOperationEnum.CLASS_ERROR);
        }
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(jobName, param.getJobGroup()).build();
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(param.getCronExpression());
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, param.getJobGroup())
                    .withSchedule(scheduleBuilder).build();
            // 加载到定时任务的容器中
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e){
            throw new TaskOperationException(TaskOperationEnum.CRON_ERROR);
        }
        SysTask entity = getEntity(param);
        this.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(SysTaskParam param){
        SysTask sysTask = this.getById(param.getId());
        JobKey jobKey = new JobKey(sysTask.getJobName(), sysTask.getJobGroup());
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new TaskOperationException(TaskOperationEnum.JOB_DELETE_ERROR);
        }
        this.removeById(getKey(param));
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysTaskParam param){
        SysTask oldEntity = getOldEntity(param);
        SysTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        try {
            JobKey jobKey = new JobKey(oldEntity.getJobName(), oldEntity.getJobGroup());
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new TaskOperationException(TaskOperationEnum.JOB_UPDATE_DELETE);
        }
        String jobName = UUID.randomUUID().toString();
        newEntity.setJobName(jobName);
        Class clazz = null;
        try {
            clazz = Class.forName(param.getBeanClass());
        } catch (Exception e) {
            throw new TaskOperationException(TaskOperationEnum.JOB_UPDATE_CLASS);
        }
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(jobName, param.getJobGroup()).build();
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(param.getCronExpression());
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, param.getJobGroup())
                    .withSchedule(scheduleBuilder).build();
            // 加载到定时任务的容器中
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e){
            throw new TaskOperationException(TaskOperationEnum.JOB_UPDATE_CRON);
        }
        newEntity.setCreateTime(new Date());
        this.updateById(newEntity);
    }

    @Override
    public SysTaskResult findBySpec(SysTaskParam param){
        return null;
    }

    @Override
    public List<SysTaskResult> findListBySpec(SysTaskParam param){
        return this.baseMapper.customList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(SysTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(SysTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private SysTask getOldEntity(SysTaskParam param) {
        return this.getById(getKey(param));
    }

    private SysTask getEntity(SysTaskParam param) {
        SysTask entity = new SysTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
