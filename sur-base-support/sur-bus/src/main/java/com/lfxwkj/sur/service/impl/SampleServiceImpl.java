package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Sample;
import com.lfxwkj.sur.mapper.SampleMapper;
import com.lfxwkj.sur.model.params.SampleParam;
import com.lfxwkj.sur.model.result.SampleResult;
import  com.lfxwkj.sur.service.SampleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 取样数据表 服务实现类
 * </p>
 *
 * @author 张童
 * @since 2020-10-22
 */
@Service
public class SampleServiceImpl extends ServiceImpl<SampleMapper, Sample> implements SampleService {

    @Override
    public void add(SampleParam param){
        Sample entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SampleParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(SampleParam param){
        Sample oldEntity = getOldEntity(param);
        Sample newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SampleResult findBySpec(SampleParam param){
        return null;
    }

    @Override
    public List<SampleResult> findListBySpec(SampleParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(SampleParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(SampleParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Sample getOldEntity(SampleParam param) {
        return this.getById(getKey(param));
    }

    private Sample getEntity(SampleParam param) {
        Sample entity = new Sample();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
