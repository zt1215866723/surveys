package com.lfxwkj.sur.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 工程土层标准
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@Data
public class StandardFormationResult implements Serializable {

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
     * 岩土类别
     */
    private String type;

    /**
     * 岩土名称
     */
    private String name;

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

}
