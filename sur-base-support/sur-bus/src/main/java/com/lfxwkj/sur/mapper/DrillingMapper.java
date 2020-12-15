package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Drilling;
import com.lfxwkj.sur.model.params.DrillingParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.model.result.DrillingResult;
import com.lfxwkj.sur.model.result.DrillingVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 勘探点数据表 Mapper 接口
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
@Repository
public interface DrillingMapper extends BaseMapper<Drilling> {

    /**
     * 获取列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    List<DrillingResult> customList(@Param("paramCondition") DrillingParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") DrillingParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    Page<DrillingResult> customPageList(@Param("page") Page page, @Param("paramCondition") DrillingParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") DrillingParam paramCondition);

    /**
     * 选择多个勘探点查询列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    List<DrillingResult> selectDrillingByIds(@Param("strings") String[] strings,@Param("itemId")Long itemId);

    /**
     * 地图上点击某个工程查询该工程的钻孔信息
     *
     * @author 张童
     * @Date 2020-09-17
     */
    List<DrillingVo> selectDrillingByItemId(@Param("paramCondition") DrillingParam drillingParam);

    List<Drilling> selectExplorationPoints(@Param("itemId")Long itemId);

    Page<DrillingResult> customWaterPageList(@Param("page") Page page, @Param("paramCondition") DrillingParam paramCondition);
}
