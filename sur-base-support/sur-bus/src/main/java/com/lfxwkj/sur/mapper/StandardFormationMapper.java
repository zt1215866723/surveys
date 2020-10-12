package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.StandardFormation;
import com.lfxwkj.sur.model.params.StandardFormationParam;
import com.lfxwkj.sur.model.result.StandardFormationResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工程土层标准 Mapper 接口
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
public interface StandardFormationMapper extends BaseMapper<StandardFormation> {

    /**
     * 获取列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<StandardFormationResult> customList(@Param("paramCondition") StandardFormationParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") StandardFormationParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    Page<StandardFormationResult> customPageList(@Param("page") Page page, @Param("paramCondition") StandardFormationParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") StandardFormationParam paramCondition);

}
