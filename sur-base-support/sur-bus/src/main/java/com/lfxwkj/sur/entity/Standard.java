package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 地层信息
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
@TableName("lz_standard")
public class Standard implements Serializable {

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
     * 层底深度（米）
     */
    @TableField("depth")
    private Double depth;

    @TableField("tcxh")
    private Integer tcxh;

    /**
     * 主层编号
     */
    @TableField("main_code")
    private String mainCode;

    /**
     * 亚层编号
     */
    @TableField("secondary_code")
    private String secondaryCode;

    /**
     * 次亚层编号
     */
    @TableField("third_code")
    private String thirdCode;

    /**
     * 地质时代
     */
    @TableField("tcdzsd")
    private String tcdzsd;

    /**
     * 地质成因
     */
    @TableField("tcdzcy")
    private String tcdzcy;

    /**
     * 自然层厚度(m)
     */
    @TableField("tcdchd")
    private Double tcdchd;

    /**
     * 地层厚度(m)
     */
    @TableField("tchd")
    private Double tchd;

    /**
     * 岩土类别
     */
    @TableField("type")
    private String type;

    /**
     * 岩土名称
     */
    @TableField("name")
    private String name;

    /**
     * 土名代号
     */
    @TableField("tcmdh")
    private String tcmdh;

    /**
     * 亚岩土名称
     */
    @TableField("tcymc")
    private String tcymc;

    /**
     * 土层颜色
     */
    @TableField("tcys")
    private String tcys;

    /**
     * 密实度
     */
    @TableField("tcmsd")
    private String tcmsd;

    /**
     * 湿度
     */
    @TableField("tcsid")
    private String tcsid;

    /**
     * 可塑性
     */
    @TableField("tcksx")
    private String tcksx;

    /**
     * 浑圆度
     */
    @TableField("tchyd")
    private String tchyd;

    /**
     * 均匀性
     */
    @TableField("tcjyx")
    private String tcjyx;

    /**
     * 风化程度
     */
    @TableField("tcfhcd")
    private String tcfhcd;

    /**
     * 岩层倾向(度)
     */
    @TableField("tcysqx")
    private Double tcysqx;

    /**
     * 岩层倾角(度)
     */
    @TableField("tcysqj")
    private Double tcysqj;

    /**
     * 矿物成分
     */
    @TableField("tckwcf")
    private String tckwcf;

    /**
     * 结构构造
     */
    @TableField("tcjggz")
    private String tcjggz;

    /**
     * 包含物
     */
    @TableField("tcbhw")
    private String tcbhw;

    /**
     * 气味
     */
    @TableField("tcqw")
    private String tcqw;

    /**
     * 土层描述
     */
    @TableField("tcms")
    private String tcms;

    /**
     * 完整程度
     */
    @TableField("tcztx")
    private String tcztx;

    /**
     * 坚硬程度
     */
    @TableField("tcjycd")
    private String tcjycd;

    /**
     * 破碎程度
     */
    @TableField("tcpl")
    private String tcpl;

    /**
     * 节理发育
     */
    @TableField("tcjlfy")
    private String tcjlfy;

    /**
     * 地层描述
     */
    @TableField("tcdcms")
    private String tcdcms;

    /**
     * 节理间距(cm)
     */
    @TableField("tcjljj")
    private Double tcjljj;

    /**
     * 视倾角(度)
     */
    @TableField("tcpxjd")
    private Double tcpxjd;

    /**
     * 其他特征
     */
    @TableField("tcqttz")
    private String tcqttz;

    /**
     * 天然重度(kN/m3)
     */
    @TableField("tczrzd")
    private Double tczrzd;

    /**
     * 粘聚力(kPa)
     */
    @TableField("tcnjl")
    private Double tcnjl;

    /**
     * 内摩擦角(°)
     */
    @TableField("tcnmcj")
    private Double tcnmcj;

    /**
     * 承载力(kPa)
     */
    @TableField("tcczl")
    private Double tcczl;

    /**
     * 压缩模量(MPa)
     */
    @TableField("tcysml")
    private Double tcysml;

