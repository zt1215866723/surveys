package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Drilling;
import com.lfxwkj.sur.mapper.DrillingMapper;
import com.lfxwkj.sur.model.params.DrillingParam;
import com.lfxwkj.sur.model.result.DrillingResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfxwkj.sur.model.result.DrillingVo;
import com.lfxwkj.sur.service.DrillingService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 勘探点数据表 服务实现类
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
@Service
public class DrillingServiceImpl extends ServiceImpl<DrillingMapper, Drilling> implements DrillingService {

    @Override
    public void add(DrillingParam param){
        Drilling entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(DrillingParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(DrillingParam param){
        Drilling oldEntity = getOldEntity(param);
        Drilling newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public DrillingResult findBySpec(DrillingParam param){
        return null;
    }

    @Override
    public List<DrillingResult> findListBySpec(DrillingParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(DrillingParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DrillingParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Drilling getOldEntity(DrillingParam param) {
        return this.getById(getKey(param));
    }

    private Drilling getEntity(DrillingParam param) {
        Drilling entity = new Drilling();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    /**
     * 选择多个勘探点查询列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @Override
    public  List<DrillingResult> selectDrillingByIds(String holeCode,Long itemId) {
        //钻孔编号
        String[] strings = holeCode.split(",");
        return this.baseMapper.selectDrillingByIds(strings,itemId);
    }

    /**
     * 地图上点击某个工程查询该工程的钻孔信息
     *
     * @author 张童
     * @Date 2020-09-17
     */
    @Override
    public List<DrillingVo> selectDrillingByItemId(DrillingParam drillingParam) {
        return this.baseMapper.selectDrillingByItemId(drillingParam);
    }

}
