package com.lfxwkj.sur.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 项目勘察文件表
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@Data
public class ItemSubResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 项目主键
     */
    private Long itemId;

    private String itemName;

    /**
     * 勘察种类
     */
    private String itemType;

    private String typeName;

    /**
     * 勘察号
     */
    private String surNum;

    /**
     * 资质等级， 取自数据字典
     */
    private Long surLevel;

    private String levelName;

    /**
     * 勘察阶段，取自数据字典 勘察阶段
     */
    private Long surStage;

    private String stageName;

    /**
     * 报告名称
     */
    private String surName;

    /**
     * 报告编写人
     */
    private String writeName;

    /**
     * 复核人
     */
    private String reviewName;

    /**
     * 审核人
     */
    private String checkName;

    /**
     * 审定人
     */
    private String examName;

    /**
     * 项目负责人
     */
    private String chargeName;

    /**
     * 总工程师
     */
    private String enginName;

    /**
     * 法定代表人
     */
    private String legalName;

    /**
     * 文档完成日期
     */
    private Date writeTime;

    /**
     * 上传人
     */
    private Long addUser;

    /**
     * 上传时间
     */
    private Date addTime;

    /**
     * 状态
     */
    private Integer state;

    private String location;

    private Integer isForeign;

    private Integer isBorrow;

    private String filePath;
}