    /**
     * 预制桩侧阻力特征值(kPa)
     */
    @TableField("tcyzz_czltzz")
    private Double tcyzzCzltzz;

    /**
     * 预制桩端阻力特征值(kPa)
     */
    @TableField("tcyzz_dzltzz")
    private Double tcyzzDzltzz;

    /**
     * 预制桩极限侧阻力标准值(kPa)
     */
    @TableField("tcyzz_czlbzz")
    private Double tcyzzCzlbzz;

    /**
     * 预制桩极限端阻力标准值(kPa)
     */
    @TableField("tcyzz_dzlbzz")
    private Double tcyzzDzlbzz;

    /**
     * 冲(钻)孔桩侧阻力特征值(kPa)
     */
    @TableField("tcckz_czltzz")
    private Double tcckzCzltzz;

    /**
     * 冲(钻)孔桩端阻力特征值(kPa)
     */
    @TableField("tcckz_dzltzz")
    private Double tcckzDzltzz;

    /**
     * 冲(钻)孔桩极限侧阻力标准值(kPa)
     */
    @TableField("tcckz_czlbzz")
    private Double tcckzCzlbzz;

    /**
     * 冲(钻)孔桩极限端阻力标准值(kPa)
     */
    @TableField("tcckz_dzlbzz")
    private Double tcckzDzlbzz;

    /**
     * 自定义字段1
     */
    @TableField("tcuserdefine1")
    private String tcuserdefine1;

    /**
     * 自定义字段2
     */
    @TableField("tcuserdefine2")
    private String tcuserdefine2;

    /**
     * 自定义字段3
     */
    @TableField("tcuserdefine3")
    private String tcuserdefine3;


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

    public Integer getTcxh() {
        return tcxh;
    }

