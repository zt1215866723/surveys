package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.StandardPenetration;
import com.lfxwkj.sur.model.params.StandardPenetrationParam;
import com.lfxwkj.sur.model.result.StandardPenetrationResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 标贯数据 服务类
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
public interface StandardPenetrationService extends IService<StandardPenetration> {

    /**
     * 新增
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void add(StandardPenetrationParam param);

    /**
     * 删除
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void delete(StandardPenetrationParam param);

    /**
     * 更新
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void update(StandardPenetrationParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
    StandardPenetrationResult findBySpec(StandardPenetrationParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<StandardPenetrationResult> findListBySpec(StandardPenetrationParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
     LayuiPageInfo findPageBySpec(StandardPenetrationParam param);

}
