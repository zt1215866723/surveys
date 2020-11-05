package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.*;
import com.lfxwkj.sur.mapper.*;
import com.lfxwkj.sur.model.params.IndexParam;
import com.lfxwkj.sur.model.result.IndexResult;
import com.lfxwkj.sur.service.ConsoleService;
import com.lfxwkj.sur.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 索引信息 服务实现类
 * </p>
 *
 * @author zt
 * @since 2020-11-04
 */
@Service
public class ConsoleServiceImpl implements ConsoleService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private DrillingMapper drillingMapper;
    @Autowired
    private SampleMapper sampleMapper;
    @Autowired
    private StandardPenetrationMapper standardPenetrationMapper;

    @Override
    public List<Map<String, String>> itemsByType() {
        //四个分类：勘察工程；测量工程；岩土设计；其他工程。（先写死吧，首页展示没发动态啊）
        List<Map<String, String>> result = new ArrayList<>();
        //勘察工程
        String count1 = itemMapper.getCountByType(2L);
        Map<String, String> a1 = new HashMap<>();
        a1.put("name", "勘察工程");
        a1.put("value", count1);
        result.add(a1);
        //测量工程
        String count2 = itemMapper.getCountByType(4L);
        Map<String, String> a2 = new HashMap<>();
        a2.put("name", "测量工程");
        a2.put("value", count2);
        result.add(a2);
        //岩土设计
        String count3 = itemMapper.getCountByType(3L);
        String count4 = itemMapper.getCountByType(5L);
        Integer integer = Integer.parseInt(count3)+Integer.parseInt(count4);
        Map<String, String> a3 = new HashMap<>();
        a3.put("name", "岩土设计");
        a3.put("value", integer.toString());
        result.add(a3);
        //其他工程
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",0);
        Integer count5 = itemMapper.selectCount(queryWrapper);
        count5 = count5 - integer - Integer.parseInt(count1)+Integer.parseInt(count2);
        Map<String, String> a = new HashMap<>();
        a.put("name", "其他工程");
        a.put("value", count5.toString());
        result.add(a);
        return result;
    }

    @Override
    public List<Map<String, String>> drillingsByType() {
        //四个分类：钻孔总数量；原状样数量；扰动量数量；标贯数量。
        List<Map<String, String>> result = new ArrayList<>();
        //钻孔总数量
        QueryWrapper<Drilling> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("zksfcy",1);
        Integer count1 = drillingMapper.selectCount(queryWrapper1);
        Map<String, String> a1 = new HashMap<>();
        a1.put("name", "钻孔总数量");
        a1.put("value", count1.toString());
        result.add(a1);
        //原状样数量
        QueryWrapper<Sample> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("qylx",0);
        Integer count2 = sampleMapper.selectCount(queryWrapper2);
        Map<String, String> a2 = new HashMap<>();
        a2.put("name", "原状样数量");
        a2.put("value", count2.toString());
        result.add(a2);
        //扰动量数量
        QueryWrapper<Sample> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("qylx",1);
        Integer count3 = sampleMapper.selectCount(queryWrapper3);
        Map<String, String> a3 = new HashMap<>();
        a3.put("name", "扰动量数量");
        a3.put("value", count3.toString());
        result.add(a3);
        //标贯数量
        QueryWrapper<StandardPenetration> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.eq("cy",1);
        Integer count4 = standardPenetrationMapper.selectCount(queryWrapper4);
        Map<String, String> a4 = new HashMap<>();
        a4.put("name", "标贯数量");
        a4.put("value", count4.toString());
        result.add(a4);
        return result;
    }

    @Override
    public List<Item> itemsByProgram(String program) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",0);
        queryWrapper.eq("progress",program);
        List<Item> list = itemMapper.selectList(queryWrapper);
        return list;
    }
}
