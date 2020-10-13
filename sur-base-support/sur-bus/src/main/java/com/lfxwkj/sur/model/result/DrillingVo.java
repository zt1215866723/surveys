package com.lfxwkj.sur.model.result;

import lombok.Data;

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

    /**
     * 孔口高程(m)
     */
    private Double zkbg;

    /**
     * 钻孔直径(m)
     */
    private Double zkzj;

    /**
     * 转换前经度
     */
    private Double zkx;

    /**
     * 转换前纬度
     */
    private Double zky;

    /**
     * 转换后经度
     */
    private String xaxis;

    /**
     * 转换后纬度
     */
    private String yaxis;
}
