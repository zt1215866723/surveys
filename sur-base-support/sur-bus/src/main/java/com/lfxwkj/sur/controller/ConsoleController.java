package com.lfxwkj.sur.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.result.ItemResult;
import com.lfxwkj.sur.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 项目表控制器
 *
 * @author zt
 * @Date 2020-11-16 11:54:07
 */
@Controller
@RequestMapping("/console")
public class ConsoleController extends BaseController {

    private String PREFIX = "/console";

    @Autowired
    private ConsoleService consoleService;

    /**
     * @Description  ：首页工程分类数量查询
     * @methodName   : itemsByType
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/itemsByType")
    public ResponseData itemsByType() {
        List<Map<String,String>> result = consoleService.itemsByType();
        return ResponseData.success(result);
    }

    /**
     * @Description  ：首页钻孔总数量，原状样数量，扰动量数量，标贯数量查询
     * @methodName   : drillingsByType
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/drillingsByType")
    public ResponseData drillingsByType() {
        List<Map<String,String>> result = consoleService.drillingsByType();
        return ResponseData.success(result);
    }

    /**
     * @Description  ：首页工程进度数量查询
     * @methodName   : itemsByProgram
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/itemsByProgram")
    public ResponseData itemsByProgram() {
        List<List<Item>> result = consoleService.itemsByProgram();
        return ResponseData.success(result);
    }

    /**
     * @Description  ：工程排名
     * @methodName   : itemHot
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/itemHot")
    public ResponseData itemHot() {
        List<Item> itemList = consoleService.itemHot();
        return ResponseData.success(itemList);
    }

    /**
     * @Description  ：按照年份查询近四年的勘察总进尺
     * @methodName   : selectByYeartime
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/selectByYeartime")
    public ResponseData selectByYeartime() {
        List<Map<String,Object>> result = consoleService.selectByYeartime();
        return ResponseData.success(result);
    }

    /**
     * @Description  ：按照季度查询近四个季度的勘察总进尺
     * @methodName   : selectBySeasontime
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    @ResponseBody
    @RequestMapping("/selectBySeasontime")
    public ResponseData selectBySeasontime() {
        List<Map<String,Object>> result = consoleService.selectBySeasontime();
        return ResponseData.success(result);
    }
}


