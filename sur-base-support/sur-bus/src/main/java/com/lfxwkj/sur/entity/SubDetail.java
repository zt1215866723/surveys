package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 文档的目录详情。
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@TableName("sur_sub_detail")
public class SubDetail implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 取自数据字典，目录的主键
     */
    @TableField("cata_id")
    private String cataId;

    @TableField("sub_id")
    private Long subId;

    /**
     * 目录的名称
     */
    @TableField("cata_name")
    private String cataName;

    /**
     * 描述
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 文档的页数
     */
    @TableField("page_num")
    private Integer pageNum;

    /**
     * 文档的存放路径
     */
    @TableField("save_url")
    private String saveUrl;

    /**
     * 编制人
     */
    @TableField("prep_name")
    private String prepName;

    /**
     * 复核人
     */
    @TableField("revie_name")
    private String revieName;

    /**
     * 审核人
     */
    @TableField("check_name")
    private String checkName;

    /**
     * 状态
     */
    @TableField("state")
    private Integer state;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCataId() {
        return cataId;
    }

    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getCataName() {
        return cataName;
    }

    public void setCataName(String cataName) {
        this.cataName = cataName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getSaveUrl() {
        return saveUrl;
    }

    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl;
    }

    public String getPrepName() {
        return prepName;
    }

    public void setPrepName(String prepName) {
        this.prepName = prepName;
    }

    public String getRevieName() {
        return revieName;
    }

    public void setRevieName(String revieName) {
        this.revieName = revieName;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    @Override
    public String toString() {
        return "SubDetail{" +
        "id=" + id +
        ", cataId=" + cataId +
        ", cataName=" + cataName +
        ", remarks=" + remarks +
        ", pageNum=" + pageNum +
        ", saveUrl=" + saveUrl +
        ", prepName=" + prepName +
        ", revieName=" + revieName +
        ", checkName=" + checkName +
        ", state=" + state +
        "}";
    }
}
