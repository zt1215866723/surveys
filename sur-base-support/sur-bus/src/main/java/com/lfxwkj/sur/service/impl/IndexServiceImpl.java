package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Index;
import com.lfxwkj.sur.mapper.IndexMapper;
import com.lfxwkj.sur.model.params.IndexParam;
import com.lfxwkj.sur.model.result.IndexResult;
import  com.lfxwkj.sur.service.IndexService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 索引信息 服务实现类
 * </p>
 *
 * @author lizheng
 * @since 2020-08-19
 */
@Service
public class IndexServiceImpl extends ServiceImpl<IndexMapper, Index> implements IndexService {

    @Override
    public void add(IndexParam param){
        Index entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(IndexParam param){
        param.setState(1);
        this.update(param);
    }

    @Override
    public void update(IndexParam param){
        Index oldEntity = getOldEntity(param);
        Index newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public IndexResult findBySpec(IndexParam param){
        return null;
    }

    @Override
    public List<IndexResult> findListBySpec(IndexParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(IndexParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(IndexParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Index getOldEntity(IndexParam param) {
        return this.getById(getKey(param));
    }

    private Index getEntity(IndexParam param) {
        Index entity = new Index();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
