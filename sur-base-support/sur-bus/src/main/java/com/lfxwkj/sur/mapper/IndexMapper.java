package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.Index;
import com.lfxwkj.sur.model.params.IndexParam;
import com.lfxwkj.sur.model.result.IndexResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 索引信息 Mapper 接口
 * </p>
 *
 * @author lizheng
 * @since 2020-08-19
 */
public interface IndexMapper extends BaseMapper<Index> {

    /**
     * 获取列表
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    List<IndexResult> customList(@Param("paramCondition") IndexParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") IndexParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    Page<IndexResult> customPageList(@Param("page") Page page, @Param("paramCondition") IndexParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") IndexParam paramCondition);

}
