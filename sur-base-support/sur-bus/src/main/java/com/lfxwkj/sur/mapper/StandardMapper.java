package com.lfxwkj.sur.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.entity.Standard;
import com.lfxwkj.sur.model.params.StandardParam;
import com.lfxwkj.sur.model.result.StandardResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 地层信息 Mapper 接口
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
public interface StandardMapper extends BaseMapper<Standard> {

    /**
     * 获取列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    List<StandardResult> customList(@Param("paramCondition") StandardParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") StandardParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    Page<StandardResult> customPageList(@Param("page") Page page, @Param("paramCondition") StandardParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") StandardParam paramCondition);

    /**
     * 选择多个地层信息统计查询列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    List<StandardResult> selectStandardByIds(@Param("itemId")Long itemId);

    /**
     * 查询单个钻孔柱状图
     *
     * @author 张童
     * @Date 2020-09-21
     */
    List<StandardResult> selectStandardHistogram(@Param("holeCode")String holeCode, @Param("itemId")Long itemId);

    /**
     * 查询钻孔剖面图
     *
     * @author 张童
     * @Date 2020-09-21
     */
    List<StandardResult> selectStandardsHistogram(@Param("strings") String[] strings,@Param("itemId")Long itemId);
}
