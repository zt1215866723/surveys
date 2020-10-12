package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.Focus;
import com.lfxwkj.sur.model.params.FocusParam;
import com.lfxwkj.sur.model.result.FocusResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关注项 Mapper 接口
 * </p>
 *
 * @author lizheng
 * @since 2020-08-19
 */
public interface FocusMapper extends BaseMapper<Focus> {

    /**
     * 获取列表
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    List<FocusResult> customList(@Param("paramCondition") FocusParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") FocusParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    Page<FocusResult> customPageList(@Param("page") Page page, @Param("paramCondition") FocusParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") FocusParam paramCondition);

}
