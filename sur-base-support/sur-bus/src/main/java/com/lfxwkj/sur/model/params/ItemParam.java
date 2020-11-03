package com.lfxwkj.sur.model.params;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class ItemParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
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

    private Long progress;

    private Long type;

    private String processName;

    private String typeName;

    private String head;

    private Date beginDate;

    private Date endDate;

    private Integer isForeign;

    private Integer isShow;

    private Integer coorSystem;

    private String itemTypes;

    private String itemPlans;

    private String[] typeArray;

    private String[] planArray;

    @Override
    public String checkParam() {
        return null;
    }

}
