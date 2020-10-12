package com.lfxwkj.sur.core.quartz.controller;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.core.quartz.entity.SysTask;
import com.lfxwkj.sur.core.quartz.model.params.SysTaskParam;
import com.lfxwkj.sur.core.quartz.service.SysTaskService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器
 *
 * @author 郭晓东
 * @Date 2020-07-14 14:34:55
 */
@Controller
@RequestMapping("/sysTask")
public class SysTaskController extends BaseController {

    private String PREFIX = "/sysTask";

    @Autowired
    private SysTaskService sysTaskService;

    /**
     * 跳转到主页面
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/sysTask.html";
    }

    /**
     * 新增页面
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/sysTask_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/sysTask_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(SysTaskParam sysTaskParam) {
        this.sysTaskService.add(sysTaskParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(SysTaskParam sysTaskParam) {
        this.sysTaskService.update(sysTaskParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(SysTaskParam sysTaskParam) {
        this.sysTaskService.delete(sysTaskParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(SysTaskParam sysTaskParam) {
        SysTask detail = this.sysTaskService.getById(sysTaskParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 郭晓东
     * @Date 2020-07-14
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(SysTaskParam sysTaskParam) {
        return this.sysTaskService.findPageBySpec(sysTaskParam);
    }

}


