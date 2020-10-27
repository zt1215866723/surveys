package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.SurStandard;
import com.lfxwkj.sur.mapper.SurStandardMapper;
import com.lfxwkj.sur.model.params.SurStandardParam;
import com.lfxwkj.sur.model.result.SurStandardResult;
import  com.lfxwkj.sur.service.SurStandardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 所有工程土层标准 服务实现类
 * </p>
 *
 * @author 张童
 * @since 2020-10-22
 */
@Service
public class SurStandardServiceImpl extends ServiceImpl<SurStandardMapper, SurStandard> implements SurStandardService {

    @Override
    public void add(SurStandardParam param){
        if(param.getThirdCode().equals("")){             //为次亚层提供默认值
            param.setThirdCode("0");
        }
        SurStandard entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SurStandardParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(SurStandardParam param){
        SurStandard oldEntity = getOldEntity(param);
        SurStandard newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SurStandardResult findBySpec(SurStandardParam param){
        return null;
    }

    @Override
    public List<SurStandardResult> findListBySpec(SurStandardParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(SurStandardParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(SurStandardParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private SurStandard getOldEntity(SurStandardParam param) {
        return this.getById(getKey(param));
    }

    private SurStandard getEntity(SurStandardParam param) {
        SurStandard entity = new SurStandard();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
