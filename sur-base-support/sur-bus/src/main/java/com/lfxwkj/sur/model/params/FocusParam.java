package com.lfxwkj.sur.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 关注项
 * </p>
 *
 * @author lizheng
 * @since 2020-08-19
 */
@Data
public class FocusParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 关注项名称
     */
    private String name;

    /**
     * 单位
     */
    private String unit;

    /**
     * 0double >1字典类型
     */
    private Long type;

    /**
     * 状态 0默认 1删除 2禁用
     */
    private Integer state;

    /**
     * 添加人
     */
    private Long addUser;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 备用__勘察种类（数据字典）
     */
    private Long itemType;

    private String itemName;

    /**
     * 备用__是否必填 0否 1是
     */
    private Integer isNecessary;

    /**
     * 是否展示
     */
    private Integer isShow;

    @Override
    public String checkParam() {
        return null;
    }

}
