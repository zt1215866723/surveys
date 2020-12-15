package com.lfxwkj.sur.controller;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.WaterLevel;
import com.lfxwkj.sur.model.params.WaterLevelParam;
import com.lfxwkj.sur.service.WaterLevelService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 水位信息控制器
 *
 * @author 张童
 * @Date 2020-09-16 10:55:41
 */
@Controller
@RequestMapping("/waterLevel")
public class WaterLevelController extends BaseController {

    private String PREFIX = "/waterLevel";

    @Autowired
    private WaterLevelService waterLevelService;

    /**
     * 跳转到主页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/waterLevel.html";
    }

    /**
     * 新增页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/waterLevel_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/waterLevel_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WaterLevelParam waterLevelParam) {
        this.waterLevelService.add(waterLevelParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WaterLevelParam waterLevelParam) {
        this.waterLevelService.update(waterLevelParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WaterLevelParam waterLevelParam) {
        this.waterLevelService.delete(waterLevelParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WaterLevelParam waterLevelParam) {
        WaterLevel detail = this.waterLevelService.getById(waterLevelParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WaterLevelParam waterLevelParam) {
        return this.waterLevelService.findPageBySpec(waterLevelParam);
    }

    @RequestMapping(value = "/updateMs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateMs(String opt,Double depth, String itemId){
        if(opt.equals("-")){
            depth = -depth;
        }
        this.waterLevelService.updateMs(depth, itemId);
        return ResponseData.success();
    }
}


