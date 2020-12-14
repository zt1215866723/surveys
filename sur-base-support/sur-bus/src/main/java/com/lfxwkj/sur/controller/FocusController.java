package com.lfxwkj.sur.controller;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Focus;
import com.lfxwkj.sur.model.params.FocusParam;
import com.lfxwkj.sur.model.params.ItemSubParam;
import com.lfxwkj.sur.model.result.IndexResult;
import com.lfxwkj.sur.service.FocusService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * 关注项控制器
 *
 * @author lizheng
 * @Date 2020-08-19 11:19:55
 */
@Controller
@RequestMapping("/focus")
public class FocusController extends BaseController {

    private String PREFIX = "/focus";

    @Autowired
    private FocusService focusService;

    /**
     * 跳转到主页面
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/focus.html";
    }

    /**
     * 新增页面
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/focus_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/focus_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(FocusParam focusParam) {
        this.focusService.add(focusParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(FocusParam focusParam) {
        this.focusService.update(focusParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(FocusParam focusParam) {
        this.focusService.delete(focusParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(FocusParam focusParam) {
        Focus detail = this.focusService.getById(focusParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(FocusParam focusParam) {
        LayuiPageInfo pageBySpec = this.focusService.findPageBySpec(focusParam);
        return pageBySpec;
    }

    /**
     * 查询列表
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @ResponseBody
    @RequestMapping("/getList")
    public ResponseData getList(FocusParam focusParam) {
         List<Focus> list = this.focusService.getList(focusParam);
         return ResponseData.success(list);
    }


    /**
     * 查询某个工程的关注项
     *
     * @author zt
     * @Date 2020-11-27
     */
    @ResponseBody
    @RequestMapping("/selectFocusByitemId")
    public ResponseData selectFocusByitemId(ItemSubParam itemSubParam) {
        List<IndexResult> list = this.focusService.selectFocusByitemId(itemSubParam);
        return ResponseData.success(list);
    }

    /**
     * 查询地图显示关注项列表
     *
     * @author zt
     * @Date 2020-11-27
     */
    @ResponseBody
    @RequestMapping("/selectFocusInMap")
    public ResponseData selectFocusInMap() {
        List<Focus> list = this.focusService.selectFocusInMap();
        return ResponseData.success(list);
    }
}


