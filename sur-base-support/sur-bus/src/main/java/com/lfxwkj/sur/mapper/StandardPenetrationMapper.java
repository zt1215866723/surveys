package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.StandardPenetration;
import com.lfxwkj.sur.model.params.StandardPenetrationParam;
import com.lfxwkj.sur.model.result.StandardPenetrationResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标贯数据 Mapper 接口
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
public interface StandardPenetrationMapper extends BaseMapper<StandardPenetration> {

    /**
     * 获取列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<StandardPenetrationResult> customList(@Param("paramCondition") StandardPenetrationParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") StandardPenetrationParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    Page<StandardPenetrationResult> customPageList(@Param("page") Page page, @Param("paramCondition") StandardPenetrationParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") StandardPenetrationParam paramCondition);

}
