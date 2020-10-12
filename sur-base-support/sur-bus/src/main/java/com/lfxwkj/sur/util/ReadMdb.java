package com.lfxwkj.sur.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.Future;

@Component
public class ReadMdb {

    @Async
    public Future<List<Map>> selectData(String saveUrl, String table) throws SQLException {
        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;
        List<Map> mapList = new ArrayList<>();
        try{
            Properties prop = new Properties();
            prop.put("jackcessOpener", "com.lfxwkj.sur.util.CharsetOpener");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            conn = DriverManager.getConnection("jdbc:ucanaccess://" + saveUrl, prop);
            statement = conn.createStatement();
            String sql = "select * from " + table;
            result = statement.executeQuery(sql);
            ResultSetMetaData data = result.getMetaData();
            while (result.next()) {
                Map map = new HashMap();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    //列名
                    String columnName = data.getColumnName(i);
                    String columnValue = result.getString(i);
                    map.put(columnName, columnValue);
                }
                mapList.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            conn.close();
            statement.close();
            result.close();
        }
        return new AsyncResult<List<Map>>(mapList);
    }
}
