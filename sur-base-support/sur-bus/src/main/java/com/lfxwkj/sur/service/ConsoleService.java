package com.lfxwkj.sur.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Index;
import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.model.params.IndexParam;
import com.lfxwkj.sur.model.result.IndexResult;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 索引信息 服务类
 * </p>
 *
 * @author zt
 * @since 2020-11-04
 */
public interface ConsoleService {
    /**
     * @Description  ：首页工程分类数量查询
     * @methodName   : itemsByType
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    List<Map<String, String>> itemsByType();
    /**
     * @Description  ：首页钻孔总数量，原状样数量，扰动量数量，标贯数量查询
     * @methodName   : drillingsByType
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    List<Map<String, String>> drillingsByType();

    /**
     * @Description  ：首页工程进度数量查询
     * @methodName   : itemsByProgram
     * @return       : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @exception    :
     * @author       : 张童
     */
    List<Item> itemsByProgram(String program);
}
