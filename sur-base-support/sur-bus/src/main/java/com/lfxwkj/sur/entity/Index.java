package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 索引信息
 * </p>
 *
 * @author lizheng
 * @since 2020-08-19
 */
@TableName("sur_index")
public class Index implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 文档id
     */
    @TableField("sub_id")
    private Long subId;

    /**
     * 检测项id
     */
    @TableField("focus_id")
    private Long focusId;

    /**
     * 数字型结果
     */
    @TableField("nou_value")
    private Double nouValue;

    /**
     * 字符串结果(数据字典)
     */
    @TableField("str_value")
    private String strValue;

    /**
     * 状态
     */
    @TableField("state")
    private Integer state;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Long getFocusId() {
        return focusId;
    }

    public void setFocusId(Long focusId) {
        this.focusId = focusId;
    }

    public Double getNouValue() {
        return nouValue;
    }

    public void setNouValue(Double nouValue) {
        this.nouValue = nouValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Index{" +
        "id=" + id +
        ", subId=" + subId +
        ", focusId=" + focusId +
        ", nouValue=" + nouValue +
        ", strValue=" + strValue +
        ", state=" + state +
        "}";
    }
}
