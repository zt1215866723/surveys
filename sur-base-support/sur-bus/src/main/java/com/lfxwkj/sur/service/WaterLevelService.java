package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.WaterLevel;
import com.lfxwkj.sur.model.params.WaterLevelParam;
import com.lfxwkj.sur.model.result.WaterLevelResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 水位信息 服务类
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
public interface WaterLevelService extends IService<WaterLevel> {

    /**
     * 新增
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void add(WaterLevelParam param);

    /**
     * 删除
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void delete(WaterLevelParam param);

    /**
     * 更新
     *
     * @author 张童
     * @Date 2020-09-16
     */
    void update(WaterLevelParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
    WaterLevelResult findBySpec(WaterLevelParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<WaterLevelResult> findListBySpec(WaterLevelParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 张童
     * @Date 2020-09-16
     */
     LayuiPageInfo findPageBySpec(WaterLevelParam param);

}
