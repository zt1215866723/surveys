package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.WaterLevel;
import com.lfxwkj.sur.model.params.WaterLevelParam;
import com.lfxwkj.sur.model.result.WaterLevelResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 水位信息 Mapper 接口
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
public interface WaterLevelMapper extends BaseMapper<WaterLevel> {

    /**
     * 获取列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<WaterLevelResult> customList(@Param("paramCondition") WaterLevelParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WaterLevelParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    Page<WaterLevelResult> customPageList(@Param("page") Page page, @Param("paramCondition") WaterLevelParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WaterLevelParam paramCondition);

}
