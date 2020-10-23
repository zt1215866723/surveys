package com.lfxwkj.sur.controller;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Sample;
import com.lfxwkj.sur.model.params.SampleParam;
import com.lfxwkj.sur.service.SampleService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 取样数据表控制器
 *
 * @author 张童
 * @Date 2020-10-22 14:58:44
 */
@Controller
@RequestMapping("/sample")
public class SampleController extends BaseController {

    private String PREFIX = "/sample";

    @Autowired
    private SampleService sampleService;

    /**
     * 跳转到主页面
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/sample.html";
    }

    /**
     * 新增页面
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/sample_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/sample_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 张童
     * @Date 2020-10-22
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(SampleParam sampleParam) {
        this.sampleService.add(sampleParam);
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
    public ResponseData editItem(SampleParam sampleParam) {
        this.sampleService.update(sampleParam);
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
    public ResponseData delete(SampleParam sampleParam) {
        this.sampleService.delete(sampleParam);
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
    public ResponseData detail(SampleParam sampleParam) {
        Sample detail = this.sampleService.getById(sampleParam.getId());
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
    public LayuiPageInfo list(SampleParam sampleParam) {
        return this.sampleService.findPageBySpec(sampleParam);
    }

}


