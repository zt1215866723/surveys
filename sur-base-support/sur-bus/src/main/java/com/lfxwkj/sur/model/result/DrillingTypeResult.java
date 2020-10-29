package com.lfxwkj.sur.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 钻孔类型表
 * </p>
 *
 * @author 王南翔
 * @since 2020-10-29
 */
@Data
public class DrillingTypeResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图例存储路径
     */
    private String url;

    /**
     * 状态0启用1删除2禁用
     */
    private Integer status;

}
