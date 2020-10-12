package com.lfxwkj.sur.service;

import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.RecordBorrow;
import com.lfxwkj.sur.model.params.RecordBorrowParam;
import com.lfxwkj.sur.model.result.RecordBorrowResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 借阅记录表 服务类
 * </p>
 *
 * @author lizheng
 * @since 2020-09-08
 */
public interface RecordBorrowService extends IService<RecordBorrow> {

    /**
     * 新增
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    void add(RecordBorrowParam param);

    /**
     * 删除
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    void delete(RecordBorrowParam param);

    /**
     * 更新
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    void update(RecordBorrowParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    RecordBorrowResult findBySpec(RecordBorrowParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    List<RecordBorrowResult> findListBySpec(RecordBorrowParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lizheng
     * @Date 2020-09-08
     */
     LayuiPageInfo findPageBySpec(RecordBorrowParam param);

    /**
     * 根据id获取详情
     * @param id
     * @return
     */
    RecordBorrowResult getDetail(Long id);

    /**
     * 归还
     * @param recordBorrowParam
     */
    void returnItem(RecordBorrowParam recordBorrowParam);
}
