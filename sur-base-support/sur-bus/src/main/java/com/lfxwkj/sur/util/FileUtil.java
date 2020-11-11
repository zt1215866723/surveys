package com.lfxwkj.sur.util;

import cn.hutool.system.SystemUtil;
import com.lfxwkj.sur.config.FileUploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @program: survey
 * @description:
 * @author: zt
 * @create: 2020-11-09 08:49
 **/
@Component
public class FileUtil {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    /**
     * 在basePath下保存上传的文件夹
     *
     * @param files
     */
    public String saveMultiFile(MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return null;
        }
        String savePath = getFilePath();
        String fileSavePath = files[0].getOriginalFilename().split("/")[0];
        for (MultipartFile file : files) {
            String filePath = savePath + "/" + file.getOriginalFilename();
            makeDir(filePath);
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return fileSavePath;
    }

    /**
     * 确保目录存在，不存在则创建
     *
     * @param filePath
     */
    private static void makeDir(String filePath) {
        if (filePath.lastIndexOf('/') > 0) {
            String dirPath = filePath.substring(0, filePath.lastIndexOf('/'));
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }

    private String getFilePath(){
        String savePath;
        if (SystemUtil.getOsInfo().isWindows()) {
            savePath = fileUploadConfig.getWindows();
        } else {
            savePath = fileUploadConfig.getLinux();
        }
        return savePath;
    }
}
