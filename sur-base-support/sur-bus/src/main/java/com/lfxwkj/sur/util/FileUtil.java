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
        File dir = new File(savePath+"/"+fileSavePath);
        //递归删除文件夹内的文件
        deleteDir(dir);
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

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
