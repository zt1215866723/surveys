package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 标贯数据
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@TableName("lz_standard_penetration")
public class StandardPenetration implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 工程主键
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 钻孔编号
     */
    @TableField("hole_code")
    private String holeCode;

    /**
     * 试验点底深度（米）
     */
    @TableField("depth")
    private Double depth;

    /**
     * 标贯类型
     */
    @TableField("bglx")
    private Integer bglx;

    /**
     * 特征值
     */
    @TableField("bgtzz")
    private Integer bgtzz;

    /**
     * 杆长（米）
     */
    @TableField("length")
    private Double length;

    /**
     * 一阵击数的长度(m)
     */
    @TableField("bgyzcd")
    private Double bgyzcd;

    /**
     * 一阵击数(击)
     */
    @TableField("bgyzjs")
    private Double bgyzjs;

    /**
     * 标贯击数(击/30cm)
     */
    @TableField("bgjs")
    private Double bgjs;

    /**
     * 标贯修正系数
     */
    @TableField("bgxs")
    private Double bgxs;

    /**
     * 修正后的标贯击数(击/30cm)
     */
    @TableField("bgxzjs")
    private Double bgxzjs;

    /**
     * 修正否
     */
    @TableField("bgsxz")
    private Integer bgsxz;

    /**
     * 是否参与统计1是0否
     */
    @TableField("cy")
    private Integer cy;

    @TableField("bgxjsx")
    private Integer bgxjsx;

    @TableField("bgxjstjx")
    private Integer bgxjstjx;

    @TableField("bgjsx")
    private Integer bgjsx;

    @TableField("bgjstjx")
    private Integer bgjstjx;


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

    public Integer getBglx() {
        return bglx;
    }

    public void setBglx(Integer bglx) {
        this.bglx = bglx;
    }

    public Integer getBgtzz() {
        return bgtzz;
    }

    public void setBgtzz(Integer bgtzz) {
        this.bgtzz = bgtzz;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getBgyzcd() {
        return bgyzcd;
    }

    public void setBgyzcd(Double bgyzcd) {
        this.bgyzcd = bgyzcd;
    }

    public Double getBgyzjs() {
        return bgyzjs;
    }

    public void setBgyzjs(Double bgyzjs) {
        this.bgyzjs = bgyzjs;
    }

    public Double getBgjs() {
        return bgjs;
    }

    public void setBgjs(Double bgjs) {
        this.bgjs = bgjs;
    }

    public Double getBgxs() {
        return bgxs;
    }

    public void setBgxs(Double bgxs) {
        this.bgxs = bgxs;
    }

    public Double getBgxzjs() {
        return bgxzjs;
    }

    public void setBgxzjs(Double bgxzjs) {
        this.bgxzjs = bgxzjs;
    }

    public Integer getBgsxz() {
        return bgsxz;
    }

    public void setBgsxz(Integer bgsxz) {
        this.bgsxz = bgsxz;
    }

    public Integer getCy() {
        return cy;
    }

    public void setCy(Integer cy) {
        this.cy = cy;
    }

    public Integer getBgxjsx() {
        return bgxjsx;
    }

    public void setBgxjsx(Integer bgxjsx) {
        this.bgxjsx = bgxjsx;
    }

    public Integer getBgxjstjx() {
        return bgxjstjx;
    }

    public void setBgxjstjx(Integer bgxjstjx) {
        this.bgxjstjx = bgxjstjx;
    }

    public Integer getBgjsx() {
        return bgjsx;
    }

    public void setBgjsx(Integer bgjsx) {
        this.bgjsx = bgjsx;
    }

    public Integer getBgjstjx() {
        return bgjstjx;
    }

    public void setBgjstjx(Integer bgjstjx) {
        this.bgjstjx = bgjstjx;
    }

    @Override
    public String toString() {
        return "StandardPenetration{" +
        "id=" + id +
        ", itemId=" + itemId +
        ", holeCode=" + holeCode +
        ", depth=" + depth +
        ", bglx=" + bglx +
        ", bgtzz=" + bgtzz +
        ", length=" + length +
        ", bgyzcd=" + bgyzcd +
        ", bgyzjs=" + bgyzjs +
        ", bgjs=" + bgjs +
        ", bgxs=" + bgxs +
        ", bgxzjs=" + bgxzjs +
        ", bgsxz=" + bgsxz +
        ", cy=" + cy +
        ", bgxjsx=" + bgxjsx +
        ", bgxjstjx=" + bgxjstjx +
        ", bgjsx=" + bgjsx +
        ", bgjstjx=" + bgjstjx +
        "}";
    }
}
