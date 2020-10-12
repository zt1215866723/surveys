package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.StandardPenetration;
import com.lfxwkj.sur.mapper.StandardPenetrationMapper;
import com.lfxwkj.sur.model.params.StandardPenetrationParam;
import com.lfxwkj.sur.model.result.StandardPenetrationResult;
import  com.lfxwkj.sur.service.StandardPenetrationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 标贯数据 服务实现类
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@Service
public class StandardPenetrationServiceImpl extends ServiceImpl<StandardPenetrationMapper, StandardPenetration> implements StandardPenetrationService {

    @Override
    public void add(StandardPenetrationParam param){
        StandardPenetration entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(StandardPenetrationParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(StandardPenetrationParam param){
        StandardPenetration oldEntity = getOldEntity(param);
        StandardPenetration newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public StandardPenetrationResult findBySpec(StandardPenetrationParam param){
        return null;
    }

    @Override
    public List<StandardPenetrationResult> findListBySpec(StandardPenetrationParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(StandardPenetrationParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(StandardPenetrationParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private StandardPenetration getOldEntity(StandardPenetrationParam param) {
        return this.getById(getKey(param));
    }

    private StandardPenetration getEntity(StandardPenetrationParam param) {
        StandardPenetration entity = new StandardPenetration();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
