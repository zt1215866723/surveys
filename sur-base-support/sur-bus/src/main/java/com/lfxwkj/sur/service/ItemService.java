package com.lfxwkj.sur.service;

import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.result.ItemResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目表 服务类
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
public interface ItemService extends IService<Item> {

    /**
     * 新增
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    void add(ItemParam param);

    /**
     * 删除
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    void delete(ItemParam param);

    /**
     * 更新
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    void update(ItemParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    ItemResult findBySpec(ItemParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    List<ItemResult> findListBySpec(ItemParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
     LayuiPageInfo findPageBySpec(ItemParam param);

    List<Item> getList(ItemParam itemParam);

    /**
     * 获取文档数列表
     * @param itemId
     * @return
     */
    List<LayuiTreeNode> getTree(Long itemId);

    /**
     * 理正数据同步
     * @param itemId
     * @param isCover
     * @return
     */
    ResponseData synchronous(Long itemId, int isCover) throws Exception;

    /**
     * @Description  ：在地图上展示所有工程信息
     * @methodName   : getItemOnTheMap
     * @param        : * @param itemParam :
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    List<ItemResult> getItemOnTheMap(ItemParam itemParam);

    /**
     * 查看详情接口
     *
     * @author zt
     * @Date 2020-09-23
     */
    ItemResult getItemDetail(Long id);

    List<Map<String, String>> itemECharts();

}
