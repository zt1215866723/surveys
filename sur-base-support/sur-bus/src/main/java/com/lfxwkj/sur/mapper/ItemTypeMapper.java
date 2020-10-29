package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.ItemType;
import com.lfxwkj.sur.model.params.ItemTypeParam;
import com.lfxwkj.sur.model.result.ItemTypeResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目类型表 Mapper 接口
 * </p>
 *
 * @author 王南翔
 * @since 2020-10-29
 */
public interface ItemTypeMapper extends BaseMapper<ItemType> {

    /**
     * 获取列表
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    List<ItemTypeResult> customList(@Param("paramCondition") ItemTypeParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ItemTypeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    Page<ItemTypeResult> customPageList(@Param("page") Page page, @Param("paramCondition") ItemTypeParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ItemTypeParam paramCondition);

}
