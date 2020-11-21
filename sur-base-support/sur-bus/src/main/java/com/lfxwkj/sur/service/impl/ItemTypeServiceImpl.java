package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.ItemType;
import com.lfxwkj.sur.mapper.ItemTypeMapper;
import com.lfxwkj.sur.model.params.ItemTypeParam;
import com.lfxwkj.sur.model.result.ItemTypeResult;
import  com.lfxwkj.sur.service.ItemTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 项目类型表 服务实现类
 * </p>
 *
 * @author 王南翔
 * @since 2020-10-29
 */
@Service
public class ItemTypeServiceImpl extends ServiceImpl<ItemTypeMapper, ItemType> implements ItemTypeService {

    @Override
    public void add(ItemTypeParam param){
        ItemType entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ItemTypeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ItemTypeParam param){
        ItemType oldEntity = getOldEntity(param);
        ItemType newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ItemTypeResult findBySpec(ItemTypeParam param){
        return null;
    }

    @Override
    public List<ItemTypeResult> findListBySpec(ItemTypeParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ItemTypeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void deleteByLogic(ItemTypeParam param) {
        param.setStatus(1);
        this.update(param);
    }

    private Serializable getKey(ItemTypeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ItemType getOldEntity(ItemTypeParam param) {
        return this.getById(getKey(param));
    }

    private ItemType getEntity(ItemTypeParam param) {
        ItemType entity = new ItemType();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
