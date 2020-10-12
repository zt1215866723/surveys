package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.StaticTest;
import com.lfxwkj.sur.model.params.StaticTestParam;
import com.lfxwkj.sur.model.result.StaticTestResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 静探表 Mapper 接口
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
public interface StaticTestMapper extends BaseMapper<StaticTest> {

    /**
     * 获取列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<StaticTestResult> customList(@Param("paramCondition") StaticTestParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") StaticTestParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    Page<StaticTestResult> customPageList(@Param("page") Page page, @Param("paramCondition") StaticTestParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") StaticTestParam paramCondition);

}
