package com.lfxwkj.sur.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lfxwkj.sur.entity.*;
import com.lfxwkj.sur.mapper.*;
import com.lfxwkj.sur.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 读取access数据库数据
 * @author Administrator
 */
@Component
public class ReadMdb {

    @Autowired
    private ItemService itemService;

    @Async
    public void selectData(Long itemId, String saveUrl, String... tables) throws ParseException, ClassNotFoundException {
        itemService.saveData(itemId, saveUrl, tables);
    }
}
