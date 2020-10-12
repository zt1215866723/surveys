package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.entity.SubDetail;
import com.lfxwkj.sur.model.params.SubDetailParam;
import com.lfxwkj.sur.model.result.SubDetailResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文档的目录详情。 Mapper 接口
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
public interface SubDetailMapper extends BaseMapper<SubDetail> {

    /**
     * 获取列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    List<SubDetailResult> customList(@Param("paramCondition") SubDetailParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") SubDetailParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    Page<SubDetailResult> customPageList(@Param("page") Page page, @Param("paramCondition") SubDetailParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") SubDetailParam paramCondition);

    /**
     * 获取树列表
     * @param subId
     * @return
     */
    List<LayuiTreeNode> getTree(Long subId);

    /**
     * 获取数据文件路径
     * @param itemId
     * @return
     */
    SubDetailResult getSynchronousFile(Long itemId);
}
