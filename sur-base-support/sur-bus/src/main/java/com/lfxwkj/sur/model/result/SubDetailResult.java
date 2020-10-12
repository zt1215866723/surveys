package com.lfxwkj.sur.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 文档的目录详情。
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@Data
public class SubDetailResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 取自数据字典，目录的主键
     */
    private String cataId;

    /**
     * 目录的名称
     */
    private String cataName;

    /**
     * 描述
     */
    private String remarks;

    /**
     * 文档的页数
     */
    private Integer pageNum;

    /**
     * 文档的存放路径
     */
    private String saveUrl;

    /**
     * 编制人
     */
    private String prepName;

    /**
     * 复核人
     */
    private String revieName;

    /**
     * 审核人
     */
    private String checkName;

    /**
     * 状态
     */
    private Long state;

}
