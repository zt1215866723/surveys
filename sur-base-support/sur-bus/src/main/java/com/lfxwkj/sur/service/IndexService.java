package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Index;
import com.lfxwkj.sur.model.params.IndexParam;
import com.lfxwkj.sur.model.result.IndexResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 索引信息 服务类
 * </p>
 *
 * @author lizheng
 * @since 2020-08-19
 */
public interface IndexService extends IService<Index> {

    /**
     * 新增
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    void add(IndexParam param);

    /**
     * 删除
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    void delete(IndexParam param);

    /**
     * 更新
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    void update(IndexParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    IndexResult findBySpec(IndexParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    List<IndexResult> findListBySpec(IndexParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lizheng
     * @Date 2020-08-19
     */
     LayuiPageInfo findPageBySpec(IndexParam param);

}
