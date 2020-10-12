package com.lfxwkj.sur.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 剖线数据表
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@Data
public class LineResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 项目id
     */
    private Long itemId;

    /**
     * 剖线编号
     */
    private String code;

    /**
     * 连接的钻孔编号，逗号隔开
     */
    private String holeCodes;

    /**
     * 剖线孔间距(m)
     */
    private Double pxkj;

    /**
     * 剖线方位角度(度)
     */
    private Double pxfwj;

    /**
     * 线不绘制
     */
    private Integer pxshx;

    /**
     * 剖线起始指向地点
     */
    private String pxqfx;

    /**
     * 剖线终止指向地点
     */
    private String pxzfx;

    /**
     * 是否删除0否1是
     */
    private Integer pxsc;

    /**
     * 原始工程编号
     */
    private String ysgcbh;

    /**
     * 剖线的起始点X坐标(m)
     */
    private Double pxqsdx;

    /**
     * 剖线的起始点Y坐标(m)
     */
    private Double pxqsdy;

    /**
     * 剖线的终止点X坐标(m)
     */
    private Double pxzzdx;

    /**
     * 剖线的终止点Y坐标(m)
     */
    private Double pxzzdy;

    private Double pxqsdjl;

    private Double pxqsdjd;

    private Double pxzzdjl;

    private Double pxzzdjd;

    private Integer zkh;

    private Integer zkv;

}
