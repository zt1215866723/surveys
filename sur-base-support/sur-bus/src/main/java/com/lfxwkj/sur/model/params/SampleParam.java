package com.lfxwkj.sur.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 取样数据表
 * </p>
 *
 * @author 张童
 * @since 2020-10-22
 */
@Data
public class SampleParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 项目Id
     */
    private Long itemId;

    /**
     * 钻孔编号
     */
    private String holeCode;

    /**
     * 取样编号
     */
    private String qybh;

    /**
     * 取样顶深度(m)
     */
    private Float qysd;

    /**
     * 取样长度(m)
     */
    private Float qyhd;

    /**
     * 所在地层
     */
    private String qydc;

    /**
     * 取样类型 0：原状样 1：扰动样
     */
    private Integer qylx;

    /**
     * 质量密度ρ(g/cm^3)
     */
    private Float qyzlmd;

    /**
     * 土粒比重Gs
     */
    private Float qybz;

    /**
     * 含水量ω(%)
     */
    private Float qyhsl;

    /**
     * 液限ωL(%)
     */
    private Float qyyx;

    /**
     * 塑限ωP(%)
     */
    private Float qysx;

    @Override
    public String checkParam() {
        return null;
    }

}
