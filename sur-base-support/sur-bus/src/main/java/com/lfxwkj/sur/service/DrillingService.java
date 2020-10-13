package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Drilling;
import com.lfxwkj.sur.model.params.DrillingParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.result.DrillingResult;
import com.lfxwkj.sur.model.result.DrillingVo;

import java.util.List;

/**
 * <p>
 * 勘探点数据表 服务类
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
public interface DrillingService extends IService<Drilling> {

    /**
     * 新增
     *
     * @author 张童
     * @Date 2020-09-15
     */
    void add(DrillingParam param);

    /**
     * 删除
     *
     * @author 张童
     * @Date 2020-09-15
     */
    void delete(DrillingParam param);

    /**
     * 更新
     *
     * @author 张童
     * @Date 2020-09-15
     */
    void update(DrillingParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-15
     */
    DrillingResult findBySpec(DrillingParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 张童
     * @Date 2020-09-15
     */
    List<DrillingResult> findListBySpec(DrillingParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-15
     */
     LayuiPageInfo findPageBySpec(DrillingParam param);

    /**
     * 选择多个勘探点查询列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    List<DrillingResult> selectDrillingByIds(String holeCode,Long itemId);

    /**
     * 地图上点击某个工程查询该工程的钻孔信息
     *
     * @author 张童
     * @Date 2020-09-17
     */
    List<DrillingVo> selectDrillingByItemId(DrillingParam drillingParam);

}
