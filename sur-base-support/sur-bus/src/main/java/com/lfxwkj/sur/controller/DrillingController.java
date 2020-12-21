package com.lfxwkj.sur.controller;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Drilling;
import com.lfxwkj.sur.model.params.DrillingParam;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.result.DrillingResult;
import com.lfxwkj.sur.model.result.DrillingVo;
import com.lfxwkj.sur.model.result.ItemResult;
import com.lfxwkj.sur.service.DrillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 勘探点数据表控制器
 *
 * @author 张童
 * @Date 2020-09-15 18:00:28
 */
@Controller
@RequestMapping("/drilling")
public class DrillingController extends BaseController {

    private String PREFIX = "/drilling";

    @Autowired
    private DrillingService drillingService;

    /**
     * 跳转到主页面
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/drilling.html";
    }

    /**
     * 新增页面
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/drilling_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/drilling_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DrillingParam drillingParam) {
        this.drillingService.add(drillingParam);
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
    public ResponseData editItem(DrillingParam drillingParam) {
        this.drillingService.update(drillingParam);
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
    public ResponseData delete(DrillingParam drillingParam) {
        this.drillingService.delete(drillingParam);
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
    public ResponseData detail(DrillingParam drillingParam) {
        Drilling detail = this.drillingService.getById(drillingParam.getId());
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
    public LayuiPageInfo list(DrillingParam drillingParam) {
        return this.drillingService.findPageBySpec(drillingParam);
    }

    /**
     * 查询钻孔水位列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @ResponseBody
    @RequestMapping("/waterList")
    public LayuiPageInfo waterList(DrillingParam drillingParam) {
        return this.drillingService.findWaterPageBySpec(drillingParam);
    }

//    /**
//     * 选择多个勘探点查询列表
//     *
//     * @author 张童
//     * @Date 2020-09-15
//     */
//    @ResponseBody
//    @RequestMapping("/selectDrillingByIds")
//    public LayuiPageInfo selectDrillingByIds(String holeCode,Long itemId) {
//        List<DrillingResult> drillingResults = this.drillingService.selectDrillingByIds(holeCode,itemId);
//        LayuiPageInfo layuiPageInfo = new LayuiPageInfo();
//        layuiPageInfo.setData(drillingResults);
//        return layuiPageInfo;
//    }

    /**
     * 跳转到主页面
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @RequestMapping("/drillingAndStandard")
    public String drillingAndStandard() {
        return PREFIX + "/drillingAndStandard.html";
    }

    /**
     * 地图上点击某个工程查询该工程的钻孔信息
     *
     * @author 张童
     * @Date 2020-09-17
     */
    @RequestMapping("/selectDrillingByItemId")
    @ResponseBody
    public ResponseData selectDrillingByItemId(DrillingParam drillingParam) {
        List<DrillingVo> drillingVos = this.drillingService.selectDrillingByItemId(drillingParam);
        return ResponseData.success(drillingVos);
    }

    /**
     * 钻孔详情页面
     *
     * @author 张童
     * @Date 2020-09-23
     */
    @RequestMapping("/drillingDetail")
    public String drillingDetail() {
        return PREFIX + "/drillingInfo.html";
    }

    /**
     * 钻孔地图页面
     *
     * @author 张童
     * @Date 2020-09-23
     */
    @RequestMapping("/drillingMap")
    public String drillingMap() {
        return PREFIX + "/drillingMap.html";
    }

    /**
     * 钻孔地图页面
     *
     * @author 张童
     * @Date 2020-09-23
     */
    @RequestMapping("/drillingMap2")
    public String drillingMap2() {
        return PREFIX + "/drillingMap2.html";
    }

    /**
     * @Description  ：首页钻孔echarts图
     * @methodName   : drillingECharts
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/drillingECharts")
    public ResponseData drillingECharts() {
        List<Map<String,String>> result = drillingService.drillingECharts();
        return ResponseData.success(result);
    }

    /**
     * 选中多个钻孔查看‘勘探点一览表’
     *
     * @author 张童
     * @Date 2020-10.22
     */
    @RequestMapping("/selectExplorationPoints")
    @ResponseBody
    public LayuiPageInfo selectExplorationPoints(Long itemId) {
        List<Drilling> drillingList = this.drillingService.selectExplorationPoints(itemId);
                LayuiPageInfo layuiPageInfo = new LayuiPageInfo();
                layuiPageInfo.setData(drillingList);
                return layuiPageInfo;
    }

    /**
     * @Description  ：钻孔柱状折线图
     * @methodName   : drillingECharts
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/drillingCounts")
    public ResponseData drillingCounts(Long itemId,String holeCode) {
        List<Integer> result = drillingService.drillingCounts(itemId,holeCode);
        return ResponseData.success(result);
    }
}