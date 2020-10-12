package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.StaticTest;
import com.lfxwkj.sur.mapper.StaticTestMapper;
import com.lfxwkj.sur.model.params.StaticTestParam;
import com.lfxwkj.sur.model.result.StaticTestResult;
import  com.lfxwkj.sur.service.StaticTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 静探表 服务实现类
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@Service
public class StaticTestServiceImpl extends ServiceImpl<StaticTestMapper, StaticTest> implements StaticTestService {

    @Override
    public void add(StaticTestParam param){
        StaticTest entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(StaticTestParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(StaticTestParam param){
        StaticTest oldEntity = getOldEntity(param);
        StaticTest newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public StaticTestResult findBySpec(StaticTestParam param){
        return null;
    }

    @Override
    public List<StaticTestResult> findListBySpec(StaticTestParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(StaticTestParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(StaticTestParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private StaticTest getOldEntity(StaticTestParam param) {
        return this.getById(getKey(param));
    }

    private StaticTest getEntity(StaticTestParam param) {
        StaticTest entity = new StaticTest();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    /**
     * 静力触探图
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @Override
    public List<StaticTest> staticTestChart(StaticTestParam staticTestParam) {
        QueryWrapper<StaticTest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id",Long.parseLong(staticTestParam.getItemIds()));
        queryWrapper.eq("hole_code",staticTestParam.getHoleCode());
        queryWrapper.eq("cy",1);
        queryWrapper.orderByAsc("depth");
        List<StaticTest> staticTests = this.baseMapper.selectList(queryWrapper);
        return staticTests;
    }
}
