package com.lfxwkj.sur.controller;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Index;
import com.lfxwkj.sur.model.params.IndexParam;
import com.lfxwkj.sur.model.params.ItemSubParam;
import com.lfxwkj.sur.model.result.IndexResult;
import com.lfxwkj.sur.model.result.ItemResult;
import com.lfxwkj.sur.service.IndexService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 索引信息控制器
 *
 * @author lizheng
 * @Date 2020-08-19 11:19:56
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    private String PREFIX = "/index";

    @Autowired
    private IndexService indexService;

    /**
     * 跳转到主页面
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/index.html";
    }

    /**
     * 新增页面
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/index_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/index_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(IndexParam indexParam) {
        this.indexService.add(indexParam);
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
    public ResponseData editItem(IndexParam indexParam) {
        this.indexService.update(indexParam);
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
    public ResponseData delete(IndexParam indexParam) {
        this.indexService.delete(indexParam);
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
    public ResponseData detail(IndexParam indexParam) {
        Index detail = this.indexService.getById(indexParam.getId());
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
    public LayuiPageInfo list(IndexParam indexParam) {
        return this.indexService.findPageBySpec(indexParam);
    }

    /**
     * 地图页根据关注项查询工程
     *
     * @author zt
     * @Date 2020-12-10
     */
    @ResponseBody
    @RequestMapping("/selectItemByFocusId")
    public ResponseData selectItemByFocusId(IndexParam indexParam) {
        List<ItemResult> list = this.indexService.selectItemByFocusId(indexParam);
        return ResponseData.success(list);
    }
}


