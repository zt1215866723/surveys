package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.auth.context.LoginContextHolder;
import com.lfxwkj.sur.auth.model.LoginUser;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Focus;
import com.lfxwkj.sur.mapper.FocusMapper;
import com.lfxwkj.sur.model.params.FocusParam;
import com.lfxwkj.sur.model.result.FocusResult;
import  com.lfxwkj.sur.service.FocusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 关注项 服务实现类
 * </p>
 *
 * @author lizheng
 * @since 2020-08-19
 */
@Service
public class FocusServiceImpl extends ServiceImpl<FocusMapper, Focus> implements FocusService {

    @Override
    public void add(FocusParam param){
        LoginUser user = LoginContextHolder.getContext().getUser();
        Focus entity = getEntity(param);
        entity.setAddTime(new Date());
        entity.setAddUser(user.getId());
        this.save(entity);
    }

    @Override
    public void delete(FocusParam param){
        param.setState(1);
        this.update(param);
    }

    @Override
    public void update(FocusParam param){
        Focus oldEntity = getOldEntity(param);
        Focus newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public FocusResult findBySpec(FocusParam param){
        return null;
    }

    @Override
    public List<FocusResult> findListBySpec(FocusParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(FocusParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public List<Focus> getList(FocusParam focusParam) {
        QueryWrapper<Focus> focusQueryWrapper = new QueryWrapper<>();
        focusQueryWrapper.eq("state", 0);
        focusQueryWrapper.orderByAsc("sort");
        return this.list(focusQueryWrapper);
    }

    private Serializable getKey(FocusParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Focus getOldEntity(FocusParam param) {
        return this.getById(getKey(param));
    }

    private Focus getEntity(FocusParam param) {
        Focus entity = new Focus();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
