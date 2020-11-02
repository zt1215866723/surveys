package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.DrillingType;
import com.lfxwkj.sur.model.params.DrillingTypeParam;
import com.lfxwkj.sur.model.result.DrillingTypeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 钻孔类型表 服务类
 * </p>
 *
 * @author 王南翔
 * @since 2020-10-29
 */
public interface DrillingTypeService extends IService<DrillingType> {

    /**
     * 新增
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    void add(DrillingTypeParam param);

    /**
     * 删除
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    void delete(DrillingTypeParam param);

    /**
     * 更新
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    void update(DrillingTypeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    DrillingTypeResult findBySpec(DrillingTypeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
    List<DrillingTypeResult> findListBySpec(DrillingTypeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 王南翔
     * @Date 2020-10-29
     */
     LayuiPageInfo findPageBySpec(DrillingTypeParam param);

     String convertFileToBase64(String imgPath);
}
