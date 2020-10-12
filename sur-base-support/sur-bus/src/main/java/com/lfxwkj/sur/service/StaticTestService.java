package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.StaticTest;
import com.lfxwkj.sur.model.params.StaticTestParam;
import com.lfxwkj.sur.model.result.StaticTestResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 静探表 服务类
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
public interface StaticTestService extends IService<StaticTest> {

    /**
     * 新增
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void add(StaticTestParam param);

    /**
     * 删除
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void delete(StaticTestParam param);

    /**
     * 更新
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void update(StaticTestParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
    StaticTestResult findBySpec(StaticTestParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<StaticTestResult> findListBySpec(StaticTestParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
     LayuiPageInfo findPageBySpec(StaticTestParam param);

    /**
     * 静力触探图
     *
     * @author 张童
     * @Date 2020-09-16
     */
     List<StaticTest> staticTestChart(StaticTestParam staticTestParam);
}
