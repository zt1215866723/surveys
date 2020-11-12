package com.lfxwkj.sur.controller;

import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Index;
import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.entity.ItemSub;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.params.ItemSubParam;
import com.lfxwkj.sur.service.ItemSubService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.Query;
import java.util.List;


/**
 * 项目勘察文件表控制器
 *
 * @author 郭晓东
 * @Date 2020-08-18 11:54:07
 */
@Controller
@RequestMapping("/itemSub")
public class ItemSubController extends BaseController {

    private String PREFIX = "/itemSub";

    @Autowired
    private ItemSubService itemSubService;

    /**
     * 跳转到主页面
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/itemSub.html";
    }

    /**
     * 新增页面
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/itemSub_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/itemSub_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ItemSubParam itemSubParam) {
        if(this.checkRepeat(itemSubParam) != null){
            return ResponseData.error("记录已存在,请检查勘察号。");
        }
        this.itemSubService.add(itemSubParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ItemSubParam itemSubParam) {
        ItemSub itemSub = this.itemSubService.getById(itemSubParam.getId());
        if(!itemSub.getSurNum().equals(itemSubParam.getSurNum())){
            if(this.checkRepeat(itemSubParam) != null){
                return ResponseData.error("记录已存在,请检查勘察号。");
            }
        }
        this.itemSubService.update(itemSubParam);
        return ResponseData.success();
    }


    @RequestMapping("/getList")
    @ResponseBody
    public ResponseData getList(ItemSubParam itemSubParam) {
        List<Index> indexList = itemSubService.getList(itemSubParam);
        return ResponseData.success(indexList);
    }

    /**
     * 验重
     * @param itemSubParam
     * @return
     */
    private ItemSub checkRepeat(ItemSubParam itemSubParam){
        if(itemSubParam.getSurNum() == null || itemSubParam.getSurNum().equals("")){
            return null;
        }
        QueryWrapper<ItemSub> itemSubQueryWrapper = new QueryWrapper<>();
        itemSubQueryWrapper.ne("state", 1);
        itemSubQueryWrapper.eq("sur_num", itemSubParam.getSurNum());
        itemSubQueryWrapper.last("limit 1");
        return this.itemSubService.getOne(itemSubQueryWrapper);
    }

    /**
     * 删除接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ItemSubParam itemSubParam) {
        this.itemSubService.delete(itemSubParam);
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
    public ResponseData detail(ItemSubParam itemSubParam) {
        ItemSub detail = this.itemSubService.getById(itemSubParam.getId());
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
    public LayuiPageInfo list(ItemSubParam itemSubParam) {
        return this.itemSubService.findPageBySpec(itemSubParam);
    }

    @RequestMapping("/file")
    public String file() {
        return PREFIX + "/file.html";
    }

    @RequestMapping("/itemSubDetail")
    public String itemSubDetail() {
        return PREFIX + "/itemSubDetail.html";
    }

    /**
     * 获取树列表
     * @param id
     * @return
     */
    @RequestMapping("/getTree")
    @ResponseBody
    public List<LayuiTreeNode> getTree(Long id) {
        List<LayuiTreeNode> list = this.itemSubService.getTree(id);
        return list;
    }


    /*
     * 2020年11月12日
     * 王南翔
     * 添加文档判断项目文档是否存在
     * true 重复  false 不重复
     */
    @RequestMapping("/checkRepeat")
    @ResponseBody
    public Boolean checkRepeatByItemId(ItemSubParam itemSubParam) {
        QueryWrapper<ItemSub> itemSubQueryWrapper = new QueryWrapper<>();
        itemSubQueryWrapper.eq("item_id", itemSubParam.getItemId());
        itemSubQueryWrapper.eq("state", "0");
        return this.itemSubService.getOne(itemSubQueryWrapper) != null;
    }
}


