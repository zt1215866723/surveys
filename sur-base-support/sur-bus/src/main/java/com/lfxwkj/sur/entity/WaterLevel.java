package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 水位信息
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@TableName("lz_water_level")
public class WaterLevel implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 工程id
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 钻孔编号
     */
    @TableField("hole_code")
    private String holeCode;

    /**
     * 深度（米）
     */
    @TableField("depth")
    private Double depth;

    /**
     * 是否稳定1是 0不是
     */
    @TableField("swlx")
    private Integer swlx;

    /**
     * 是否为地下水位1是 0不是
     */
    @TableField("swch")
    private Integer swch;

    /**
     * 测水日期
     */
    @TableField("swcsrq")
    private Date swcsrq;

    /**
     * 地下水温(度)
     */
    @TableField("swdxsw")
    private Double swdxsw;

    /**
     * 水位范围
     */
    @TableField("swfw")
    private String swfw;

    /**
     * 地下水类型
     */
    @TableField("swxz")
    private Integer swxz;

    /**
     * 是否参与统计1参与0不参与
     */
    @TableField("cy")
    private Integer cy;


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

    public String getHoleCode() {
        return holeCode;
    }

    public void setHoleCode(String holeCode) {
        this.holeCode = holeCode;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Integer getSwlx() {
        return swlx;
    }

    public void setSwlx(Integer swlx) {
        this.swlx = swlx;
    }

    public Integer getSwch() {
        return swch;
    }

    public void setSwch(Integer swch) {
        this.swch = swch;
    }

    public Date getSwcsrq() {
        return swcsrq;
    }

    public void setSwcsrq(Date swcsrq) {
        this.swcsrq = swcsrq;
    }

    public Double getSwdxsw() {
        return swdxsw;
    }

    public void setSwdxsw(Double swdxsw) {
        this.swdxsw = swdxsw;
    }

    public String getSwfw() {
        return swfw;
    }

    public void setSwfw(String swfw) {
        this.swfw = swfw;
    }

    public Integer getSwxz() {
        return swxz;
    }

    public void setSwxz(Integer swxz) {
        this.swxz = swxz;
    }

    public Integer getCy() {
        return cy;
    }

    public void setCy(Integer cy) {
        this.cy = cy;
    }

    @Override
    public String toString() {
        return "WaterLevel{" +
        "id=" + id +
        ", itemId=" + itemId +
        ", holeCode=" + holeCode +
        ", depth=" + depth +
        ", swlx=" + swlx +
        ", swch=" + swch +
        ", swcsrq=" + swcsrq +
        ", swdxsw=" + swdxsw +
        ", swfw=" + swfw +
        ", swxz=" + swxz +
        ", cy=" + cy +
        "}";
    }
}
