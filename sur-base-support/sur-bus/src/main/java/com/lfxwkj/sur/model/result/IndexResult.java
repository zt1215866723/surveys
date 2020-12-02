package com.lfxwkj.sur.model.result;

import lombok.Data;
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
public class IndexResult implements Serializable {

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

    private String focusName;

    private String unit;

    private Long type;

    private String resultName;
}
