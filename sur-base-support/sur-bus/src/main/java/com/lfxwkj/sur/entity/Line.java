package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 剖线数据表
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@TableName("lz_line")
public class Line implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目id
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 剖线编号
     */
    @TableField("code")
    private String code;

    /**
     * 连接的钻孔编号，逗号隔开
     */
    @TableField("hole_codes")
    private String holeCodes;

    /**
     * 剖线孔间距(m)
     */
    @TableField("pxkj")
    private Double pxkj;

    /**
     * 剖线方位角度(度)
     */
    @TableField("pxfwj")
    private Double pxfwj;

    /**
     * 线不绘制
     */
    @TableField("pxshx")
    private Integer pxshx;

    /**
     * 剖线起始指向地点
     */
    @TableField("pxqfx")
    private String pxqfx;

    /**
     * 剖线终止指向地点
     */
    @TableField("pxzfx")
    private String pxzfx;

    /**
     * 是否删除0否1是
     */
    @TableField("pxsc")
    private Integer pxsc;

    /**
     * 原始工程编号
     */
    @TableField("ysgcbh")
    private String ysgcbh;

    /**
     * 剖线的起始点X坐标(m)
     */
    @TableField("pxqsdx")
    private Double pxqsdx;

    /**
     * 剖线的起始点Y坐标(m)
     */
    @TableField("pxqsdy")
    private Double pxqsdy;

    /**
     * 剖线的终止点X坐标(m)
     */
    @TableField("pxzzdx")
    private Double pxzzdx;

    /**
     * 剖线的终止点Y坐标(m)
     */
    @TableField("pxzzdy")
    private Double pxzzdy;

    @TableField("pxqsdjl")
    private Double pxqsdjl;

    @TableField("pxqsdjd")
    private Double pxqsdjd;

    @TableField("pxzzdjl")
    private Double pxzzdjl;

    @TableField("pxzzdjd")
    private Double pxzzdjd;

    @TableField("zkh")
    private Integer zkh;

    @TableField("zkv")
    private Integer zkv;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHoleCodes() {
        return holeCodes;
    }

    public void setHoleCodes(String holeCodes) {
        this.holeCodes = holeCodes;
    }

    public Double getPxkj() {
        return pxkj;
    }

    public void setPxkj(Double pxkj) {
        this.pxkj = pxkj;
    }

    public Double getPxfwj() {
        return pxfwj;
    }

    public void setPxfwj(Double pxfwj) {
        this.pxfwj = pxfwj;
    }

    public Integer getPxshx() {
        return pxshx;
    }

    public void setPxshx(Integer pxshx) {
        this.pxshx = pxshx;
    }

    public String getPxqfx() {
        return pxqfx;
    }

    public void setPxqfx(String pxqfx) {
        this.pxqfx = pxqfx;
    }

    public String getPxzfx() {
        return pxzfx;
    }

    public void setPxzfx(String pxzfx) {
        this.pxzfx = pxzfx;
    }

    public Integer getPxsc() {
        return pxsc;
    }

    public void setPxsc(Integer pxsc) {
        this.pxsc = pxsc;
    }

    public String getYsgcbh() {
        return ysgcbh;
    }

    public void setYsgcbh(String ysgcbh) {
        this.ysgcbh = ysgcbh;
    }

    public Double getPxqsdx() {
        return pxqsdx;
    }

    public void setPxqsdx(Double pxqsdx) {
        this.pxqsdx = pxqsdx;
    }

    public Double getPxqsdy() {
        return pxqsdy;
    }

    public void setPxqsdy(Double pxqsdy) {
        this.pxqsdy = pxqsdy;
    }

    public Double getPxzzdx() {
        return pxzzdx;
    }

    public void setPxzzdx(Double pxzzdx) {
        this.pxzzdx = pxzzdx;
    }

    public Double getPxzzdy() {
        return pxzzdy;
    }

    public void setPxzzdy(Double pxzzdy) {
        this.pxzzdy = pxzzdy;
    }

    public Double getPxqsdjl() {
        return pxqsdjl;
    }

    public void setPxqsdjl(Double pxqsdjl) {
        this.pxqsdjl = pxqsdjl;
    }

    public Double getPxqsdjd() {
        return pxqsdjd;
    }

    public void setPxqsdjd(Double pxqsdjd) {
        this.pxqsdjd = pxqsdjd;
    }

    public Double getPxzzdjl() {
        return pxzzdjl;
    }

    public void setPxzzdjl(Double pxzzdjl) {
        this.pxzzdjl = pxzzdjl;
    }

    public Double getPxzzdjd() {
        return pxzzdjd;
    }

    public void setPxzzdjd(Double pxzzdjd) {
        this.pxzzdjd = pxzzdjd;
    }

    public Integer getZkh() {
        return zkh;
    }

    public void setZkh(Integer zkh) {
        this.zkh = zkh;
    }

    public Integer getZkv() {
        return zkv;
    }

    public void setZkv(Integer zkv) {
        this.zkv = zkv;
    }

    @Override
    public String toString() {
        return "Line{" +
        "id=" + id +
        ", itemId=" + itemId +
        ", code=" + code +
        ", holeCodes=" + holeCodes +
        ", pxkj=" + pxkj +
        ", pxfwj=" + pxfwj +
        ", pxshx=" + pxshx +
        ", pxqfx=" + pxqfx +
        ", pxzfx=" + pxzfx +
        ", pxsc=" + pxsc +
        ", ysgcbh=" + ysgcbh +
        ", pxqsdx=" + pxqsdx +
        ", pxqsdy=" + pxqsdy +
        ", pxzzdx=" + pxzzdx +
        ", pxzzdy=" + pxzzdy +
        ", pxqsdjl=" + pxqsdjl +
        ", pxqsdjd=" + pxqsdjd +
        ", pxzzdjl=" + pxzzdjl +
        ", pxzzdjd=" + pxzzdjd +
        ", zkh=" + zkh +
        ", zkv=" + zkv +
        "}";
    }
}
