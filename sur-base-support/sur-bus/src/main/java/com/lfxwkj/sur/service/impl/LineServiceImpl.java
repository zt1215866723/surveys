package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Line;
import com.lfxwkj.sur.mapper.LineMapper;
import com.lfxwkj.sur.model.params.LineParam;
import com.lfxwkj.sur.model.result.LineResult;
import  com.lfxwkj.sur.service.LineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 剖线数据表 服务实现类
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@Service
public class LineServiceImpl extends ServiceImpl<LineMapper, Line> implements LineService {

    @Override
    public void add(LineParam param){
        Line entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(LineParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(LineParam param){
        Line oldEntity = getOldEntity(param);
        Line newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public LineResult findBySpec(LineParam param){
        return null;
    }

    @Override
    public List<LineResult> findListBySpec(LineParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(LineParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(LineParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Line getOldEntity(LineParam param) {
        return this.getById(getKey(param));
    }

    private Line getEntity(LineParam param) {
        Line entity = new Line();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
