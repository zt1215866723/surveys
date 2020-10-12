package com.lfxwkj.sur.core.quartz.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.core.quartz.entity.SysTask;
import com.lfxwkj.sur.core.quartz.model.params.SysTaskParam;
import com.lfxwkj.sur.core.quartz.model.result.SysTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 郭晓东
 * @since 2020-07-14
 */
public interface SysTaskService extends IService<SysTask> {

    /**
     * 新增
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    void add(SysTaskParam param);

    /**
     * 删除
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    void delete(SysTaskParam param);

    /**
     * 更新
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    void update(SysTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    SysTaskResult findBySpec(SysTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    List<SysTaskResult> findListBySpec(SysTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
     LayuiPageInfo findPageBySpec(SysTaskParam param);

}
