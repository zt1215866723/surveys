package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.ItemSub;
import com.lfxwkj.sur.model.params.ItemSubParam;
import com.lfxwkj.sur.model.result.ItemSubResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目勘察文件表 Mapper 接口
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
public interface ItemSubMapper extends BaseMapper<ItemSub> {

    /**
     * 获取列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    List<ItemSubResult> customList(@Param("paramCondition") ItemSubParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ItemSubParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    Page<ItemSubResult> customPageList(@Param("page") Page page, @Param("paramCondition") ItemSubParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ItemSubParam paramCondition);

}
