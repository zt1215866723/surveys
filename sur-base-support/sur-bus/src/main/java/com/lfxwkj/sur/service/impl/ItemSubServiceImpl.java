package com.lfxwkj.sur.service.impl;

import cn.hutool.system.SystemUtil;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.auth.context.LoginContextHolder;
import com.lfxwkj.sur.auth.model.LoginUser;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.config.FileUploadConfig;
import com.lfxwkj.sur.entity.Focus;
import com.lfxwkj.sur.entity.Index;
import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.entity.ItemSub;
import com.lfxwkj.sur.mapper.FocusMapper;
import com.lfxwkj.sur.mapper.IndexMapper;
import com.lfxwkj.sur.mapper.ItemMapper;
import com.lfxwkj.sur.mapper.ItemSubMapper;
import com.lfxwkj.sur.model.params.ItemSubParam;
import com.lfxwkj.sur.model.result.ItemSubResult;
import  com.lfxwkj.sur.service.ItemSubService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfxwkj.sur.sys.modular.system.entity.Dict;
import com.lfxwkj.sur.sys.modular.system.mapper.DictMapper;
import com.lfxwkj.sur.util.TakeFilePathAndName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 项目勘察文件表 服务实现类
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@Service
public class ItemSubServiceImpl extends ServiceImpl<ItemSubMapper, ItemSub> implements ItemSubService {

    @Autowired
    private FocusMapper focusMapper;
    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private TakeFilePathAndName takeFilePathAndName;
    @Autowired
    private FileUploadConfig fileUploadConfig;
    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void add(ItemSubParam param){
        QueryWrapper<Focus> focusQueryWrapper = new QueryWrapper<>();
        focusQueryWrapper.eq("type", 0);
        focusQueryWrapper.select("id");
        List<Focus> focusList = focusMapper.selectList(focusQueryWrapper);
        List<Long> doubleFocusIds = focusList.stream().map(Focus::getId).collect(Collectors.toList());
        LoginUser user = LoginContextHolder.getContext().getUser();
        param.setAddTime(new Date());
        param.setAddUser(user.getId());
        param.setState(0);
        ItemSub entity = getEntity(param);
        this.save(entity);
        for(Map.Entry<Long, String> entry : param.getFocus().entrySet()){
            if(!entry.getValue().equals("")){
                Index index = new Index();
                index.setFocusId(entry.getKey());
                index.setState(0);
                index.setSubId(entity.getId());
                if(doubleFocusIds.contains(entry.getKey())){
                    index.setNouValue(Double.valueOf(entry.getValue()));
                }else{
                    index.setStrValue(entry.getValue());
                }
                indexMapper.insert(index);
            }
        }
    }

    @Override
    public void delete(ItemSubParam param){
        param.setState(1);
        this.update(param);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void update(ItemSubParam param){
        ItemSub oldEntity = getOldEntity(param);
        ItemSub newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
        if(param.getFocus() != null){
            QueryWrapper<Focus> focusQueryWrapper = new QueryWrapper<>();
            focusQueryWrapper.eq("type", 0);
            focusQueryWrapper.select("id");
            List<Focus> focusList = focusMapper.selectList(focusQueryWrapper);
            List<Long> doubleFocusIds = focusList.stream().map(Focus::getId).collect(Collectors.toList());
            QueryWrapper<Index> indexQueryWrapper = new QueryWrapper<>();
            indexQueryWrapper.eq("sub_id", param.getId());
            indexQueryWrapper.in("focus_id", doubleFocusIds);
            indexQueryWrapper.select("id");
            List<Index> indexList = indexMapper.selectList(indexQueryWrapper);
            List<Long> doubleIndexIds = indexList.stream().map(Index::getId).collect(Collectors.toList());
            for(Map.Entry<Long, String> entry : param.getFocus().entrySet()){
                Index index = new Index();
                index.setId(entry.getKey());
                if(doubleIndexIds.contains(entry.getKey())){
                    index.setNouValue(Double.valueOf(entry.getValue()));
                }else{
                    index.setStrValue(entry.getValue());
                }
                indexMapper.updateById(index);
            }
        }
    }

    @Override
    public ItemSubResult findBySpec(ItemSubParam param){
        return null;
    }

    @Override
    public List<ItemSubResult> findListBySpec(ItemSubParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ItemSubParam param){
        Page pageContext = getPageContext();
        if(ToolUtil.isNotEmpty(param.getTimeLimit())) {
            String[] split = param.getTimeLimit().split(" - ");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(formatter.parse(split[1]));
                calendar.add(Calendar.DATE,1);
                param.setEndTime(calendar.getTime());
                param.setStartTime(formatter.parse(split[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public List<Index> getList(ItemSubParam itemSubParam) {
        QueryWrapper<Index> indexQueryWrapper = new QueryWrapper<>();
        indexQueryWrapper.eq("sub_id", itemSubParam.getId());
        List<Index> indexList = indexMapper.selectList(indexQueryWrapper);
        return indexList;
    }

    @Override
    public List<LayuiTreeNode> getTree(Long id) {
        ItemSub itemSub = this.baseMapper.selectById(id);
        //这是需要获取的文件夹路径
        List<LayuiTreeNode> file = takeFilePathAndName.getFile(getFilePath() + "/" + itemSub.getFilePath(), 0);
        List<LayuiTreeNode> layuiTreeNodeList = new ArrayList<>();
        for (LayuiTreeNode layuiTreeNode : file){
            layuiTreeNode.setSpread(true);
        }
        LayuiTreeNode layuiTreeNode = new LayuiTreeNode();
        layuiTreeNode.setTitle(itemSub.getFilePath());
        layuiTreeNode.setId(1L);
        layuiTreeNode.setSpread(true);
        layuiTreeNode.setChildren(file);
        layuiTreeNodeList.add(layuiTreeNode);
        return layuiTreeNodeList;
    }

    private Serializable getKey(ItemSubParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ItemSub getOldEntity(ItemSubParam param) {
        return this.getById(getKey(param));
    }

    private ItemSub getEntity(ItemSubParam param) {
        ItemSub entity = new ItemSub();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    private String getFilePath(){
        String savePath;
        if (SystemUtil.getOsInfo().isWindows()) {
            savePath = fileUploadConfig.getWindows();
        } else {
            savePath = fileUploadConfig.getLinux();
        }
        return savePath;
    }
}
