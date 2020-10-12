package com.lfxwkj.sur.model.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 项目表
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@Data
public class ItemResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 编号
     */
    private String itemCode;

    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 地点
     */
    private String location;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 设备状态， 与数据字典对应不关联
     */
    private Integer state;

    /**
     * 项目经度
     */
    private String xaxis;

    /**
     * 项目纬度
     */
    private String yaxis;

    /**
     * 项目进度
     */
    private Long progress;

    /**
     * 项目类型（勘察种类）
     */
    private Long type;

    /**
     * 项目进度名
     */
    private String processName;

    /**
     * 项目类型名（勘察种类）
     */
    private String typeName;

    /**
     * 负责人
     */
    private String head;

    /**
     * 开始日期
     */
    private Date beginDate;

    /**
     * 结束日期
     */
    private Date endDate;

}
