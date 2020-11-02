package com.lfxwkj.sur.controller;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.DrillingType;
import com.lfxwkj.sur.model.params.DrillingTypeParam;
import com.lfxwkj.sur.service.DrillingTypeService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 钻孔类型表控制器
 *
 * @author 王南翔
 * @Date 2020-10-29 10:50:22
 */
@Controller
@RequestMapping("/drillingType")
public class DrillingTypeController extends BaseController {

    private String PREFIX = "/drillingType";

    @Value("${img.path}")
    private String path;

    @Autowired
    private DrillingTypeService drillingTypeService;

    /**
     * 跳转到主页面
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/drillingType.html";
    }

    /**
     * 新增页面
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/drillingType_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/drillingType_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DrillingTypeParam drillingTypeParam) {
        this.drillingTypeService.add(drillingTypeParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(DrillingTypeParam drillingTypeParam) {
        this.drillingTypeService.update(drillingTypeParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(DrillingTypeParam drillingTypeParam) {
        this.drillingTypeService.delete(drillingTypeParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(DrillingTypeParam drillingTypeParam) {
        DrillingType detail = this.drillingTypeService.getById(drillingTypeParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(DrillingTypeParam drillingTypeParam) {
        return this.drillingTypeService.findPageBySpec(drillingTypeParam);
    }

    /**
     * 图片上传
     *
     * @author 王南翔
     * @Date 2020-11-02
     */
    @ResponseBody
    @RequestMapping("imgUpload")
    public Map upload(MultipartFile file, HttpServletRequest request) {
        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                String suffix = originalName.substring(originalName.lastIndexOf(".") + 1);
                String uuid = UUID.randomUUID() + "";
                String filepath = path + uuid + "." + suffix;


                File files = new File(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String, Object> pathMap = new HashMap<>();
                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("msg", "");
                map.put("data", pathMap);
                pathMap.put("src", filepath);
                return map;
            }

        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        return map;

    }

}


