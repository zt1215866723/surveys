package com.lfxwkj.sur.controller;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.StandardFormation;
import com.lfxwkj.sur.model.params.StandardFormationParam;
import com.lfxwkj.sur.service.StandardFormationService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 工程土层标准控制器
 *
 * @author 张童
 * @Date 2020-09-16 10:55:41
 */
@Controller
@RequestMapping("/standardFormation")
public class StandardFormationController extends BaseController {

    private String PREFIX = "/standardFormation";

    @Autowired
    private StandardFormationService standardFormationService;

    /**
     * 跳转到主页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/standardFormation.html";
    }

    /**
     * 新增页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/standardFormation_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/standardFormation_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(StandardFormationParam standardFormationParam) {
        this.standardFormationService.add(standardFormationParam);
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
    public ResponseData editItem(StandardFormationParam standardFormationParam) {
        this.standardFormationService.update(standardFormationParam);
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
    public ResponseData delete(StandardFormationParam standardFormationParam) {
        this.standardFormationService.delete(standardFormationParam);
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
    public ResponseData detail(StandardFormationParam standardFormationParam) {
        StandardFormation detail = this.standardFormationService.getById(standardFormationParam.getId());
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
    public LayuiPageInfo list(StandardFormationParam standardFormationParam) {
        return this.standardFormationService.findPageBySpec(standardFormationParam);
    }

}


