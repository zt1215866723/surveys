package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.StandardFormation;
import com.lfxwkj.sur.model.params.StandardFormationParam;
import com.lfxwkj.sur.model.result.StandardFormationResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工程土层标准 服务类
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
public interface StandardFormationService extends IService<StandardFormation> {

    /**
     * 新增
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void add(StandardFormationParam param);

    /**
     * 删除
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void delete(StandardFormationParam param);

    /**
     * 更新
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void update(StandardFormationParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
    StandardFormationResult findBySpec(StandardFormationParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<StandardFormationResult> findListBySpec(StandardFormationParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
     LayuiPageInfo findPageBySpec(StandardFormationParam param);

}
