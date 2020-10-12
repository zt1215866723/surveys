package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 勘探点数据表
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
@TableName("lz_drilling")
public class Drilling implements Serializable {

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
     * 钻孔类型
     */
    @TableField("type")
    private String type;

    /**
     * 横坐标
     */
    @TableField("zkx")
    private Double zkx;

    /**
     * 纵坐标
     */
    @TableField("zky")
    private Double zky;

    /**
     * 建筑地段
     */
    @TableField("jzdd")
    private String jzdd;

    /**
     * 段号
     */
    @TableField("zkdh")
    private String zkdh;

    /**
     * 里程（米）
     */
    @TableField("zklc")
    private Double zklc;

    /**
     * 偏移量(m)
     */
    @TableField("zkpil")
    private Double zkpil;

    /**
     * 孔口高程(m)
     */
    @TableField("zkbg")
    private Double zkbg;

    /**
     * 水面高程(m)
     */
    @TableField("zkhsbg")
    private Double zkhsbg;

    /**
     * 勘探深度(m)
     */
    @TableField("depth")
    private Double depth;

    /**
     * 探井深度(m)
     */
    @TableField("zktjsd")
    private Double zktjsd;

    /**
     * 钻孔直径(mm)
     */
    @TableField("zkzj")
    private Double zkzj;

    /**
     * 断层类型
     */
    @TableField("zkdclc")
    private String zkdclc;

    /**
     * 断层夹角(度)
     */
    @TableField("zkdcjj")
    private Double zkdcjj;

    /**
     * 断层起始深度(m)
     */
    @TableField("zkdcqsd")
    private Double zkdcqsd;

    /**
     * 断层终止深度(m)
     */
    @TableField("zkdczsd")
    private Double zkdczsd;

    /**
     * 断层盘面
     */
    @TableField("zkdcpm")
    private String zkdcpm;

    /**
     * 虚拟钻孔0不是1是
     */
    @TableField("zkxn")
    private String zkxn;

    /**
     * 是否参与统计1是0否
     */
    @TableField("zksfcy")
    private Integer zksfcy;

    /**
     * 已有孔标志
     */
    @TableField("zkjyk")
    private Integer zkjyk;

    /**
     * 地下水温(度)
     */
    @TableField("zkdxsw")
    private Double zkdxsw;

    /**
     * 探槽角度(度)
     */
    @TableField("zktcjd")
    private Double zktcjd;

    /**
     * 勘探开始日期
     */
    @TableField("zkksrq")
    private Date zkksrq;

    /**
     * 勘探终止日期
     */
    @TableField("zkzzrq")
    private Date zkzzrq;

    /**
     * 勘探点等级
     */
    @TableField("zkdj")
    private Integer zkdj;

    /**
     * 土的湿度系数ψw
     */
    @TableField("zkpztsdxs")
    private Double zkpztsdxs;

    /**
     * 大气影响深度(m)
     */
    @TableField("zkpzdqyxsd")
    private Double zkpzdqyxsd;

    /**
     * 备注
     */
    @TableField("bz")
    private String bz;

    @TableField("zksc")
    private Integer zksc;

    @TableField("zkh")
    private Integer zkh;

    @TableField("zkv")
    private Integer zkv;

    @TableField("zkyhzs")
    private String zkyhzs;

    /**
     * 湿陷等级
     */
    @TableField("sxdj")
    private Integer sxdj;

    /**
     * 湿陷类型
     */
    @TableField("sxlx")
    private Integer sxlx;

    /**
     * 膨胀潜势
     */
    @TableField("zkpzqs")
    private Integer zkpzqs;

    /**
     * 膨胀等级
     */
    @TableField("zkpzdj")
    private Integer zkpzdj;

    /**
     * 1m深处含水量ω1
     */
    @TableField("zkymchsl")
    private Double zkymchsl;

    /**
     * 1m深处塑限含水量ω1p
     */
    @TableField("zkymcsxhsl")
    private Double zkymcsxhsl;

    @TableField("dtable")
    private String dtable;

    /**
     * 原始工程编号
     */
    @TableField("ysgcbh")
    private String ysgcbh;

    @TableField("zkcolor")
    private Integer zkcolor;

    /**
     * 总编号
     */
    @TableField("zkzbh")
    private String zkzbh;

    /**
     * 施钻方法
     */
    @TableField("zkszff")
    private String zkszff;

    /**
     * 钻机类型
     */
    @TableField("zkzjlx")
    private String zkzjlx;

    /**
     * 钻探单位
     */
    @TableField("zkztdw")
    private String zkztdw;

    /**
     * 工点名称
     */
    @TableField("zkgdmc")
    private String zkgdmc;

    /**
     * 地面高程
     */
    @TableField("zkdmbg")
    private Double zkdmbg;

