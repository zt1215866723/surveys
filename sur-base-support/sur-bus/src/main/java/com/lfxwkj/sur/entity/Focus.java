package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 关注项
 * </p>
 *
 * @author lizheng
 * @since 2020-08-19
 */
@TableName("sur_focus")
public class Focus implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关注项名称
     */
    @TableField("name")
    private String name;

    /**
     * 单位
     */
    @TableField("unit")
    private String unit;

    /**
     * 0double >1字典类型
     */
    @TableField("type")
    private Long type;

    /**
     * 状态 0默认 1删除 2禁用
     */
    @TableField("state")
    private Integer state;

    /**
     * 添加人
     */
    @TableField("add_user")
    private Long addUser;

    /**
     * 添加时间
     */
    @TableField("add_time")
    private Date addTime;

    /**
     * 备用__勘察种类（数据字典）
     */
    @TableField("item_type")
    private Long itemType;

    /**
     * 备用__是否必填 0否 1是
     */
    @TableField("is_necessary")
    private Integer isNecessary;

    @TableField("is_show")
    private Integer isShow;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getAddUser() {
        return addUser;
    }

    public void setAddUser(Long addUser) {
        this.addUser = addUser;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Long getItemType() {
        return itemType;
    }

    public void setItemType(Long itemType) {
        this.itemType = itemType;
    }

    public Integer getIsNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(Integer isNecessary) {
        this.isNecessary = isNecessary;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return "Focus{" +
        "id=" + id +
        ", name=" + name +
        ", unit=" + unit +
        ", type=" + type +
        ", state=" + state +
        ", addUser=" + addUser +
        ", addTime=" + addTime +
        ", itemType=" + itemType +
        ", isNecessary=" + isNecessary +
        "}";
    }
}
