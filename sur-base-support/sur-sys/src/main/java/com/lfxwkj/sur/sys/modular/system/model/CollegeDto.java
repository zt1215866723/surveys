package com.lfxwkj.sur.sys.modular.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 牛棚信息
 * </p>
 *
 * @author 郭晓东
 * @since 2020-04-16
 */
@Data
public class CollegeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long deptId;

    /**
     * 父部门主键
     */
    private Long pId;

    /**
     * 名称
     */
    private String deptName;

}
