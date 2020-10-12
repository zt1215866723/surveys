package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 静探表
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@TableName("lz_static_test")
public class StaticTest implements Serializable {

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
     * 试探点深度（米）
     */
    @TableField("depth")
    private Double depth;

    /**
     * 静探类型
     */
    @TableField("jtlx")
    private Integer jtlx;

    /**
     * 试验段长（米）
     */
    @TableField("length")
    private Double length;

    /**
     * 比贯入阻力(MPa)
     */
    @TableField("jtbgrzl")
    private Double jtbgrzl;

    /**
     * 锥头阻力(MPa)
     */
    @TableField("jtztzl")
    private Double jtztzl;

    /**
     * 侧壁摩阻力(kPa)
     */
    @TableField("jtcmz")
    private Double jtcmz;

    /**
     * 摩阻比(%)
     */
    @TableField("jtmzb")
    private Double jtmzb;

    /**
     * 孔隙水压力(kPa)
     */
    @TableField("jtkxyl")
    private Double jtkxyl;

    /**
     * 是否参与统计1参与0不参与
     */
    @TableField("cy")
    private Integer cy;

    @TableField("jtbgrzlx")
    private Integer jtbgrzlx;

    @TableField("jtbgrzltjx")
    private Integer jtbgrzltjx;

    @TableField("jtztzlx")
    private Integer jtztzlx;

    @TableField("jtztzltjx")
    private Integer jtztzltjx;

    @TableField("jtcmzx")
    private Integer jtcmzx;

    @TableField("jtcmztjx")
    private Integer jtcmztjx;

    @TableField("jtmzbx")
    private Integer jtmzbx;

    @TableField("jtmzbtjx")
    private Integer jtmzbtjx;

    @TableField("jtkxylx")
    private Integer jtkxylx;

    @TableField("jtkxyltjx")
    private Integer jtkxyltjx;


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

    public Integer getJtlx() {
        return jtlx;
    }

    public void setJtlx(Integer jtlx) {
        this.jtlx = jtlx;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getJtbgrzl() {
        return jtbgrzl;
    }

    public void setJtbgrzl(Double jtbgrzl) {
        this.jtbgrzl = jtbgrzl;
    }

    public Double getJtztzl() {
        return jtztzl;
    }

    public void setJtztzl(Double jtztzl) {
        this.jtztzl = jtztzl;
    }

    public Double getJtcmz() {
        return jtcmz;
    }

    public void setJtcmz(Double jtcmz) {
        this.jtcmz = jtcmz;
    }

    public Double getJtmzb() {
        return jtmzb;
    }

    public void setJtmzb(Double jtmzb) {
        this.jtmzb = jtmzb;
    }

    public Double getJtkxyl() {
        return jtkxyl;
    }

    public void setJtkxyl(Double jtkxyl) {
        this.jtkxyl = jtkxyl;
    }

    public Integer getCy() {
        return cy;
    }

    public void setCy(Integer cy) {
        this.cy = cy;
    }

    public Integer getJtbgrzlx() {
        return jtbgrzlx;
    }

    public void setJtbgrzlx(Integer jtbgrzlx) {
        this.jtbgrzlx = jtbgrzlx;
    }

    public Integer getJtbgrzltjx() {
        return jtbgrzltjx;
    }

    public void setJtbgrzltjx(Integer jtbgrzltjx) {
        this.jtbgrzltjx = jtbgrzltjx;
    }

    public Integer getJtztzlx() {
        return jtztzlx;
    }

    public void setJtztzlx(Integer jtztzlx) {
        this.jtztzlx = jtztzlx;
    }

    public Integer getJtztzltjx() {
        return jtztzltjx;
    }

    public void setJtztzltjx(Integer jtztzltjx) {
        this.jtztzltjx = jtztzltjx;
    }

    public Integer getJtcmzx() {
        return jtcmzx;
    }

    public void setJtcmzx(Integer jtcmzx) {
        this.jtcmzx = jtcmzx;
    }

    public Integer getJtcmztjx() {
        return jtcmztjx;
    }

    public void setJtcmztjx(Integer jtcmztjx) {
        this.jtcmztjx = jtcmztjx;
    }

    public Integer getJtmzbx() {
        return jtmzbx;
    }

    public void setJtmzbx(Integer jtmzbx) {
        this.jtmzbx = jtmzbx;
    }

    public Integer getJtmzbtjx() {
        return jtmzbtjx;
    }

    public void setJtmzbtjx(Integer jtmzbtjx) {
        this.jtmzbtjx = jtmzbtjx;
    }

    public Integer getJtkxylx() {
        return jtkxylx;
    }

    public void setJtkxylx(Integer jtkxylx) {
        this.jtkxylx = jtkxylx;
    }

    public Integer getJtkxyltjx() {
        return jtkxyltjx;
    }

    public void setJtkxyltjx(Integer jtkxyltjx) {
        this.jtkxyltjx = jtkxyltjx;
    }

    @Override
    public String toString() {
        return "StaticTest{" +
        "id=" + id +
        ", itemId=" + itemId +
        ", holeCode=" + holeCode +
        ", depth=" + depth +
        ", jtlx=" + jtlx +
        ", length=" + length +
        ", jtbgrzl=" + jtbgrzl +
        ", jtztzl=" + jtztzl +
        ", jtcmz=" + jtcmz +
        ", jtmzb=" + jtmzb +
        ", jtkxyl=" + jtkxyl +
        ", cy=" + cy +
        ", jtbgrzlx=" + jtbgrzlx +
        ", jtbgrzltjx=" + jtbgrzltjx +
        ", jtztzlx=" + jtztzlx +
        ", jtztzltjx=" + jtztzltjx +
        ", jtcmzx=" + jtcmzx +
        ", jtcmztjx=" + jtcmztjx +
        ", jtmzbx=" + jtmzbx +
        ", jtmzbtjx=" + jtmzbtjx +
        ", jtkxylx=" + jtkxylx +
        ", jtkxyltjx=" + jtkxyltjx +
        "}";
    }
}
