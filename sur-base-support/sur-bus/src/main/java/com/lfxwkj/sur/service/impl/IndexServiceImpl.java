package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Index;
import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.mapper.IndexMapper;
import com.lfxwkj.sur.mapper.ItemMapper;
import com.lfxwkj.sur.mapper.ItemSubMapper;
import com.lfxwkj.sur.model.params.IndexParam;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.result.IndexResult;
import com.lfxwkj.sur.model.result.ItemResult;
import  com.lfxwkj.sur.service.IndexService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ItemSubMapper itemSubMapper;

    @Autowired
    private ItemMapper itemMapper;

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

    @Override
    public List<ItemResult> selectItemByFocusId(IndexParam indexParam) {
        List<ItemResult> itemResultList = null;
        //先查这个关注项对应哪些文档
        List<String> list = this.baseMapper.selectIndexByFocusId(indexParam.getFocusId());
        //根据文档Id们查询工程
        if (list.size()>0){
            List<String> list1 = itemSubMapper.selectSubByIndexId(list);
            //再根据工程id们查询工程信息
            ItemParam itemParam = new ItemParam();
            itemParam.setItemIds(list1);
            itemResultList = itemMapper.getItemOnTheMapByGZ(itemParam);
        }
        return itemResultList;
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
