package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfxwkj.sur.entity.Standard;
import com.lfxwkj.sur.mapper.StandardMapper;
import com.lfxwkj.sur.model.params.StandardParam;
import com.lfxwkj.sur.model.result.StandardResult;
import com.lfxwkj.sur.service.StandardService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 地层信息 服务实现类
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
@Service
public class StandardServiceImpl extends ServiceImpl<StandardMapper, Standard> implements StandardService {

    @Override
    public void add(StandardParam param){
        Standard entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(StandardParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(StandardParam param){
        Standard oldEntity = getOldEntity(param);
        Standard newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public StandardResult findBySpec(StandardParam param){
        return null;
    }

    @Override
    public List<StandardResult> findListBySpec(StandardParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(StandardParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(StandardParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Standard getOldEntity(StandardParam param) {
        return this.getById(getKey(param));
    }

    private Standard getEntity(StandardParam param) {
        Standard entity = new Standard();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    /**
     * 选择多个地层信息统计查询列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @Override
    public List<StandardResult> selectStandardByIds(String holeCode,Long itemId) {
        //钻孔编号
        String[] strings = holeCode.split(",");
        return this.baseMapper.selectStandardByIds(strings,itemId);
    }

    /**
     * 查询单个钻孔柱状图
     *
     * @author 张童
     * @Date 2020-09-21
     */
    @Override
    public List<StandardResult> selectStandardHistogram(String holeCode, Long itemId) {

        return this.baseMapper.selectStandardHistogram(holeCode,itemId);
    }

    /**
     * 查询钻孔剖面图
     *
     * @author 张童
     * @Date 2020-09-21
     */
    @Override
    public List<StandardResult> selectStandardsHistogram(String holeCode, Long itemId) {
        //钻孔编号
        String[] strings = holeCode.split(",");
        return this.baseMapper.selectStandardsHistogram(strings,itemId);
    }
}
