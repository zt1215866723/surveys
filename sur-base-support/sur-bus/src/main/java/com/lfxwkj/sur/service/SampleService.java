package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Sample;
import com.lfxwkj.sur.model.params.SampleParam;
import com.lfxwkj.sur.model.result.SampleResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 取样数据表 服务类
 * </p>
 *
 * @author 张童
 * @since 2020-10-22
 */
public interface SampleService extends IService<Sample> {

    /**
     * 新增
     *
     * @author 张童
     * @Date 2020-10-22
     */
    void add(SampleParam param);

    /**
     * 删除
     *
     * @author 张童
     * @Date 2020-10-22
     */
    void delete(SampleParam param);

    /**
     * 更新
     *
     * @author 张童
     * @Date 2020-10-22
     */
    void update(SampleParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 张童
     * @Date 2020-10-22
     */
    SampleResult findBySpec(SampleParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 张童
     * @Date 2020-10-22
     */
    List<SampleResult> findListBySpec(SampleParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 张童
     * @Date 2020-10-22
     */
     LayuiPageInfo findPageBySpec(SampleParam param);

}
