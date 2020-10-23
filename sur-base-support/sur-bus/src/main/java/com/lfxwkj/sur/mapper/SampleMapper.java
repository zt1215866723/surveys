package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.Sample;
import com.lfxwkj.sur.model.params.SampleParam;
import com.lfxwkj.sur.model.result.SampleResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 取样数据表 Mapper 接口
 * </p>
 *
 * @author 张童
 * @since 2020-10-22
 */
public interface SampleMapper extends BaseMapper<Sample> {

    /**
     * 获取列表
     *
     * @author 张童
     * @Date 2020-10-22
     */
    List<SampleResult> customList(@Param("paramCondition") SampleParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 张童
     * @Date 2020-10-22
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") SampleParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 张童
     * @Date 2020-10-22
     */
    Page<SampleResult> customPageList(@Param("page") Page page, @Param("paramCondition") SampleParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 张童
     * @Date 2020-10-22
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") SampleParam paramCondition);

}
