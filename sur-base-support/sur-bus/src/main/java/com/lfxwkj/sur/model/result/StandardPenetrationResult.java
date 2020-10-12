package com.lfxwkj.sur.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 标贯数据
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@Data
public class StandardPenetrationResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 工程主键
     */
    private Long itemId;

    /**
     * 钻孔编号
     */
    private String holeCode;

    /**
     * 试验点底深度（米）
     */
    private Double depth;

    /**
     * 标贯类型
     */
    private Integer bglx;

    /**
     * 特征值
     */
    private Integer bgtzz;

    /**
     * 杆长（米）
     */
    private Double length;

    /**
     * 一阵击数的长度(m)
     */
    private Double bgyzcd;

    /**
     * 一阵击数(击)
     */
    private Double bgyzjs;

    /**
     * 标贯击数(击/30cm)
     */
    private Integer Double;

    /**
     * 标贯修正系数
     */
    private Double bgxs;

    /**
     * 修正后的标贯击数(击/30cm)
     */
    private Double bgxzjs;

    /**
     * 修正否
     */
    private Integer bgsxz;

    /**
     * 是否参与统计1是0否
     */
    private Integer cy;

    private Integer bgxjsx;

    private Integer bgxjstjx;

    private Integer bgjsx;

    private Integer bgjstjx;

}
