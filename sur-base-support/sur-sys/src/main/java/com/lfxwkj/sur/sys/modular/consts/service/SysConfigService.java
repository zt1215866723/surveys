package com.lfxwkj.sur.sys.modular.consts.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.sys.modular.consts.entity.SysConfig;
import com.lfxwkj.sur.sys.modular.consts.model.params.SysConfigParam;
import com.lfxwkj.sur.sys.modular.consts.model.result.SysConfigResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 参数配置 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-20
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-06-20
     */
    void add(SysConfigParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-06-20
     */
    void delete(SysConfigParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-06-20
     */
    void update(SysConfigParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-06-20
     */
    SysConfigResult findBySpec(SysConfigParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author stylefeng
     * @Date 2019-06-20
     */
    List<SysConfigResult> findListBySpec(SysConfigParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-06-20
     */
    LayuiPageInfo findPageBySpec(SysConfigParam param);

}
