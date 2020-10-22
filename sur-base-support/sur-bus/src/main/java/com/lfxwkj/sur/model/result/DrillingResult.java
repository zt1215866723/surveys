package com.lfxwkj.sur.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 勘探点数据表
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
@Data
public class DrillingResult implements Serializable {

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
     * 钻孔类型
     */
    private String type;

    /**
     * 横坐标
     */
    private Double zkx;

    /**
     * 纵坐标
     */
    private Double zky;

    /**
     * 建筑地段
     */
    private String jzdd;

    /**
     * 段号
     */
    private String zkdh;

    /**
     * 里程（米）
     */
    private Double zklc;

    /**
     * 偏移量(m)
     */
    private Double zkpil;

    /**
     * 孔口高程(m)
     */
    private Double zkbg;

    /**
     * 水面高程(m)
     */
    private Double zkhsbg;

    /**
     * 勘探深度(m)
     */
    private Double depth;

    /**
     * 探井深度(m)
     */
    private Double zktjsd;

    /**
     * 钻孔直径(mm)
     */
    private Double zkzj;

    /**
     * 断层类型
     */
    private String zkdclc;

    /**
     * 断层夹角(度)
     */
    private Double zkdcjj;

    /**
     * 断层起始深度(m)
     */
    private Double zkdcqsd;

    /**
     * 断层终止深度(m)
     */
    private Double zkdczsd;

    /**
     * 断层盘面
     */
    private String zkdcpm;

    /**
     * 虚拟钻孔0不是1是
     */
    private String zkxn;

    /**
     * 是否参与统计1是0否
     */
    private Integer zksfcy;

    /**
     * 已有孔标志
     */
    private Integer zkjyk;

    /**
     * 地下水温(度)
     */
    private Double zkdxsw;

    /**
     * 探槽角度(度)
     */
    private Double zktcjd;

    /**
     * 勘探开始日期
     */
    private Date zkksrq;

    /**
     * 勘探终止日期
     */
    private Date zkzzrq;

    /**
     * 勘探点等级
     */
    private Integer zkdj;

    /**
     * 土的湿度系数ψw
     */
    private Double zkpztsdxs;

    /**
     * 大气影响深度(m)
     */
    private Double zkpzdqyxsd;

    /**
     * 备注
     */
    private String bz;

    private Integer zksc;

    private String zkh;

    private Integer zkv;

    private String zkyhzs;

    /**
     * 湿陷等级
     */
    private Integer sxdj;

    /**
     * 湿陷类型
     */
    private Integer sxlx;

    /**
     * 膨胀潜势
     */
    private Integer zkpzqs;

    /**
     * 膨胀等级
     */
    private Integer zkpzdj;

    /**
     * 1m深处含水量ω1
     */
    private Double zkymchsl;

    /**
     * 1m深处塑限含水量ω1p
     */
    private Double zkymcsxhsl;

    private String dtable;

    /**
     * 原始工程编号
     */
    private String ysgcbh;

    private Integer zkcolor;

    /**
     * 总编号
     */
    private String zkzbh;

    /**
     * 施钻方法
     */
    private String zkszff;

    /**
     * 钻机类型
     */
    private String zkzjlx;

    /**
     * 钻探单位
     */
    private String zkztdw;

    /**
     * 工点名称
     */
    private String zkgdmc;

    /**
     * 地面高程
     */
    private Double zkdmbg;

    /**
     * 建议基础高程(m)
     */
    private Double jyjcgc;

    private Integer sortkey;

}
