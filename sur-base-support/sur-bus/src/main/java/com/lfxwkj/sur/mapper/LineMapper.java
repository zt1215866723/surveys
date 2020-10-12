package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.Line;
import com.lfxwkj.sur.model.params.LineParam;
import com.lfxwkj.sur.model.result.LineResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 剖线数据表 Mapper 接口
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
public interface LineMapper extends BaseMapper<Line> {

    /**
     * 获取列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<LineResult> customList(@Param("paramCondition") LineParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") LineParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    Page<LineResult> customPageList(@Param("page") Page page, @Param("paramCondition") LineParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") LineParam paramCondition);

}
