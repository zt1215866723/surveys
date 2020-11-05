package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.*;
import com.lfxwkj.sur.mapper.*;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.result.ItemResult;
import com.lfxwkj.sur.model.result.SubDetailResult;
import com.lfxwkj.sur.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfxwkj.sur.sys.modular.system.entity.Dict;
import com.lfxwkj.sur.sys.modular.system.service.DictService;
import com.lfxwkj.sur.util.AsyncResult;
import com.lfxwkj.sur.util.ReadMdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private ItemSubMapper itemSubMapper;
    @Autowired
    private SubDetailMapper subDetailMapper;
    @Autowired
    private DrillingMapper drillingMapper;
    @Autowired
    private DictService dictService;
    @Autowired
    private ReadMdb readMdb;

    @Override
    public void add(ItemParam param) {
        Item entity = getEntity(param);
        entity.setState(0);
        this.save(entity);
    }

    @Override
    public void delete(ItemParam param) {
        param.setState(1);
        this.update(param);
    }

    @Override
    public void update(ItemParam param) {
        Item oldEntity = getOldEntity(param);
        Item newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ItemResult findBySpec(ItemParam param) {
        return null;
    }

    @Override
    public List<ItemResult> findListBySpec(ItemParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ItemParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public List<Item> getList(ItemParam itemParam) {
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("state", 0);
        return this.list(itemQueryWrapper);
    }

    @Override
    public List<LayuiTreeNode> getTree(Long itemId) {
        List<LayuiTreeNode> layuiTreeNodeList = new ArrayList<>();
        QueryWrapper<ItemSub> itemSubQueryWrapper = new QueryWrapper<>();
        itemSubQueryWrapper.eq("item_id", itemId);
        itemSubQueryWrapper.eq("state", 0);
        List<ItemSub> itemSubList = itemSubMapper.selectList(itemSubQueryWrapper);
        for (ItemSub itemSub : itemSubList) {
            List<LayuiTreeNode> layuiTreeNodeList1 = subDetailMapper.getTree(itemSub.getId());
            LayuiTreeNode layuiTreeNode = new LayuiTreeNode();
            layuiTreeNode.setTitle(itemSub.getSurName());
            layuiTreeNode.setId(itemSub.getId());
            layuiTreeNode.setPid(-1L);
            layuiTreeNode.setSpread(true);
            layuiTreeNodeList1.add(layuiTreeNode);
            layuiTreeNodeList.addAll(layuiTreeNodeList1);
        }
        return layuiTreeNodeList;
    }

    @Override
    public ResponseData synchronous(Long itemId, int isDataCover) throws InterruptedException {
        //查询理正数据库文件存放位置
        SubDetailResult subDetailResult = subDetailMapper.getSynchronousFile(itemId);
        if (subDetailResult == null) {
            return ResponseData.error(1, "请先上传理勘察正备份库文件");
        }
        //覆盖
        if (isDataCover == 0) {
            //根据数据库中钻孔表数据判断是否已经导入过理正数据
            QueryWrapper<Drilling> drillingWrapper = new QueryWrapper<>();
            drillingWrapper.eq("item_id", itemId);
            List<Drilling> drillings = drillingMapper.selectList(drillingWrapper);
            if (drillings.size() > 0) {
                return ResponseData.error(4, "该工程下已导入理正数据，是否覆盖？");
            }
        }
        //暂定同步（钻孔，静探，标贯，土层，水位，工程，抛线，土层标准）
        String[] tableNames = {"z_ZuanKong", "z_y_JingTan", "z_y_BiaoGuan", "z_g_TuCeng", "z_g_ShuiWei", "x_GongCheng", "p_PouXian", "g_STuCengGC", "z_c_quyang"};
        try {
            readMdb.selectData(itemId, subDetailResult.getSaveUrl(), tableNames);
        }catch (RejectedExecutionException e){
            e.printStackTrace();
            return ResponseData.error("当前处理任务过多，请稍后重试。");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error("服务异常。");
        }
        Thread.sleep(1000);
        return ResponseData.success();
    }

    private Serializable getKey(ItemParam param) {
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Item getOldEntity(ItemParam param) {
        return this.getById(getKey(param));
    }

    private Item getEntity(ItemParam param) {
        Item entity = new Item();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    /**
     * @param : * @param itemParam :
     * @return : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @throws :
     * @Description ：在地图上展示所有工程信息
     * @methodName : getItemOnTheMap
     * @author : 张童
     */
    @Override
    public List<ItemResult> getItemOnTheMap(ItemParam itemParam) {
        if (itemParam.getItemTypes()!=null && !itemParam.getItemTypes().equals("")) {
            String s1 = itemParam.getItemTypes().substring(0, itemParam.getItemTypes().length() - 1);
            String[] split = s1.split(",");
            itemParam.setTypeArray(split);
        }
        if (itemParam.getItemPlans()!=null && !itemParam.getItemPlans().equals("")) {
            String s2 = itemParam.getItemPlans().substring(0, itemParam.getItemPlans().length() - 1);
            String[] split1 = s2.split(",");
            itemParam.setPlanArray(split1);
        }
        List<ItemResult> itemOnTheMap = this.baseMapper.getItemOnTheMap(itemParam);
        return itemOnTheMap;
    }

    /**
     * 查看详情接口
     *
     * @author zt
     * @Date 2020-09-23
     */
    @Override
    public ItemResult getItemDetail(Long id) {
        return this.baseMapper.getItemDetail(id);
    }

    /**
     * @return : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @throws :
     * @Description ：首页工程echarts图
     * @methodName : itemECharts
     * @author : 张童
     */
    @Override
    public List<Map<String, String>> itemECharts() {
        List<Map<String, String>> result = new ArrayList<>();
        //查一下几类工程
        List<Dict> dicts = dictService.listDicts(1303502589535965185l);
        //分类查数量
        for (Dict d : dicts) {
            //查询每个类型有多少项目
            String count = this.baseMapper.getCountByType(d.getDictId());
            Map<String, String> a = new HashMap<>();
            a.put("name", d.getName());
            a.put("id", d.getDictId().toString());
            a.put("value", count);
            result.add(a);
        }
        return result;
    }
}
