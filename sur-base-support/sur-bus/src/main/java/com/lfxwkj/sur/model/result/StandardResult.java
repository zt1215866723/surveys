package com.lfxwkj.sur.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 地层信息
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
@Data
public class StandardResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 工程id
     */
    private Long itemId;

    /**
     * 钻孔编号
     */
    private String holeCode;

    /**
     * 层底深度（米）
     */
    private Double depth;

    private Integer tcxh;

    /**
     * 主层编号
     */
    private String mainCode;

    /**
     * 亚层编号
     */
    private String secondaryCode;

    /**
     * 次亚层编号
     */
    private String thirdCode;

    /**
     * 地质时代
     */
    private String tcdzsd;

    /**
     * 地质成因
     */
    private String tcdzcy;

    /**
     * 自然层厚度(m)
     */
    private Double tcdchd;

    /**
     * 地层厚度(m)
     */
    private Double tchd;

    /**
     * 岩土类别
     */
    private String type;

    /**
     * 岩土名称
     */
    private String name;

    /**
     * 土名代号
     */
    private String tcmdh;

    /**
     * 亚岩土名称
     */
    private String tcymc;

    /**
     * 土层颜色
     */
    private String tcys;

    /**
     * 密实度
     */
    private String tcmsd;

    /**
     * 湿度
     */
    private String tcsid;

    /**
     * 可塑性
     */
    private String tcksx;

    /**
     * 浑圆度
     */
    private String tchyd;

    /**
     * 均匀性
     */
    private String tcjyx;

    /**
     * 风化程度
     */
    private String tcfhcd;

    /**
     * 岩层倾向(度)
     */
    private Double tcysqx;

    /**
     * 岩层倾角(度)
     */
    private Double tcysqj;

    /**
     * 矿物成分
     */
    private String tckwcf;

    /**
     * 结构构造
     */
    private String tcjggz;

    /**
     * 包含物
     */
    private String tcbhw;

    /**
     * 气味
     */
    private String tcqw;

    /**
     * 土层描述
     */
    private String tcms;

    /**
     * 完整程度
     */
    private String tcztx;

    /**
     * 坚硬程度
     */
    private String tcjycd;

    /**
     * 破碎程度
     */
    private String tcpl;

    /**
     * 节理发育
     */
    private String tcjlfy;

    /**
     * 地层描述
     */
    private String tcdcms;

    /**
     * 节理间距(cm)
     */
    private Double tcjljj;

    /**
     * 视倾角(度)
     */
    private Double tcpxjd;

    /**
     * 其他特征
     */
    private String tcqttz;

    /**
     * 天然重度(kN/m3)
     */
    private Double tczrzd;

    /**
     * 粘聚力(kPa)
     */
    private Double tcnjl;

    /**
     * 内摩擦角(°)
     */
    private Double tcnmcj;

    /**
     * 承载力(kPa)
     */
    private Double tcczl;

    /**
     * 压缩模量(MPa)
     */
    private Double tcysml;

    /**
     * 预制桩侧阻力特征值(kPa)
     */
    private Double tcyzzCzltzz;

    /**
     * 预制桩端阻力特征值(kPa)
     */
    private Double tcyzzDzltzz;

    /**
     * 预制桩极限侧阻力标准值(kPa)
     */
    private Double tcyzzCzlbzz;

    /**
     * 预制桩极限端阻力标准值(kPa)
     */
    private Double tcyzzDzlbzz;

    /**
     * 冲(钻)孔桩侧阻力特征值(kPa)
     */
    private Double tcckzCzltzz;

    /**
     * 冲(钻)孔桩端阻力特征值(kPa)
     */
    private Double tcckzDzltzz;

    /**
     * 冲(钻)孔桩极限侧阻力标准值(kPa)
     */
    private Double tcckzCzlbzz;

    /**
     * 冲(钻)孔桩极限端阻力标准值(kPa)
     */
    private Double tcckzDzlbzz;

    /**
     * 自定义字段1
     */
    private String tcuserdefine1;

    /**
     * 自定义字段2
     */
    private String tcuserdefine2;

    /**
     * 自定义字段3
     */
    private String tcuserdefine3;

}
