package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 取样数据表
 * </p>
 *
 * @author 张童
 * @since 2020-10-22
 */
@TableName("lz_sample")
public class Sample implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目Id
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 钻孔编号
     */
    @TableField("hole_code")
    private String holeCode;

    /**
     * 取样编号
     */
    @TableField("qybh")
    private String qybh;

    /**
     * 取样顶深度(m)
     */
    @TableField("qysd")
    private Float qysd;

    /**
     * 取样长度(m)
     */
    @TableField("qyhd")
    private Float qyhd;

    /**
     * 所在地层
     */
    @TableField("qydc")
    private String qydc;

    /**
     * 取样类型 0：原状样 1：扰动样
     */
    @TableField("qylx")
    private Integer qylx;

    /**
     * 质量密度ρ(g/cm^3)
     */
    @TableField("qyzlmd")
    private Float qyzlmd;

    /**
     * 土粒比重Gs
     */
    @TableField("qybz")
    private Float qybz;

    /**
     * 含水量ω(%)
     */
    @TableField("qyhsl")
    private Float qyhsl;

    /**
     * 液限ωL(%)
     */
    @TableField("qyyx")
    private Float qyyx;

    /**
     * 塑限ωP(%)
     */
    @TableField("qysx")
    private Float qysx;


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

    public String getQybh() {
        return qybh;
    }

    public void setQybh(String qybh) {
        this.qybh = qybh;
    }

    public Float getQysd() {
        return qysd;
    }

    public void setQysd(Float qysd) {
        this.qysd = qysd;
    }

    public Float getQyhd() {
        return qyhd;
    }

    public void setQyhd(Float qyhd) {
        this.qyhd = qyhd;
    }

    public String getQydc() {
        return qydc;
    }

    public void setQydc(String qydc) {
        this.qydc = qydc;
    }

    public Integer getQylx() {
        return qylx;
    }

    public void setQylx(Integer qylx) {
        this.qylx = qylx;
    }

    public Float getQyzlmd() {
        return qyzlmd;
    }

    public void setQyzlmd(Float qyzlmd) {
        this.qyzlmd = qyzlmd;
    }

    public Float getQybz() {
        return qybz;
    }

    public void setQybz(Float qybz) {
        this.qybz = qybz;
    }

    public Float getQyhsl() {
        return qyhsl;
    }

    public void setQyhsl(Float qyhsl) {
        this.qyhsl = qyhsl;
    }

    public Float getQyyx() {
        return qyyx;
    }

    public void setQyyx(Float qyyx) {
        this.qyyx = qyyx;
    }

    public Float getQysx() {
        return qysx;
    }

    public void setQysx(Float qysx) {
        this.qysx = qysx;
    }

    @Override
    public String toString() {
        return "Sample{" +
        "id=" + id +
        ", itemId=" + itemId +
        ", holeCode=" + holeCode +
        ", qybh=" + qybh +
        ", qysd=" + qysd +
        ", qyhd=" + qyhd +
        ", qydc=" + qydc +
        ", qylx=" + qylx +
        ", qyzlmd=" + qyzlmd +
        ", qybz=" + qybz +
        ", qyhsl=" + qyhsl +
        ", qyyx=" + qyyx +
        ", qysx=" + qysx +
        "}";
    }
}
