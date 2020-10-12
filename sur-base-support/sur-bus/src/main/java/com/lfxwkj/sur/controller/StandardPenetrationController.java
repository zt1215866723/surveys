package com.lfxwkj.sur.controller;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.StandardPenetration;
import com.lfxwkj.sur.model.params.StandardPenetrationParam;
import com.lfxwkj.sur.service.StandardPenetrationService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 标贯数据控制器
 *
 * @author 张童
 * @Date 2020-09-16 11:08:41
 */
@Controller
@RequestMapping("/standardPenetration")
public class StandardPenetrationController extends BaseController {

    private String PREFIX = "/standardPenetration";

    @Autowired
    private StandardPenetrationService standardPenetrationService;

    /**
     * 跳转到主页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/standardPenetration.html";
    }

    /**
     * 新增页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/standardPenetration_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/standardPenetration_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 张童
     * @Date 2020-09-16
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(StandardPenetrationParam standardPenetrationParam) {
        this.standardPenetrationService.add(standardPenetrationParam);
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
    public ResponseData editItem(StandardPenetrationParam standardPenetrationParam) {
        this.standardPenetrationService.update(standardPenetrationParam);
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
    public ResponseData delete(StandardPenetrationParam standardPenetrationParam) {
        this.standardPenetrationService.delete(standardPenetrationParam);
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
    public ResponseData detail(StandardPenetrationParam standardPenetrationParam) {
        StandardPenetration detail = this.standardPenetrationService.getById(standardPenetrationParam.getId());
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
    public LayuiPageInfo list(StandardPenetrationParam standardPenetrationParam) {
        return this.standardPenetrationService.findPageBySpec(standardPenetrationParam);
    }

}


