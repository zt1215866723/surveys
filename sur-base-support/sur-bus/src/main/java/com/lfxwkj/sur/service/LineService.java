package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Line;
import com.lfxwkj.sur.model.params.LineParam;
import com.lfxwkj.sur.model.result.LineResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 剖线数据表 服务类
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
public interface LineService extends IService<Line> {

    /**
     * 新增
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void add(LineParam param);

    /**
     * 删除
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void delete(LineParam param);

    /**
     * 更新
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void update(LineParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
    LineResult findBySpec(LineParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<LineResult> findListBySpec(LineParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
     LayuiPageInfo findPageBySpec(LineParam param);

}
