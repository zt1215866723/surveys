package com.lfxwkj.sur.controller;

import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.SubDetail;
import com.lfxwkj.sur.model.params.SubDetailParam;
import com.lfxwkj.sur.service.SubDetailService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.lfxwkj.sur.sys.modular.system.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * 文档的目录详情。控制器
 *
 * @author 郭晓东
 * @Date 2020-08-18 11:54:07
 */
@Controller
@RequestMapping("/subDetail")
public class SubDetailController extends BaseController {

    private String PREFIX = "/subDetail";

    @Autowired
    private SubDetailService subDetailService;

    /**
     * 跳转到主页面
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/subDetail.html";
    }

    /**
     * 新增页面
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/subDetail_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/subDetail_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(SubDetailParam subDetailParam) {
        this.subDetailService.add(subDetailParam);
        return ResponseData.success();
    }

    /**
     * 获取树列表
     * @param subId
     * @return
     */
    @RequestMapping("/getTree")
    @ResponseBody
    public List<LayuiTreeNode> getTree(Long subId) {
        List<LayuiTreeNode> list = subDetailService.getTree(subId);
        DefaultTreeBuildFactory<LayuiTreeNode> treeBuildFactory = new DefaultTreeBuildFactory<>();
        treeBuildFactory.setRootParentId("-1");
        return treeBuildFactory.doTreeBuild(list);
    }

    /**
     * 获取资料种类
     * @param itemType
     * @return
     */
    @RequestMapping("/getCatas")
    @ResponseBody
    public ResponseData getCatas(Long itemType) {
        List<Dict> dictList = this.subDetailService.getCatas(itemType);
        return ResponseData.success(dictList);
    }

    /**
     * 编辑接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(SubDetailParam subDetailParam) {
        this.subDetailService.update(subDetailParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(SubDetailParam subDetailParam) {
        this.subDetailService.delete(subDetailParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(SubDetailParam subDetailParam) {
        SubDetail detail = this.subDetailService.getById(subDetailParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(SubDetailParam subDetailParam) {
        return this.subDetailService.findPageBySpec(subDetailParam);
    }

    /**
     * 文件上传
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/fileUpload")
    public ResponseData fileUpload(MultipartFile file, Long subId) throws IOException {
        return this.subDetailService.fileUpload(file, subId);
    }

    /**
     * 文件下载
     * @param subDetailId
     */
    @ResponseBody
    @RequestMapping("/fileDownload")
    public void fileDownload(Long subDetailId) throws IOException {
        SubDetail subDetail =subDetailService.getById(subDetailId);
        String fileUrl = subDetail.getSaveUrl();
        String filename = fileUrl.substring(fileUrl.lastIndexOf("\\"),fileUrl.length());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(filename);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type",type);
        // 设置编码
        String hehe = new String(filename.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
        this.download(fileUrl, response);
    }

    private void download(String fileUrl, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File(fileUrl)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }

}


