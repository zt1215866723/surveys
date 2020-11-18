package com.lfxwkj.sur.util;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

/**
 * @program: survey
 * @description:
 * @author: 张永辉
 * @create: 2020-11-13 11:10
 **/
@Slf4j
public class IOCloseUtils {
    public static void ioClose(Closeable... closeables) {
        //遍历传入需要关闭的流
        for (Closeable closeable:closeables){
            try {
                //判断传入的流是否为空
                if (closeable != null) {
                    closeable.close();//不为空则关闭
                }
            } catch (IOException e) {
                log.error("IO流关闭异常========="+e.getMessage(),e);
            }
        }
    }
}
