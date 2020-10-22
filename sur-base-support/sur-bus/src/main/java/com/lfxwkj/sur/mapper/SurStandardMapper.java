package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.SurStandard;
import com.lfxwkj.sur.model.params.SurStandardParam;
import com.lfxwkj.sur.model.result.SurStandardResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 所有工程土层标准 Mapper 接口
 * </p>
 *
 * @author 张童
 * @since 2020-10-22
 */
public interface SurStandardMapper extends BaseMapper<SurStandard> {

    /**
     * 获取列表
     *
     * @author 张童
     * @Date 2020-10-22
     */
    List<SurStandardResult> customList(@Param("paramCondition") SurStandardParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 张童
     * @Date 2020-10-22
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") SurStandardParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 张童
     * @Date 2020-10-22
     */
    Page<SurStandardResult> customPageList(@Param("page") Page page, @Param("paramCondition") SurStandardParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 张童
     * @Date 2020-10-22
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") SurStandardParam paramCondition);

}
