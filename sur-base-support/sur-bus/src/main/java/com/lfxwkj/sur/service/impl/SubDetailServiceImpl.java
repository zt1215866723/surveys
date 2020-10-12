package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.entity.ItemSub;
import com.lfxwkj.sur.entity.SubDetail;
import com.lfxwkj.sur.mapper.ItemMapper;
import com.lfxwkj.sur.mapper.ItemSubMapper;
import com.lfxwkj.sur.mapper.SubDetailMapper;
import com.lfxwkj.sur.model.params.SubDetailParam;
import com.lfxwkj.sur.model.result.SubDetailResult;
import  com.lfxwkj.sur.service.SubDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfxwkj.sur.sys.modular.system.entity.Dict;
import com.lfxwkj.sur.sys.modular.system.mapper.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文档的目录详情。 服务实现类
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@Service
public class SubDetailServiceImpl extends ServiceImpl<SubDetailMapper, SubDetail> implements SubDetailService {

    @Autowired
    private ItemSubMapper itemSubMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public void add(SubDetailParam param){
        param.setSaveUrl(param.getSaveUrl().replaceAll("& #40;","("));
        param.setSaveUrl(param.getSaveUrl().replaceAll("& #41;",")"));
        param.setSaveUrl(param.getSaveUrl().replaceAll("& #39;","'"));
        SubDetail entity = getEntity(param);
        entity.setState(0);
        this.save(entity);
    }

    @Override
    public void delete(SubDetailParam param){
        param.setState(1);
        this.update(param);
    }

    @Override
    public void update(SubDetailParam param){
        SubDetail oldEntity = getOldEntity(param);
        SubDetail newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SubDetailResult findBySpec(SubDetailParam param){
        return null;
    }

    @Override
    public List<SubDetailResult> findListBySpec(SubDetailParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(SubDetailParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public ResponseData fileUpload(MultipartFile file, Long subId) throws IOException {
        ItemSub itemSub = itemSubMapper.selectById(subId);
        Item item = itemMapper.selectById(itemSub.getItemId());
        String folder = "D:\\勘察文档\\" + item.getItemName() + "\\" + itemSub.getSurName();
        File localFile = new File(folder, file.getOriginalFilename());
        //判断文件是否已经存在
        if (localFile.exists()) {
            return ResponseData.error(ResponseData.DEFAULT_ERROR_CODE, "文件名已存在。", localFile.getAbsolutePath());
        }
        //判断文件父目录是否存在
        if (!localFile.getParentFile().exists()) {
            localFile.getParentFile().mkdirs();
        }
        file.transferTo(localFile);
        return ResponseData.success(localFile.getAbsolutePath());
    }

    @Override
    public List<Dict> getCatas(Long itemType) {
        List<Long> dicts = new ArrayList<>();
        if(itemType.equals(1303502789608460289L)){
            dicts.add(1303502318252576770L);
            dicts.add(1303502422384562178L);
            dicts.add(1305779332963622913L);
        }else if(itemType.equals(1303502842829983746L)){
            dicts.add(1303502378092711938L);
            dicts.add(1303513218468532226L);
        }else if(itemType.equals(1303502895028097025L)){
            dicts.add(1303513860343844865L);
            dicts.add(1303513360223424513L);
        }else if(itemType.equals(1303502953022738433L)){
            dicts.add(1303513766580178945L);
            dicts.add(1303513282544914434L);
        }
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.in("dict_id", dicts);
        return dictMapper.selectList(dictQueryWrapper);
    }

    @Override
    public List<LayuiTreeNode> getTree(Long subId) {
        List<LayuiTreeNode> layuiTreeNodeList = new ArrayList<>();
        ItemSub itemSub = itemSubMapper.selectById(subId);
        layuiTreeNodeList = this.baseMapper.getTree(subId);
        LayuiTreeNode layuiTreeNode = new LayuiTreeNode();
        layuiTreeNode.setTitle(itemSub.getSurName());
        layuiTreeNode.setId(subId);
        layuiTreeNode.setPid(-1L);
        layuiTreeNode.setSpread(true);
        layuiTreeNodeList.add(layuiTreeNode);
        return layuiTreeNodeList;
    }

    private Serializable getKey(SubDetailParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private SubDetail getOldEntity(SubDetailParam param) {
        return this.getById(getKey(param));
    }

    private SubDetail getEntity(SubDetailParam param) {
        SubDetail entity = new SubDetail();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
