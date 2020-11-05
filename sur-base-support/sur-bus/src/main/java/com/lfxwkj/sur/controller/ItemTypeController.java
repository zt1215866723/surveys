package com.lfxwkj.sur.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.ItemType;
import com.lfxwkj.sur.model.params.ItemTypeParam;
import com.lfxwkj.sur.model.result.ItemTypeResult;
import com.lfxwkj.sur.service.ItemTypeService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 项目类型表控制器
 *
 * @author 王南翔
 * @Date 2020-10-29 10:50:22
 */
@Controller
@RequestMapping("/itemType")
public class ItemTypeController extends BaseController {

    private String PREFIX = "/itemType";

    @Value("${img.itemPath}")
    private String path;

    @Autowired
    private ItemTypeService itemTypeService;

    /**
     * 跳转到主页面
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/itemType.html";
    }

    /**
     * 新增页面
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/itemType_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/itemType_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ItemTypeParam itemTypeParam) {
        this.itemTypeService.add(itemTypeParam);
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
    public ResponseData editItem(ItemTypeParam itemTypeParam) {
        this.itemTypeService.update(itemTypeParam);
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
    public ResponseData delete(ItemTypeParam itemTypeParam) {
        this.itemTypeService.delete(itemTypeParam);
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
    public ResponseData detail(ItemTypeParam itemTypeParam) {
        ItemType detail = this.itemTypeService.getById(itemTypeParam.getId());
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
    public LayuiPageInfo list(ItemTypeParam itemTypeParam) {
        return this.itemTypeService.findPageBySpec(itemTypeParam);
    }

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
                String filepaths = uuid + "." + suffix;

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
                pathMap.put("src", filepaths);
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

    /**
     * 查询列表
     *
     * @author zt
     * @Date 2020-11-3
     */
    @ResponseBody
    @RequestMapping("/getItemTypeList")
    public List<ItemType> getItemTypeList(ItemTypeParam itemTypeParam) {
        QueryWrapper<ItemType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",0);
        return this.itemTypeService.list(queryWrapper);
    }
}


