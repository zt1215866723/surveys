package com.lfxwkj.sur.controller;

import cn.hutool.system.SystemUtil;
import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.config.FileUploadConfig;
import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.result.ItemResult;
import com.lfxwkj.sur.service.ItemService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    @Autowired
    private FileUploadConfig fileUploadConfig;

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

    @RequestMapping("/fileUploadPage")
    public String fileUploadPage() {
        return PREFIX + "/file_upload.html";
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
    public ResponseData synchronous(Long itemId, String fileUrl) throws InterruptedException {
        ResponseData responseData = itemService.synchronous(itemId, fileUrl);
        Thread.sleep(800);
        return responseData;
    }

    /**
     * 理正文件上传
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/fileUpload")
    public ResponseData fileUpload(MultipartFile file, Long itemId) throws IOException {
        Item item = itemService.getById(itemId);
        String savePath = getFilePath();
        savePath = savePath + item.getItemName();
        savePath = savePath.replaceAll("& #40;","(").replaceAll("& #41;",")").replaceAll("& #39;","'");
        File localFile = new File(savePath, file.getOriginalFilename());
        //判断文件父目录是否存在
        if (!localFile.getParentFile().exists()) {
            localFile.getParentFile().mkdirs();
        }
        String fileName = file.getOriginalFilename();
        //后缀名
        String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length());
        //文件名（不带后缀）
        String caselsh = fileName.substring(0,fileName.lastIndexOf("."));
        //判断文件是否存在
        while(localFile.exists()){
            caselsh += "1";
            localFile = new File(savePath, caselsh + fileTyle);
        }
        file.transferTo(localFile);
        return ResponseData.success(localFile.getPath());
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
        Item item = new Item();
        item.setId(itemParam.getId());
        item.setHits(detail.getHits()+1);
        this.itemService.updateById(item);
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
     * 跳转到地图
     *
     *  @author zt
     *      * @Date 2020-09-22
     */
    @RequestMapping("/map2")
    public String map2() {
        return PREFIX + "/itemMap2.html";
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

    /**
     * 跳转到主页
     *
     *  @author zt
     *      * @Date 2020-09-22
     */
    @RequestMapping("/workplace")
    public String itemIndex() {
        return PREFIX + "/workplace.html";
    }

    /**
     * @Description  ：首页工程echarts图
     * @methodName   : itemECharts
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/itemECharts")
    public ResponseData itemECharts() {
        List<Map<String,String>> result = itemService.itemECharts();
        return ResponseData.success(result);
    }

    /**
     * @Description  ：批量展示或批量取消
     * @methodName   : itemECharts
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/showAndHiddenItems")
    public ResponseData showAndHiddenItems(String ids,Integer type) {
        String[] split = ids.split(",");
        List<Item> list = new ArrayList<>();
        if (type == 0){
            //批量展示
            for (int i = 0; i < split.length; i++) {
                Item item = new Item();
                item.setId(Long.parseLong(split[i]));
                item.setIsShow(1);
                list.add(item);
            }
        } else {
            //批量隐藏
            for (int i = 0; i < split.length; i++) {
                Item item = new Item();
                item.setId(Long.parseLong(split[i]));
                item.setIsShow(0);
                list.add(item);
            }
        }
        itemService.updateBatchById(list);
        return ResponseData.success();
    }

    /**
     * 跳转到主页
     *
     *  @author zt
     *      * @Date 2020-09-22
     */
    @RequestMapping("/home")
    public String homeIndex() {
        return "/home/index.html";
    }

    /**
     * @Description  ：在地图上展示所有工程信息(+关注项)
     * @methodName   : getItemOnTheMap
     * @param        : * @param itemParam :
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/getItemOnTheMapAddGZ")
    public ResponseData getItemOnTheMapAddGZ(ItemParam itemParam) {
        List<ItemResult> list = this.itemService.getItemOnTheMapAddGZ(itemParam);
        return ResponseData.success(list);
    }

    /**
     * 水位对比
     *
     * @author zt
     * @Date 2020-09-22
     */
    @RequestMapping("/waterContrast")
    public String waterContrast() {
        return PREFIX + "/water_contrast.html";
    }
}


