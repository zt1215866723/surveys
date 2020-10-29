package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.DrillingType;
import com.lfxwkj.sur.mapper.DrillingTypeMapper;
import com.lfxwkj.sur.model.params.DrillingTypeParam;
import com.lfxwkj.sur.model.result.DrillingTypeResult;
import  com.lfxwkj.sur.service.DrillingTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 钻孔类型表 服务实现类
 * </p>
 *
 * @author 王南翔
 * @since 2020-10-29
 */
@Service
public class DrillingTypeServiceImpl extends ServiceImpl<DrillingTypeMapper, DrillingType> implements DrillingTypeService {

    @Override
    public void add(DrillingTypeParam param){
        DrillingType entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(DrillingTypeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(DrillingTypeParam param){
        DrillingType oldEntity = getOldEntity(param);
        DrillingType newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public DrillingTypeResult findBySpec(DrillingTypeParam param){
        return null;
    }

    @Override
    public List<DrillingTypeResult> findListBySpec(DrillingTypeParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(DrillingTypeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DrillingTypeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private DrillingType getOldEntity(DrillingTypeParam param) {
        return this.getById(getKey(param));
    }

    private DrillingType getEntity(DrillingTypeParam param) {
        DrillingType entity = new DrillingType();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
