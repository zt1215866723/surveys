package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Standard;
import com.lfxwkj.sur.model.params.StandardParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfxwkj.sur.model.result.StandardResult;

import java.util.List;

/**
 * <p>
 * 地层信息 服务类
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
public interface StandardService extends IService<Standard> {

    /**
     * 新增
     *
     * @author 张童
     * @Date 2020-09-15
     */
    void add(StandardParam param);

    /**
     * 删除
     *
     * @author 张童
     * @Date 2020-09-15
     */
    void delete(StandardParam param);

    /**
     * 更新
     *
     * @author 张童
     * @Date 2020-09-15
     */
    void update(StandardParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-15
     */
    StandardResult findBySpec(StandardParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 张童
     * @Date 2020-09-15
     */
    List<StandardResult> findListBySpec(StandardParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-15
     */
     LayuiPageInfo findPageBySpec(StandardParam param);

    /**
     * 选择多个地层信息统计查询列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    List<StandardResult> selectStandardByIds(String holeCode,Long itemId);

    /**
     * 查询单个钻孔柱状图
     *
     * @author 张童
     * @Date 2020-09-21
     */
    List<StandardResult> selectStandardHistogram(String holeCode, Long itemId);

    /**
     * 查询钻孔剖面图
     *
     * @author 张童
     * @Date 2020-09-21
     */
    List<StandardResult> selectStandardsHistogram(String holeCode, Long itemId);
}
