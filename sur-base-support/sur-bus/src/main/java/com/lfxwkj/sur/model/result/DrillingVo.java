package com.lfxwkj.sur.model.result;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName:DrillingVo
 * @Description:地图上点击某个工程查询该工程的钻孔信息
 * @Author:张童
 * @Date:
 **/
@Data
public class DrillingVo {
    private static final long serialVersionUID = 1L;

    /**
     * 钻孔编号
     */
    private String holeCode;

    /**
     * 钻孔类型
     */
    private String type;

    /**
     * 勘探深度(m)
     */
    private Double depth;

}
