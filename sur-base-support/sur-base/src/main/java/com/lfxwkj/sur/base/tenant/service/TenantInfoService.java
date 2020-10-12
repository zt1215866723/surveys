package com.lfxwkj.sur.base.tenant.service;

import com.lfxwkj.sur.base.tenant.entity.TenantInfo;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.base.tenant.model.params.TenantInfoParam;
import com.lfxwkj.sur.base.tenant.model.result.TenantInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 租户表 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-16
 */
public interface TenantInfoService extends IService<TenantInfo> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    void add(TenantInfoParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    void delete(TenantInfoParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    void update(TenantInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    TenantInfoResult findBySpec(TenantInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    List<TenantInfoResult> findListBySpec(TenantInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    LayuiPageInfo findPageBySpec(TenantInfoParam param);

    /**
     * 获取租户信息，通过code
     *
     * @author fengshuonan
     * @Date 2019-06-19 14:17
     */
    TenantInfo getByCode(String code);

}
