package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.SurStandard;
import com.lfxwkj.sur.model.params.SurStandardParam;
import com.lfxwkj.sur.model.result.SurStandardResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 所有工程土层标准 服务类
 * </p>
 *
 * @author 张童
 * @since 2020-10-22
 */
public interface SurStandardService extends IService<SurStandard> {

    /**
     * 新增
     *
     * @author 张童
     * @Date 2020-10-22
     */
    void add(SurStandardParam param);

    /**
     * 删除
     *
     * @author 张童
     * @Date 2020-10-22
     */
    void delete(SurStandardParam param);

    /**
     * 更新
     *
     * @author 张童
     * @Date 2020-10-22
     */
    void update(SurStandardParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 张童
     * @Date 2020-10-22
     */
    SurStandardResult findBySpec(SurStandardParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 张童
     * @Date 2020-10-22
     */
    List<SurStandardResult> findListBySpec(SurStandardParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 张童
     * @Date 2020-10-22
     */
     LayuiPageInfo findPageBySpec(SurStandardParam param);

}
