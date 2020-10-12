package com.lfxwkj.sur.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 水位信息
 * </p>
 *
 * @author 张童
 * @since 2020-09-16
 */
@Data
public class WaterLevelParam implements Serializable, BaseValidatingParam {

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
     * 深度（米）
     */
    private Double depth;

    /**
     * 是否稳定1是 0不是
     */
    private Integer swlx;

    /**
     * 是否为地下水位1是 0不是
     */
    private Integer swch;

    /**
     * 测水日期
     */
    private Date swcsrq;

    /**
     * 地下水温(度)
     */
    private Double swdxsw;

    /**
     * 水位范围
     */
    private String swfw;

    /**
     * 地下水类型
     */
    private Integer swxz;

    /**
     * 是否参与统计1参与0不参与
     */
    private Integer cy;

    @Override
    public String checkParam() {
        return null;
    }

}
