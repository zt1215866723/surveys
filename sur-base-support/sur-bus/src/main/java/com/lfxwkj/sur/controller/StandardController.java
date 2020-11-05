package com.lfxwkj.sur.controller;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Standard;
import com.lfxwkj.sur.model.params.StandardParam;
import com.lfxwkj.sur.model.result.StandardResult;
import com.lfxwkj.sur.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 地层信息控制器
 *
 * @author 张童
 * @Date 2020-09-15 18:00:28
 */
@Controller
@RequestMapping("/standard")
public class StandardController extends BaseController {

    private String PREFIX = "/standard";

    @Autowired
    private StandardService standardService;

    /**
     * 跳转到主页面
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/standard.html";
    }

    /**
     * 新增页面
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/standard_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/standard_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(StandardParam standardParam) {
        this.standardService.add(standardParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(StandardParam standardParam) {
        this.standardService.update(standardParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(StandardParam standardParam) {
        this.standardService.delete(standardParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(StandardParam standardParam) {
        Standard detail = this.standardService.getById(standardParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(StandardParam standardParam) {
        return this.standardService.findPageBySpec(standardParam);
    }

    /**
     * 选择多个地层信息统计查询列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @ResponseBody
    @RequestMapping("/selectStandardByIds")
    public LayuiPageInfo selectStandardByIds(Long itemId) {

        List<StandardResult> standardResults = this.standardService.selectStandardByIds(itemId);
        LayuiPageInfo layuiPageInfo = new LayuiPageInfo();
        layuiPageInfo.setData(standardResults);
        return layuiPageInfo;
    }

    /**
     * 查询单个钻孔柱状图
     *
     * @author 张童
     * @Date 2020-09-21
     */
    @ResponseBody
    @RequestMapping("/selectStandardHistogram")
    public ResponseData selectStandardHistogram(String holeCode,Long itemId) {
        List<StandardResult> standardResults = this.standardService.selectStandardHistogram(holeCode,itemId);
        return ResponseData.success(standardResults);
    }

    /**
     * 查询钻孔剖面图
     *
     * @author 张童
     * @Date 2020-09-21
     */
    @ResponseBody
    @RequestMapping("/selectStandardsHistogram")
    public ResponseData selectStandardsHistogram(String holeCode,Long itemId) {
        List<StandardResult> standardResults = this.standardService.selectStandardsHistogram(holeCode,itemId);
        return ResponseData.success(standardResults);
    }
}


