package com.lfxwkj.sur.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 索引信息
 * </p>
 *
 * @author lizheng
 * @since 2020-08-19
 */
@Data
public class IndexParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 文档id
     */
    private Long subId;

    /**
     * 检测项id
     */
    private Long focusId;

    /**
     * 数字型结果
     */
    private Double nouValue;

    /**
     * 字符串结果(数据字典)
     */
    private String strValue;

    /**
     * 状态
     */
    private Integer state;

    @Override
    public String checkParam() {
        return null;
    }

}
