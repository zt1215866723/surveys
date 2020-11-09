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
        ResultSet result = null;
        Map<String, List<Map>> data = new HashMap<>();
        Properties prop = new Properties();
        prop.put("jackcessOpener", "com.lfxwkj.sur.util.CharsetOpener");
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        boolean sign = true;
        try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + saveUrl, prop);
             Statement statement = conn.createStatement();) {
            for (String table : tables) {
                List<Map> mapList = new ArrayList<>();
                String sql = "select * from " + table;
                result = statement.executeQuery(sql);
                ResultSetMetaData data1 = result.getMetaData();
                while (result.next()) {
                    Map map = new HashMap();
                    for (int i = 1; i <= data1.getColumnCount(); i++) {
                        //列名
                        String columnName = data1.getColumnName(i);
                        String columnValue = result.getString(i);
                        map.put(columnName, columnValue);
                    }
                    mapList.add(map);
                }
                data.put(table, mapList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sign = false;
        }
        itemService.saveData(itemId, sign, data);
    }
}
