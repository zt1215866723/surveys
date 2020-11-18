package com.lfxwkj.sur.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: survey
 * @description:
 * @author: zt
 * @create: 2020-11-13 10:30
 **/
@Slf4j
public class ZipUtil {

    public static void main(String[] args) throws Exception {
        byte[] buffer = new byte[1024];

        // 生成的ZIP文件名为Demo.zip
        String strZipName = "D:/Demo.zip";//生成文件的目录及命令
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipName));
        // 需要同时下载的两个文件result.txt ，source.txt
        File[] file1 = { new File("C:\\Users\\lenovo\\Desktop\\新建文件夹\\1\\1.jpg"), new File("C:\\Users\\lenovo\\Desktop\\新建文件夹\\2\\2.jpg"), new File("C:\\Users\\lenovo\\Desktop\\新建文件夹\\3.jpg")};
        for (int i = 0; i < file1.length; i++)
        {
            FileInputStream fis = new FileInputStream(file1[i]);
            out.putNextEntry(new ZipEntry(file1[i].getName()));
            int len;
            // 读入需要下载的文件的内容，打包到zip文件
            while ((len = fis.read(buffer)) > 0)
            {
                out.write(buffer, 0, len);
            }
            out.closeEntry();
            fis.close();
        }
        out.close();
        System.out.println("生成Demo.zip成功");
    }

    //ZIP文件包压缩下载


    //判断文件目录和文件是否存在 如否则新建
    public static void chenkFile(File file, String path){
        try {
            if (file.exists()){//如果目录存在
                if (!file.isDirectory()){//如果文件不存在
                    file.createNewFile();//创建文件
                }
            }else {//如果目录不存在
                File file1 = new File(path);//创建指定目录文件对象
                file1.mkdirs();//创建目录
                file.createNewFile();//创建文件
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }
    public static void zip(String sourceFileName,String fileName, HttpServletResponse response){
        ZipOutputStream out = null;
        BufferedOutputStream bos = null;
        try {
            //将zip以流的形式输出到前台
            response.setHeader("content-type", "application/octet-stream");
            response.setCharacterEncoding("utf-8");
            // 设置浏览器响应头对应的Content-disposition
            //参数中 testZip 为压缩包文件名，尾部的.zip 为文件后缀
            response.setHeader("Content-disposition",
                    "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso8859-1")+".zip");
            //创建zip输出流
            out = new ZipOutputStream(response.getOutputStream());
            //创建缓冲输出流
            bos = new BufferedOutputStream(out);
            File sourceFile = new File(sourceFileName);
            //调用压缩函数
            compress(out, bos, sourceFile, sourceFile.getName());
            out.flush();
            log.info("压缩完成");
        } catch (Exception e) {
            log.error("ZIP压缩异常："+e.getMessage(),e);
        } finally {
            IOCloseUtils.ioClose(bos,out);
        }
    }

    public static void compress(ZipOutputStream out, BufferedOutputStream bos, File sourceFile, String base){
        FileInputStream fos = null;
        BufferedInputStream bis = null;
        try {
            //如果路径为目录（文件夹）
            if (sourceFile.isDirectory()) {
                //取出文件夹中的文件（或子文件夹）
                File[] flist = sourceFile.listFiles();
                if (flist.length == 0) {//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
                    out.putNextEntry(new ZipEntry(base + "/"));
                } else {//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
                    for (int i = 0; i < flist.length; i++) {
                        compress(out, bos, flist[i], base + "/" + flist[i].getName());
                    }
                }
            } else {//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
                out.putNextEntry(new ZipEntry(base));
                fos = new FileInputStream(sourceFile);
                bis = new BufferedInputStream(fos);

                int tag;
                //将源文件写入到zip文件中
                while ((tag = bis.read()) != -1) {
                    out.write(tag);
                }

                bis.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOCloseUtils.ioClose(bis,fos);
        }
    }


}
