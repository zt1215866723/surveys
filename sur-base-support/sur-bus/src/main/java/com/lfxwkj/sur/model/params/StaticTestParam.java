package com.lfxwkj.sur.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 静探表
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@Data
public class StaticTestParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 工程id
     */
    private Long itemId;

    private String itemIds;

    /**
     * 钻孔编号
     */
    private String holeCode;

    /**
     * 试探点深度（米）
     */
    private Double depth;

    /**
     * 静探类型
     */
    private Integer jtlx;

    /**
     * 试验段长（米）
     */
    private Double length;

    /**
     * 比贯入阻力(MPa)
     */
    private Double jtbgrzl;

    /**
     * 锥头阻力(MPa)
     */
    private Double jtztzl;

    /**
     * 侧壁摩阻力(kPa)
     */
    private Double jtcmz;

    /**
     * 摩阻比(%)
     */
    private Double jtmzb;

    /**
     * 孔隙水压力(kPa)
     */
    private Double jtkxyl;

    /**
     * 是否参与统计1参与0不参与
     */
    private Integer cy;

    private Integer jtbgrzlx;

    private Integer jtbgrzltjx;

    private Integer jtztzlx;

    private Integer jtztzltjx;

    private Integer jtcmzx;

    private Integer jtcmztjx;

    private Integer jtmzbx;

    private Integer jtmzbtjx;

    private Integer jtkxylx;

    private Integer jtkxyltjx;

    @Override
    public String checkParam() {
        return null;
    }

}
