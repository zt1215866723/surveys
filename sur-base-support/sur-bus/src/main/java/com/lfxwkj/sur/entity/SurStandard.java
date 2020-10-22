package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 所有工程土层标准
 * </p>
 *
 * @author 张童
 * @since 2020-10-22
 */
@TableName("sur_standard")
public class SurStandard implements Serializable {

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

    @Override
    public String toString() {
        return "SurStandard{" +
        "id=" + id +
        ", itemId=" + itemId +
        ", mainCode=" + mainCode +
        ", secondaryCode=" + secondaryCode +
        ", thirdCode=" + thirdCode +
        ", tcdzsd=" + tcdzsd +
        ", tcdzcy=" + tcdzcy +
        ", type=" + type +
        ", name=" + name +
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
        ", tcjljj=" + tcjljj +
        ", tcpxjd=" + tcpxjd +
        ", tcqttz=" + tcqttz +
        "}";
    }
}