    /**
     * 建议基础高程(m)
     */
    @TableField("jyjcgc")
    private Double jyjcgc;

    @TableField("sortkey")
    private Integer sortkey;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getZkx() {
        return zkx;
    }

    public void setZkx(Double zkx) {
        this.zkx = zkx;
    }

    public Double getZky() {
        return zky;
    }

    public void setZky(Double zky) {
        this.zky = zky;
    }

    public String getJzdd() {
        return jzdd;
    }

    public void setJzdd(String jzdd) {
        this.jzdd = jzdd;
    }

    public String getZkdh() {
        return zkdh;
    }

    public void setZkdh(String zkdh) {
        this.zkdh = zkdh;
    }

    public Double getZklc() {
        return zklc;
    }

    public void setZklc(Double zklc) {
        this.zklc = zklc;
    }

    public Double getZkpil() {
        return zkpil;
    }

    public void setZkpil(Double zkpil) {
        this.zkpil = zkpil;
    }

    public Double getZkbg() {
        return zkbg;
    }

    public void setZkbg(Double zkbg) {
        this.zkbg = zkbg;
    }

    public Double getZkhsbg() {
        return zkhsbg;
    }

    public void setZkhsbg(Double zkhsbg) {
        this.zkhsbg = zkhsbg;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getZktjsd() {
        return zktjsd;
    }

    public void setZktjsd(Double zktjsd) {
        this.zktjsd = zktjsd;
    }

    public Double getZkzj() {
        return zkzj;
    }

    public void setZkzj(Double zkzj) {
        this.zkzj = zkzj;
    }

    public String getZkdclc() {
        return zkdclc;
    }

    public void setZkdclc(String zkdclc) {
        this.zkdclc = zkdclc;
    }

    public Double getZkdcjj() {
        return zkdcjj;
    }

    public void setZkdcjj(Double zkdcjj) {
        this.zkdcjj = zkdcjj;
    }

    public Double getZkdcqsd() {
        return zkdcqsd;
    }

    public void setZkdcqsd(Double zkdcqsd) {
        this.zkdcqsd = zkdcqsd;
    }

    public Double getZkdczsd() {
        return zkdczsd;
    }

    public void setZkdczsd(Double zkdczsd) {
        this.zkdczsd = zkdczsd;
    }

    public String getZkdcpm() {
        return zkdcpm;
    }

    public void setZkdcpm(String zkdcpm) {
        this.zkdcpm = zkdcpm;
    }

    public String getZkxn() {
        return zkxn;
    }

    public void setZkxn(String zkxn) {
        this.zkxn = zkxn;
    }

    public Integer getZksfcy() {
        return zksfcy;
    }

    public void setZksfcy(Integer zksfcy) {
        this.zksfcy = zksfcy;
    }

    public Integer getZkjyk() {
        return zkjyk;
    }

    public void setZkjyk(Integer zkjyk) {
        this.zkjyk = zkjyk;
    }

    public Double getZkdxsw() {
        return zkdxsw;
    }

    public void setZkdxsw(Double zkdxsw) {
        this.zkdxsw = zkdxsw;
    }

    public Double getZktcjd() {
        return zktcjd;
    }

    public void setZktcjd(Double zktcjd) {
        this.zktcjd = zktcjd;
    }

    public Date getZkksrq() {
        return zkksrq;
    }

    public void setZkksrq(Date zkksrq) {
        this.zkksrq = zkksrq;
    }

    public Date getZkzzrq() {
        return zkzzrq;
    }

    public void setZkzzrq(Date zkzzrq) {
        this.zkzzrq = zkzzrq;
    }

    public Integer getZkdj() {
        return zkdj;
    }

    public void setZkdj(Integer zkdj) {
        this.zkdj = zkdj;
    }

    public Double getZkpztsdxs() {
        return zkpztsdxs;
    }

    public void setZkpztsdxs(Double zkpztsdxs) {
        this.zkpztsdxs = zkpztsdxs;
    }

    public Double getZkpzdqyxsd() {
        return zkpzdqyxsd;
    }

    public void setZkpzdqyxsd(Double zkpzdqyxsd) {
        this.zkpzdqyxsd = zkpzdqyxsd;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Integer getZksc() {
        return zksc;
    }

    public void setZksc(Integer zksc) {
        this.zksc = zksc;
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

    public String getZkyhzs() {
        return zkyhzs;
    }

    public void setZkyhzs(String zkyhzs) {
        this.zkyhzs = zkyhzs;
    }

    public Integer getSxdj() {
        return sxdj;
    }

    public void setSxdj(Integer sxdj) {
        this.sxdj = sxdj;
    }

    public Integer getSxlx() {
        return sxlx;
    }

    public void setSxlx(Integer sxlx) {
        this.sxlx = sxlx;
    }

    public Integer getZkpzqs() {
        return zkpzqs;
    }

    public void setZkpzqs(Integer zkpzqs) {
        this.zkpzqs = zkpzqs;
    }

    public Integer getZkpzdj() {
        return zkpzdj;
    }

    public void setZkpzdj(Integer zkpzdj) {
        this.zkpzdj = zkpzdj;
    }

    public Double getZkymchsl() {
        return zkymchsl;
    }

    public void setZkymchsl(Double zkymchsl) {
        this.zkymchsl = zkymchsl;
    }

    public Double getZkymcsxhsl() {
        return zkymcsxhsl;
    }

    public void setZkymcsxhsl(Double zkymcsxhsl) {
        this.zkymcsxhsl = zkymcsxhsl;
    }

    public String getDtable() {
        return dtable;
    }

    public void setDtable(String dtable) {
        this.dtable = dtable;
    }

    public String getYsgcbh() {
        return ysgcbh;
    }

    public void setYsgcbh(String ysgcbh) {
        this.ysgcbh = ysgcbh;
    }

    public Integer getZkcolor() {
        return zkcolor;
    }

    public void setZkcolor(Integer zkcolor) {
        this.zkcolor = zkcolor;
    }

    public String getZkzbh() {
        return zkzbh;
    }

    public void setZkzbh(String zkzbh) {
        this.zkzbh = zkzbh;
    }

    public String getZkszff() {
        return zkszff;
    }

    public void setZkszff(String zkszff) {
        this.zkszff = zkszff;
    }

    public String getZkzjlx() {
        return zkzjlx;
    }

    public void setZkzjlx(String zkzjlx) {
        this.zkzjlx = zkzjlx;
    }

    public String getZkztdw() {
        return zkztdw;
    }

    public void setZkztdw(String zkztdw) {
        this.zkztdw = zkztdw;
    }

    public String getZkgdmc() {
        return zkgdmc;
    }

    public void setZkgdmc(String zkgdmc) {
        this.zkgdmc = zkgdmc;
    }

    public Double getZkdmbg() {
        return zkdmbg;
    }

    public void setZkdmbg(Double zkdmbg) {
        this.zkdmbg = zkdmbg;
    }

    public Double getJyjcgc() {
        return jyjcgc;
    }

    public void setJyjcgc(Double jyjcgc) {
        this.jyjcgc = jyjcgc;
    }

    public Integer getSortkey() {
        return sortkey;
    }

    public void setSortkey(Integer sortkey) {
        this.sortkey = sortkey;
    }

    @Override
    public String toString() {
        return "Drilling{" +
        "id=" + id +
        ", itemId=" + itemId +
        ", holeCode=" + holeCode +
        ", type=" + type +
        ", zkx=" + zkx +
        ", zky=" + zky +
        ", jzdd=" + jzdd +
        ", zkdh=" + zkdh +
        ", zklc=" + zklc +
        ", zkpil=" + zkpil +
        ", zkbg=" + zkbg +
        ", zkhsbg=" + zkhsbg +
        ", depth=" + depth +
        ", zktjsd=" + zktjsd +
        ", zkzj=" + zkzj +
        ", zkdclc=" + zkdclc +
        ", zkdcjj=" + zkdcjj +
        ", zkdcqsd=" + zkdcqsd +
        ", zkdczsd=" + zkdczsd +
        ", zkdcpm=" + zkdcpm +
        ", zkxn=" + zkxn +
        ", zksfcy=" + zksfcy +
        ", zkjyk=" + zkjyk +
        ", zkdxsw=" + zkdxsw +
        ", zktcjd=" + zktcjd +
        ", zkksrq=" + zkksrq +
        ", zkzzrq=" + zkzzrq +
        ", zkdj=" + zkdj +
        ", zkpztsdxs=" + zkpztsdxs +
        ", zkpzdqyxsd=" + zkpzdqyxsd +
        ", bz=" + bz +
        ", zksc=" + zksc +
        ", zkh=" + zkh +
        ", zkv=" + zkv +
        ", zkyhzs=" + zkyhzs +
        ", sxdj=" + sxdj +
        ", sxlx=" + sxlx +
        ", zkpzqs=" + zkpzqs +
        ", zkpzdj=" + zkpzdj +
        ", zkymchsl=" + zkymchsl +
        ", zkymcsxhsl=" + zkymcsxhsl +
        ", dtable=" + dtable +
        ", ysgcbh=" + ysgcbh +
        ", zkcolor=" + zkcolor +
        ", zkzbh=" + zkzbh +
        ", zkszff=" + zkszff +
        ", zkzjlx=" + zkzjlx +
        ", zkztdw=" + zkztdw +
        ", zkgdmc=" + zkgdmc +
        ", zkdmbg=" + zkdmbg +
        ", jyjcgc=" + jyjcgc +
        ", sortkey=" + sortkey +
        "}";
    }
}
