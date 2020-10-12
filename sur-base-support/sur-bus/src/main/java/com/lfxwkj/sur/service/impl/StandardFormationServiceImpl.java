package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.StandardFormation;
import com.lfxwkj.sur.mapper.StandardFormationMapper;
import com.lfxwkj.sur.model.params.StandardFormationParam;
import com.lfxwkj.sur.model.result.StandardFormationResult;
import  com.lfxwkj.sur.service.StandardFormationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 工程土层标准 服务实现类
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@Service
public class StandardFormationServiceImpl extends ServiceImpl<StandardFormationMapper, StandardFormation> implements StandardFormationService {

    @Override
    public void add(StandardFormationParam param){
        StandardFormation entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(StandardFormationParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(StandardFormationParam param){
        StandardFormation oldEntity = getOldEntity(param);
        StandardFormation newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public StandardFormationResult findBySpec(StandardFormationParam param){
        return null;
    }

    @Override
    public List<StandardFormationResult> findListBySpec(StandardFormationParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(StandardFormationParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(StandardFormationParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private StandardFormation getOldEntity(StandardFormationParam param) {
        return this.getById(getKey(param));
    }

    private StandardFormation getEntity(StandardFormationParam param) {
        StandardFormation entity = new StandardFormation();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
