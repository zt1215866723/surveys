package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.ItemType;
import com.lfxwkj.sur.model.params.ItemTypeParam;
import com.lfxwkj.sur.model.result.ItemTypeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 项目类型表 服务类
 * </p>
 *
 * @author 王南翔
 * @since 2020-10-29
 */
public interface ItemTypeService extends IService<ItemType> {

    /**
     * 新增
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    void add(ItemTypeParam param);

    /**
     * 删除
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    void delete(ItemTypeParam param);

    /**
     * 更新
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    void update(ItemTypeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    ItemTypeResult findBySpec(ItemTypeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    List<ItemTypeResult> findListBySpec(ItemTypeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
     LayuiPageInfo findPageBySpec(ItemTypeParam param);

}
