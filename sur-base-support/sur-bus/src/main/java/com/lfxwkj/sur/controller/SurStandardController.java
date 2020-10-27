package com.lfxwkj.sur.controller;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.SurStandard;
import com.lfxwkj.sur.model.params.SurStandardParam;
import com.lfxwkj.sur.service.SurStandardService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 所有工程土层标准控制器
 *
 * @author 张童
 * @Date 2020-10-22 10:12:47
 */
@Controller
@RequestMapping("/surStandard")
public class SurStandardController extends BaseController {

    private String PREFIX = "/surStandard";

    @Autowired
    private SurStandardService surStandardService;

    /**
     * 跳转到主页面
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/surStandard.html";
    }

    /**
     * 新增页面
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/surStandard_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/surStandard_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 王南翔
     * @Date 2020-10-23
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(SurStandardParam surStandardParam) {
        this.surStandardService.add(surStandardParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(SurStandardParam surStandardParam) {
        this.surStandardService.update(surStandardParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(SurStandardParam surStandardParam) {
        this.surStandardService.delete(surStandardParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(SurStandardParam surStandardParam) {
        SurStandard detail = this.surStandardService.getById(surStandardParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(SurStandardParam surStandardParam) {
        return this.surStandardService.findPageBySpec(surStandardParam);
    }

}


