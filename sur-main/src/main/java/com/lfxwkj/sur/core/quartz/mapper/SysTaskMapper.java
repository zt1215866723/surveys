package com.lfxwkj.sur.core.quartz.mapper;

import com.lfxwkj.sur.core.quartz.entity.SysTask;
import com.lfxwkj.sur.core.quartz.model.params.SysTaskParam;
import com.lfxwkj.sur.core.quartz.model.result.SysTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 郭晓东
 * @since 2020-07-14
 */
public interface SysTaskMapper extends BaseMapper<SysTask> {

    /**
     * 获取列表
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    List<SysTaskResult> customList(@Param("paramCondition") SysTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") SysTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    Page<SysTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") SysTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") SysTaskParam paramCondition);

}
