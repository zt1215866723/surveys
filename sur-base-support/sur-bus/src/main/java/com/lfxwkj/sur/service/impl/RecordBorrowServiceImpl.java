package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.auth.context.LoginContextHolder;
import com.lfxwkj.sur.auth.model.LoginUser;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.ItemSub;
import com.lfxwkj.sur.entity.RecordBorrow;
import com.lfxwkj.sur.mapper.ItemSubMapper;
import com.lfxwkj.sur.mapper.RecordBorrowMapper;
import com.lfxwkj.sur.model.params.RecordBorrowParam;
import com.lfxwkj.sur.model.result.RecordBorrowResult;
import  com.lfxwkj.sur.service.RecordBorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 借阅记录表 服务实现类
 * </p>
 *
 * @author lizheng
 * @since 2020-09-08
 */
@Service
public class RecordBorrowServiceImpl extends ServiceImpl<RecordBorrowMapper, RecordBorrow> implements RecordBorrowService {

    @Autowired
    private ItemSubMapper itemSubMapper;

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void add(RecordBorrowParam param){
        LoginUser user = LoginContextHolder.getContext().getUser();
        param.setAdministrator(user.getId());
        RecordBorrow entity = getEntity(param);
        this.save(entity);
        ItemSub itemSub = new ItemSub();
        itemSub.setId(param.getDocumentId());
        itemSub.setIsBorrow(1);
        itemSubMapper.updateById(itemSub);
    }

    @Override
    public void delete(RecordBorrowParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(RecordBorrowParam param){
        RecordBorrow oldEntity = getOldEntity(param);
        RecordBorrow newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public RecordBorrowResult findBySpec(RecordBorrowParam param){
        return null;
    }

    @Override
    public List<RecordBorrowResult> findListBySpec(RecordBorrowParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(RecordBorrowParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public RecordBorrowResult getDetail(Long id) {
        return this.baseMapper.getDetail(id);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void returnItem(RecordBorrowParam recordBorrowParam) {
        RecordBorrow oldEntity = getOldEntity(recordBorrowParam);
        RecordBorrow newEntity = getEntity(recordBorrowParam);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
        ItemSub itemSub = new ItemSub();
        itemSub.setId(recordBorrowParam.getDocumentId());
        itemSub.setIsBorrow(0);
        itemSubMapper.updateById(itemSub);
    }

    private Serializable getKey(RecordBorrowParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private RecordBorrow getOldEntity(RecordBorrowParam param) {
        return this.getById(getKey(param));
    }

    private RecordBorrow getEntity(RecordBorrowParam param) {
        RecordBorrow entity = new RecordBorrow();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
