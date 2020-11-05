package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 项目表
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@TableName("sur_item")
public class Item implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 编号
     */
    @TableField("item_code")
    private String itemCode;

    /**
     * 项目名称
     */
    @TableField("item_name")
    private String itemName;

    /**
     * 地点
     */
    @TableField("location")
    private String location;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 项目经度
     */
    @TableField("xaxis")
    private String xaxis;

    /**
     * 项目纬度
     */
    @TableField("yaxis")
    private String yaxis;

    /**
     * 进度
     */
    @TableField("progress")
    private Long progress;

    /**
     * 项目类别
     */
    @TableField("type")
    private Long type;

    /**
     * 负责人
     */
    @TableField("head")
    private String head;

    /**
     * 开始日期
     */
    @TableField("begin_date")
    private Date beginDate;

    /**
     * 结束日期
     */
    @TableField("end_date")
    private Date endDate;

    @TableField("is_foreign")
    private Integer isForeign;

    @TableField("is_show")
    private Integer isShow;

    @TableField("coor_system")
    private Integer coorSystem;

    @TableField("synchronous_state")
    private Integer synchronousState;

    public Integer getCoorSystem() {
        return coorSystem;
    }

    public Item setCoorSystem(Integer coorSystem) {
        this.coorSystem = coorSystem;
        return this;
    }

    public Integer getIsForeign() {
        return isForeign;
    }

    public Item setIsForeign(Integer isForeign) {
        this.isForeign = isForeign;
        return this;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public Item setIsShow(Integer isShow) {
        this.isShow = isShow;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getXaxis() {
        return xaxis;
    }

    public void setXaxis(String xaxis) {
        this.xaxis = xaxis;
    }

    public String getYaxis() {
        return yaxis;
    }

    public void setYaxis(String yaxis) {
        this.yaxis = yaxis;
    }

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getSynchronousState() {
        return synchronousState;
    }

    public void setSynchronousState(Integer synchronousState) {
        this.synchronousState = synchronousState;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", location='" + location + '\'' +
                ", remarks='" + remarks + '\'' +
                ", state=" + state +
                ", xaxis='" + xaxis + '\'' +
                ", yaxis='" + yaxis + '\'' +
                ", progress=" + progress +
                ", type=" + type +
                ", head='" + head + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", isForeign=" + isForeign +
                ", isShow=" + isShow +
                '}';
    }
}
