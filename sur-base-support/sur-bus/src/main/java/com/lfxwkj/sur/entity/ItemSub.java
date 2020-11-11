package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 项目勘察文件表
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@TableName("sur_item_sub")
public class ItemSub implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 项目主键
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 勘察号
     */
    @TableField("sur_num")
    private String surNum;

    /**
     * 资质等级， 取自数据字典
     */
    @TableField("sur_level")
    private Long surLevel;

    /**
     * 勘察阶段，取自数据字典 勘察阶段
     */
    @TableField("sur_stage")
    private Long surStage;

    /**
     * 报告名称
     */
    @TableField("sur_name")
    private String surName;

    /**
     * 报告编写人
     */
    @TableField("write_name")
    private String writeName;

    /**
     * 复核人
     */
    @TableField("review_name")
    private String reviewName;

    /**
     * 审核人
     */
    @TableField("check_name")
    private String checkName;

    /**
     * 审定人
     */
    @TableField("exam_name")
    private String examName;

    /**
     * 项目负责人
     */
    @TableField("charge_name")
    private String chargeName;

    /**
     * 总工程师
     */
    @TableField("engin_name")
    private String enginName;

    /**
     * 法定代表人
     */
    @TableField("legal_name")
    private String legalName;

    /**
     * 文档完成日期
     */
    @TableField("write_time")
    private Date writeTime;

    /**
     * 上传人
     */
    @TableField("add_user")
    private Long addUser;

    /**
     * 上传时间
     */
    @TableField("add_time")
    private Date addTime;

    /**
     * 状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 存放位置
     */
    @TableField("location")
    private String location;

    /**
     * 是否为外单位提供
     */
    @TableField("is_foreign")
    private Integer isForeign;

    /**
     * 是否借阅 0无 1已借阅
     */
    @TableField("is_borrow")
    private Integer isBorrow;

    /**
     * 是否借阅 0无 1已借阅
     */
    @TableField("file_path")
    private String filePath;
    public Integer getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(Integer isBorrow) {
        this.isBorrow = isBorrow;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(Integer isForeign) {
        this.isForeign = isForeign;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getSurNum() {
        return surNum;
    }

    public void setSurNum(String surNum) {
        this.surNum = surNum;
    }

    public Long getSurLevel() {
        return surLevel;
    }

    public void setSurLevel(Long surLevel) {
        this.surLevel = surLevel;
    }

    public Long getSurStage() {
        return surStage;
    }

    public void setSurStage(Long surStage) {
        this.surStage = surStage;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getWriteName() {
        return writeName;
    }

    public void setWriteName(String writeName) {
        this.writeName = writeName;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public String getEnginName() {
        return enginName;
    }

    public void setEnginName(String enginName) {
        this.enginName = enginName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public Date getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
    }

    public Long getAddUser() {
        return addUser;
    }

    public void setAddUser(Long addUser) {
        this.addUser = addUser;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFilePath() {
        return filePath;
    }

    public ItemSub setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    @Override
    public String toString() {
        return "ItemSub{" +
        "id=" + id +
        ", itemId=" + itemId +
        ", surNum=" + surNum +
        ", surLevel=" + surLevel +
        ", surStage=" + surStage +
        ", surName=" + surName +
        ", writeName=" + writeName +
        ", reviewName=" + reviewName +
        ", checkName=" + checkName +
        ", examName=" + examName +
        ", chargeName=" + chargeName +
        ", enginName=" + enginName +
        ", legalName=" + legalName +
        ", writeTime=" + writeTime +
        ", addUser=" + addUser +
        ", addTime=" + addTime +
        ", state=" + state +
        "}";
    }
}
