package com.lfxwkj.sur.controller;

import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.result.ItemResult;
import com.lfxwkj.sur.service.ItemService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 项目表控制器
 *
 * @author 郭晓东
 * @Date 2020-08-18 11:54:07
 */
@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {

    private String PREFIX = "/item";

    @Autowired
    private ItemService itemService;

    /**
     * 跳转到主页面
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/item.html";
    }

    /**
     * 新增页面
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/item_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/item_edit.html";
    }

    /**
     * 工程文档界面
     */
    @RequestMapping("/document")
    public String document() {
        return PREFIX + "/document.html";
    }

    /**
     * 获取文档树列表
     * @param itemId
     * @return
     */
    @RequestMapping("/getTree")
    @ResponseBody
    public List<LayuiTreeNode> getTree(Long itemId) {
        List<LayuiTreeNode> list = itemService.getTree(itemId);
        DefaultTreeBuildFactory<LayuiTreeNode> treeBuildFactory = new DefaultTreeBuildFactory<>();
        treeBuildFactory.setRootParentId("-1");
        return treeBuildFactory.doTreeBuild(list);
    }

    /**
     * 新增接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ItemParam itemParam) {
        if(this.checkRepeat(itemParam) != null){
            return ResponseData.error("该项目已存在");
        }else{
            this.itemService.add(itemParam);
        }
        return ResponseData.success();
    }

    /**
     * 理正数据同步
     */
    @RequestMapping("/synchronous")
    @ResponseBody
    public ResponseData synchronous(Long itemId, int isDataCover, int isItemCover) throws Exception {
        ResponseData responseData = itemService.synchronous(itemId, isDataCover, isItemCover);
        return responseData;
    }

    /**
     * 编辑接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ItemParam itemParam) {
        Item item = this.itemService.getById(itemParam.getId());
        if(!item.getItemName().equals(itemParam.getItemName())){
            if(this.checkRepeat(itemParam) != null){
                return ResponseData.error("该项目已存在");
            }
        }
        this.itemService.update(itemParam);
        return ResponseData.success();
    }

    /**
     * 验重
     * @param itemParam
     * @return
     */
    private Item checkRepeat(ItemParam itemParam){
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.ne("state", 1);
        itemQueryWrapper.eq("item_name", itemParam.getItemName());
        itemQueryWrapper.last("limit 1");
        return this.itemService.getOne(itemQueryWrapper);
    }

    /**
     * 删除接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ItemParam itemParam) {
        this.itemService.delete(itemParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ItemParam itemParam) {
        Item detail = this.itemService.getById(itemParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ItemParam itemParam) {
        return this.itemService.findPageBySpec(itemParam);
    }

    /**
     * 查询列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @ResponseBody
    @RequestMapping("/getList")
    public ResponseData getList(ItemParam itemParam) {
        List<Item> list = this.itemService.getList(itemParam);
        return ResponseData.success(list);
    }

    /**
     * @Description  ：在地图上展示所有工程信息
     * @methodName   : getItemOnTheMap
     * @param        : * @param itemParam :
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/getItemOnTheMap")
    public ResponseData getItemOnTheMap(ItemParam itemParam) {
        List<ItemResult> list = this.itemService.getItemOnTheMap(itemParam);
        return ResponseData.success(list);
    }

    /**
     * 详情页面
     *
     * @author zt
     * @Date 2020-09-22
     */
    @RequestMapping("/itemDetail")
    public String itemDetail() {
        return PREFIX + "/item_detail.html";
    }

    /**
     * 查看详情接口
     *
     * @author zt
     * @Date 2020-09-23
     */
    @RequestMapping("/getItemDetail")
    @ResponseBody
    public ResponseData getItemDetail(ItemParam itemParam) {
        ItemResult detail = this.itemService.getItemDetail(itemParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 跳转到地图
     *
     *  @author zt
     *      * @Date 2020-09-22
     */
    @RequestMapping("/map")
    public String map() {
        return PREFIX + "/itemMap.html";
    }

    /**
     * 详情页跳转地图定位
     *
     *  @author zt
     *      * @Date 2020-09-22
     */
    @RequestMapping("/detailMap")
    public String detailMap() {
        return PREFIX + "/detailMap.html";
    }
}


