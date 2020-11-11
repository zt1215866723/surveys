package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Index;
import com.lfxwkj.sur.entity.ItemSub;
import com.lfxwkj.sur.model.params.ItemSubParam;
import com.lfxwkj.sur.model.result.ItemSubResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 项目勘察文件表 服务类
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
public interface ItemSubService extends IService<ItemSub> {

    /**
     * 新增
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    void add(ItemSubParam param);

    /**
     * 删除
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    void delete(ItemSubParam param);

    /**
     * 更新
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    void update(ItemSubParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    ItemSubResult findBySpec(ItemSubParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    List<ItemSubResult> findListBySpec(ItemSubParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
     LayuiPageInfo findPageBySpec(ItemSubParam param);

    List<Index> getList(ItemSubParam itemSubParam);

    List<LayuiTreeNode> getTree(Long id);
}
