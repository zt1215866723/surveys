package com.lfxwkj.sur.controller;

import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.StaticTest;
import com.lfxwkj.sur.model.params.StaticTestParam;
import com.lfxwkj.sur.model.result.StaticTestResult;
import com.lfxwkj.sur.service.StaticTestService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 静探表控制器
 *
 * @author 张童
 * @Date 2020-09-16 11:08:41
 */
@Controller
@RequestMapping("/staticTest")
public class StaticTestController extends BaseController {

    private String PREFIX = "/staticTest";

    @Autowired
    private StaticTestService staticTestService;

    /**
     * 跳转到主页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/staticTest.html";
    }

    /**
     * 新增页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/staticTest_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/staticTest_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(StaticTestParam staticTestParam) {
        this.staticTestService.add(staticTestParam);
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
    public ResponseData editItem(StaticTestParam staticTestParam) {
        this.staticTestService.update(staticTestParam);
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
    public ResponseData delete(StaticTestParam staticTestParam) {
        this.staticTestService.delete(staticTestParam);
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
    public ResponseData detail(StaticTestParam staticTestParam) {
        StaticTest detail = this.staticTestService.getById(staticTestParam.getId());
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
    public LayuiPageInfo list(StaticTestParam staticTestParam) {
        return this.staticTestService.findPageBySpec(staticTestParam);
    }

    /**
     * 静力触探图
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @ResponseBody
    @RequestMapping("/staticTestCharts")
    public ResponseData staticTestCharts(StaticTestParam staticTestParam) {
        List<StaticTest> staticTestResults = this.staticTestService.staticTestChart(staticTestParam);
        return ResponseData.success(staticTestResults);
    }

    /**
     * 跳转到主页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/staticTestChart")
    public String staticTestChart() {
        return PREFIX + "/staticTestChart.html";
    }

}


