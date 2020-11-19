package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.result.ItemResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目表 Mapper 接口
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@Repository
public interface ItemMapper extends BaseMapper<Item> {

    /**
     * 获取列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    List<ItemResult> customList(@Param("paramCondition") ItemParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ItemParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    Page<ItemResult> customPageList(@Param("page") Page page, @Param("paramCondition") ItemParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ItemParam paramCondition);

    /**
     * @Description  ：
     * @methodName   : getItemOnTheMap
     * @param        : * @param itemParam :
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    List<ItemResult> getItemOnTheMap(@Param("paramCondition") ItemParam itemParam);

    ItemResult getItemDetail(@Param("paramCondition") Long id);

    String getCountByType(@Param("paramCondition")Long dictId);

    /**
     * 工程热度
     * @return
     */
    List<Item> selectItemHot();
    /**
     * @Description  ：
     * @methodName   : getItemOnTheMapAddGZ
     * @param        : * @param itemParam :
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    List<ItemResult> getItemOnTheMapAddGZ(@Param("paramCondition") ItemParam itemParam);
}
