package com.lfxwkj.sur.controller;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.model.params.RecordBorrowParam;
import com.lfxwkj.sur.model.result.RecordBorrowResult;
import com.lfxwkj.sur.service.RecordBorrowService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * 借阅记录表控制器
 *
 * @author lizheng
 * @Date 2020-09-08 16:58:40
 */
@Controller
@RequestMapping("/recordBorrow")
public class RecordBorrowController extends BaseController {

    private String PREFIX = "/recordBorrow";

    @Autowired
    private RecordBorrowService recordBorrowService;

    /**
     * 跳转到主页面
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/recordBorrow.html";
    }

    /**
     * 新增页面
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/recordBorrow_add.html";
    }

    /**
     * 归还页面
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    @RequestMapping("/returnPage")
    public String returnPage() {
        return PREFIX + "/recordBorrow_return.html";
    }

    /**
     * 编辑页面
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/recordBorrow_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(RecordBorrowParam recordBorrowParam) {
        this.recordBorrowService.add(recordBorrowParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(RecordBorrowParam recordBorrowParam) {
        this.recordBorrowService.update(recordBorrowParam);
        return ResponseData.success();
    }

    /**
     * 归还
     * @param recordBorrowParam
     * @return
     */
    @RequestMapping("/returnItem")
    @ResponseBody
    public ResponseData returnItem(RecordBorrowParam recordBorrowParam) {
        this.recordBorrowService.returnItem(recordBorrowParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(RecordBorrowParam recordBorrowParam) {
        this.recordBorrowService.delete(recordBorrowParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(RecordBorrowParam recordBorrowParam) {
        RecordBorrowResult detail = this.recordBorrowService.getDetail(recordBorrowParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(RecordBorrowParam recordBorrowParam) {
        if(ToolUtil.isNotEmpty(recordBorrowParam.getTimeLimit())) {
            String[] split = recordBorrowParam.getTimeLimit().split(" - ");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(formatter.parse(split[1]));
                calendar.add(Calendar.DATE,1);
                recordBorrowParam.setEndTime(calendar.getTime());
                recordBorrowParam.setStartTime(formatter.parse(split[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.recordBorrowService.findPageBySpec(recordBorrowParam);
    }

}


