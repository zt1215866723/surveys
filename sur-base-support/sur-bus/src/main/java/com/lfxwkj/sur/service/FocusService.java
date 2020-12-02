package com.lfxwkj.sur.service;

import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Focus;
import com.lfxwkj.sur.model.params.FocusParam;
import com.lfxwkj.sur.model.params.ItemSubParam;
import com.lfxwkj.sur.model.result.FocusResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfxwkj.sur.model.result.IndexResult;

import java.util.List;

/**
 * <p>
 * 关注项 服务类
 * </p>
 *
 * @author lizheng
 * @since 2020-08-19
 */
public interface FocusService extends IService<Focus> {

    /**
     * 新增
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    void add(FocusParam param);

    /**
     * 删除
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    void delete(FocusParam param);

    /**
     * 更新
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    void update(FocusParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    FocusResult findBySpec(FocusParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lizheng
     * @Date 2020-08-19
     */
    List<FocusResult> findListBySpec(FocusParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lizheng
     * @Date 2020-08-19
     */
     LayuiPageInfo findPageBySpec(FocusParam param);

    List<Focus> getList(FocusParam focusParam);

    List<IndexResult> selectFocusByitemId(ItemSubParam itemSubParam);
}
