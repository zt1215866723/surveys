package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.WaterLevel;
import com.lfxwkj.sur.mapper.WaterLevelMapper;
import com.lfxwkj.sur.model.params.WaterLevelParam;
import com.lfxwkj.sur.model.result.WaterLevelResult;
import  com.lfxwkj.sur.service.WaterLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 水位信息 服务实现类
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@Service
public class WaterLevelServiceImpl extends ServiceImpl<WaterLevelMapper, WaterLevel> implements WaterLevelService {

    @Override
    public void add(WaterLevelParam param){
        WaterLevel entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(WaterLevelParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(WaterLevelParam param){
        WaterLevel oldEntity = getOldEntity(param);
        WaterLevel newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WaterLevelResult findBySpec(WaterLevelParam param){
        return null;
    }

    @Override
    public List<WaterLevelResult> findListBySpec(WaterLevelParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(WaterLevelParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WaterLevelParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WaterLevel getOldEntity(WaterLevelParam param) {
        return this.getById(getKey(param));
    }

    private WaterLevel getEntity(WaterLevelParam param) {
        WaterLevel entity = new WaterLevel();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
