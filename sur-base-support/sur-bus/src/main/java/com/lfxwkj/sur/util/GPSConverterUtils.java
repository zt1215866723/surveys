package com.lfxwkj.sur.util;

/**
 * 坐标转换工具类
 * WGS84: Google Earth采用，Google Map中国范围外使用
 * GCJ02: 火星坐标系，中国国家测绘局制定的坐标系统，由WGS84机密后的坐标。Google Map中国和搜搜地图使用，高德
 * BD09:百度坐标，GCJ02机密后的坐标系
 * 搜狗坐标系，图吧坐标等，估计也是在GCJ02基础上加密而成的
 * Created by xfkang on 2018/3/28.
 */

import sun.misc.BASE64Decoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.ParseException;

/**
 * 坐标转换工具
 *
 * @author Administrator
 *
 */
public class GPSConverterUtils {
    public static void main(String args[]) throws ParseException {
        double lat = 39.52859194181185;
        double lon = 116.72784199889544;
        System.out.println("haha");
            String aaa=changgeXY("116.7259409189861", "39.52751442826429");
            System.out.println(aaa);
    }

    public static String changgeXY(String xx, String yy) {
        try {
            Socket s = new Socket("api.map.baidu.com", 80);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    s.getInputStream(), "UTF-8"));
            OutputStream out = s.getOutputStream();
            StringBuffer sb = new StringBuffer(
                    "GET /ag/coord/convert?from=0&to=4");
            sb.append("&x=" + xx + "&y=" + yy);
            sb.append("&callback=BMap.Convertor.cbk_3976 HTTP/1.1\r\n");
            sb.append("User-Agent: Java/1.6.0_20\r\n");
            sb.append("Host: api.map.baidu.com:80\r\n");
            sb.append("Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\n");
            sb.append("Connection: Close\r\n");
            sb.append("\r\n");
            out.write(sb.toString().getBytes());
            String json = "";
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                json += tmp;
            }

            int start = json.indexOf("cbk_3976");
            int end = json.lastIndexOf("}");
            if (start != -1 && end != -1&& json.contains("\"x\":\"")) {
                json = json.substring(start, end);
                String[] point = json.split(",");
                String x = point[1].split(":")[1].replace("\"", "");
                String y = point[2].split(":")[1].replace("\"", "");
                return (new String(decode(x)) + "," + new String(decode(y)));
            } else {
                System.out.println("gps坐标无效！！");
            }
            out.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static byte[] decode(String str) {

        byte[] bt = null;

        try {
            BASE64Decoder decoder = new BASE64Decoder();
            bt = decoder.decodeBuffer(str);
            // System.out.println(new String (bt));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bt;
    }
}
