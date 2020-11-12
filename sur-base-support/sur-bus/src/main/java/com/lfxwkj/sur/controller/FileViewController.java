package com.lfxwkj.sur.controller;

import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.lfxwkj.sur.entity.SubDetail;
import com.lfxwkj.sur.service.SubDetailService;
import com.lfxwkj.sur.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@Controller
@RequestMapping("/fileView")
public class FileViewController {

//    @Autowired
//    private DocumentConverter converter;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private SubDetailService subDetailService;

    @Autowired
    private FileUtil fileUtil;
    /**
     * 视图页面
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    @RequestMapping("/viewer")
    public String viewer() {
        return "pdf/web/viewer.html";
    }

    @RequestMapping(value = "/toPdfFile", method = RequestMethod.GET)
    public String toPdfFile(String path) throws IOException {
        String pathName = "";
        if (path != null){
            try {
               pathName = URLDecoder.decode(path , "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pathName = pathName.replaceAll("& #40;","(").replaceAll("& #41;",")").replaceAll("& #39;","'");
        File file = new File(pathName);
        String newAdress = pathName.substring(0, pathName.lastIndexOf("\\"));
        String newFileUrl = pathName.substring(0, pathName.lastIndexOf(".")) + ".pdf";
        //使用response,将pdf文件以流的方式发送的前段
        ServletOutputStream outputStream = response.getOutputStream();
        InputStream in = null;
        File pdfFile = new File(newFileUrl);
        try {
            if (pathName.substring(pathName.lastIndexOf("."), pathName.length()).equals(".pdf")) {
                //本身就是pdf文件
                in = new FileInputStream(file);
            } else if (pdfFile.exists()) {
                //已经有生成好的pdf
                in = new FileInputStream(pdfFile);
            } else {
                //没有生成的pdf文件
                //转换之后文件生成的地址
                File newFile = new File(newAdress);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                //文件转化
//                converter.convert(file).to(new File(newFileUrl)).execute();
                //读取文件
                in = new FileInputStream(new File(newFileUrl));
            }
            // copy文件
            IOUtils.copy(in, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
            outputStream.close();
        }
        return "This is to pdf";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFolder", method = RequestMethod.POST)
    public ResponseData uploadFolder(MultipartFile[] folder) {
        String s = fileUtil.saveMultiFile(folder);
        if (s != null) {
            return ResponseData.success(s);
        } else
            return ResponseData.error("上传失败");
    }

}