    public void setTcxh(Integer tcxh) {
        this.tcxh = tcxh;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getSecondaryCode() {
        return secondaryCode;
    }

    public void setSecondaryCode(String secondaryCode) {
        this.secondaryCode = secondaryCode;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getTcdzsd() {
        return tcdzsd;
    }

    public void setTcdzsd(String tcdzsd) {
        this.tcdzsd = tcdzsd;
    }

    public String getTcdzcy() {
        return tcdzcy;
    }

    public void setTcdzcy(String tcdzcy) {
        this.tcdzcy = tcdzcy;
    }

    public Double getTcdchd() {
        return tcdchd;
    }

    public void setTcdchd(Double tcdchd) {
        this.tcdchd = tcdchd;
    }

    public Double getTchd() {
        return tchd;
    }

    public void setTchd(Double tchd) {
        this.tchd = tchd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTcmdh() {
        return tcmdh;
    }

    public void setTcmdh(String tcmdh) {
        this.tcmdh = tcmdh;
    }

    public String getTcymc() {
        return tcymc;
    }

    public void setTcymc(String tcymc) {
        this.tcymc = tcymc;
    }

    public String getTcys() {
        return tcys;
    }

    public void setTcys(String tcys) {
        this.tcys = tcys;
    }

    public String getTcmsd() {
        return tcmsd;
    }

    public void setTcmsd(String tcmsd) {
        this.tcmsd = tcmsd;
    }

    public String getTcsid() {
        return tcsid;
    }

    public void setTcsid(String tcsid) {
        this.tcsid = tcsid;
    }

    public String getTcksx() {
        return tcksx;
    }

    public void setTcksx(String tcksx) {
        this.tcksx = tcksx;
    }

    public String getTchyd() {
        return tchyd;
    }

    public void setTchyd(String tchyd) {
        this.tchyd = tchyd;
    }

    public String getTcjyx() {
        return tcjyx;
    }

    public void setTcjyx(String tcjyx) {
        this.tcjyx = tcjyx;
    }

    public String getTcfhcd() {
        return tcfhcd;
    }

    public void setTcfhcd(String tcfhcd) {
        this.tcfhcd = tcfhcd;
    }

    public Double getTcysqx() {
        return tcysqx;
    }

    public void setTcysqx(Double tcysqx) {
        this.tcysqx = tcysqx;
    }

    public Double getTcysqj() {
        return tcysqj;
    }

    public void setTcysqj(Double tcysqj) {
        this.tcysqj = tcysqj;
    }

    public String getTckwcf() {
        return tckwcf;
    }

    public void setTckwcf(String tckwcf) {
        this.tckwcf = tckwcf;
    }

    public String getTcjggz() {
        return tcjggz;
    }

    public void setTcjggz(String tcjggz) {
        this.tcjggz = tcjggz;
    }

    public String getTcbhw() {
        return tcbhw;
    }

    public void setTcbhw(String tcbhw) {
        this.tcbhw = tcbhw;
    }

    public String getTcqw() {
        return tcqw;
    }

    public void setTcqw(String tcqw) {
        this.tcqw = tcqw;
    }

    public String getTcms() {
        return tcms;
    }

    public void setTcms(String tcms) {
        this.tcms = tcms;
    }

    public String getTcztx() {
        return tcztx;
    }

    public void setTcztx(String tcztx) {
        this.tcztx = tcztx;
    }

    public String getTcjycd() {
        return tcjycd;
    }

    public void setTcjycd(String tcjycd) {
        this.tcjycd = tcjycd;
    }

    public String getTcpl() {
        return tcpl;
    }

    public void setTcpl(String tcpl) {
        this.tcpl = tcpl;
    }

    public String getTcjlfy() {
        return tcjlfy;
    }

    public void setTcjlfy(String tcjlfy) {
        this.tcjlfy = tcjlfy;
    }

    public String getTcdcms() {
        return tcdcms;
    }

    public void setTcdcms(String tcdcms) {
        this.tcdcms = tcdcms;
    }

    public Double getTcjljj() {
        return tcjljj;
    }

    public void setTcjljj(Double tcjljj) {
        this.tcjljj = tcjljj;
    }

    public Double getTcpxjd() {
        return tcpxjd;
    }

    public void setTcpxjd(Double tcpxjd) {
        this.tcpxjd = tcpxjd;
    }

    public String getTcqttz() {
        return tcqttz;
    }

    public void setTcqttz(String tcqttz) {
        this.tcqttz = tcqttz;
    }

    public Double getTczrzd() {
        return tczrzd;
    }

    public void setTczrzd(Double tczrzd) {
        this.tczrzd = tczrzd;
    }

    public Double getTcnjl() {
        return tcnjl;
    }

    public void setTcnjl(Double tcnjl) {
        this.tcnjl = tcnjl;
    }

    public Double getTcnmcj() {
        return tcnmcj;
    }

    public void setTcnmcj(Double tcnmcj) {
        this.tcnmcj = tcnmcj;
    }

    public Double getTcczl() {
        return tcczl;
    }

    public void setTcczl(Double tcczl) {
        this.tcczl = tcczl;
    }

    public Double getTcysml() {
        return tcysml;
    }

    public void setTcysml(Double tcysml) {
        this.tcysml = tcysml;
    }

    public Double getTcyzzCzltzz() {
        return tcyzzCzltzz;
    }

    public void setTcyzzCzltzz(Double tcyzzCzltzz) {
        this.tcyzzCzltzz = tcyzzCzltzz;
    }

    public Double getTcyzzDzltzz() {
        return tcyzzDzltzz;
    }

    public void setTcyzzDzltzz(Double tcyzzDzltzz) {
        this.tcyzzDzltzz = tcyzzDzltzz;
    }

    public Double getTcyzzCzlbzz() {
        return tcyzzCzlbzz;
    }

    public void setTcyzzCzlbzz(Double tcyzzCzlbzz) {
        this.tcyzzCzlbzz = tcyzzCzlbzz;
    }

    public Double getTcyzzDzlbzz() {
        return tcyzzDzlbzz;
    }

    public void setTcyzzDzlbzz(Double tcyzzDzlbzz) {
        this.tcyzzDzlbzz = tcyzzDzlbzz;
    }

    public Double getTcckzCzltzz() {
        return tcckzCzltzz;
    }

    public void setTcckzCzltzz(Double tcckzCzltzz) {
        this.tcckzCzltzz = tcckzCzltzz;
    }

    public Double getTcckzDzltzz() {
        return tcckzDzltzz;
    }

    public void setTcckzDzltzz(Double tcckzDzltzz) {
        this.tcckzDzltzz = tcckzDzltzz;
    }

    public Double getTcckzCzlbzz() {
        return tcckzCzlbzz;
    }

    public void setTcckzCzlbzz(Double tcckzCzlbzz) {
        this.tcckzCzlbzz = tcckzCzlbzz;
    }

    public Double getTcckzDzlbzz() {
        return tcckzDzlbzz;
    }

    public void setTcckzDzlbzz(Double tcckzDzlbzz) {
        this.tcckzDzlbzz = tcckzDzlbzz;
    }

    public String getTcuserdefine1() {
        return tcuserdefine1;
    }

    public void setTcuserdefine1(String tcuserdefine1) {
        this.tcuserdefine1 = tcuserdefine1;
    }

    public String getTcuserdefine2() {
        return tcuserdefine2;
    }

    public void setTcuserdefine2(String tcuserdefine2) {
        this.tcuserdefine2 = tcuserdefine2;
    }

    public String getTcuserdefine3() {
        return tcuserdefine3;
    }

    public void setTcuserdefine3(String tcuserdefine3) {
        this.tcuserdefine3 = tcuserdefine3;
    }

    @Override
    public String toString() {
        return "Standard{" +
        "id=" + id +
        ", itemId=" + itemId +
        ", holeCode=" + holeCode +
        ", depth=" + depth +
        ", tcxh=" + tcxh +
        ", mainCode=" + mainCode +
        ", secondaryCode=" + secondaryCode +
        ", thirdCode=" + thirdCode +
        ", tcdzsd=" + tcdzsd +
        ", tcdzcy=" + tcdzcy +
        ", tcdchd=" + tcdchd +
        ", tchd=" + tchd +
        ", type=" + type +
        ", name=" + name +
        ", tcmdh=" + tcmdh +
        ", tcymc=" + tcymc +
        ", tcys=" + tcys +
        ", tcmsd=" + tcmsd +
        ", tcsid=" + tcsid +
        ", tcksx=" + tcksx +
        ", tchyd=" + tchyd +
        ", tcjyx=" + tcjyx +
        ", tcfhcd=" + tcfhcd +
        ", tcysqx=" + tcysqx +
        ", tcysqj=" + tcysqj +
        ", tckwcf=" + tckwcf +
        ", tcjggz=" + tcjggz +
        ", tcbhw=" + tcbhw +
        ", tcqw=" + tcqw +
        ", tcms=" + tcms +
        ", tcztx=" + tcztx +
        ", tcjycd=" + tcjycd +
        ", tcpl=" + tcpl +
        ", tcjlfy=" + tcjlfy +
        ", tcdcms=" + tcdcms +
        ", tcjljj=" + tcjljj +
        ", tcpxjd=" + tcpxjd +
        ", tcqttz=" + tcqttz +
        ", tczrzd=" + tczrzd +
        ", tcnjl=" + tcnjl +
        ", tcnmcj=" + tcnmcj +
        ", tcczl=" + tcczl +
        ", tcysml=" + tcysml +
        ", tcyzzCzltzz=" + tcyzzCzltzz +
        ", tcyzzDzltzz=" + tcyzzDzltzz +
        ", tcyzzCzlbzz=" + tcyzzCzlbzz +
        ", tcyzzDzlbzz=" + tcyzzDzlbzz +
        ", tcckzCzltzz=" + tcckzCzltzz +
        ", tcckzDzltzz=" + tcckzDzltzz +
        ", tcckzCzlbzz=" + tcckzCzlbzz +
        ", tcckzDzlbzz=" + tcckzDzlbzz +
        ", tcuserdefine1=" + tcuserdefine1 +
        ", tcuserdefine2=" + tcuserdefine2 +
        ", tcuserdefine3=" + tcuserdefine3 +
        "}";
    }
}
