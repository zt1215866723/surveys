package com.lfxwkj.sur.mapper;

import com.lfxwkj.sur.entity.RecordBorrow;
import com.lfxwkj.sur.model.params.RecordBorrowParam;
import com.lfxwkj.sur.model.result.RecordBorrowResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 借阅记录表 Mapper 接口
 * </p>
 *
 * @author lizheng
 * @since 2020-09-08
 */
public interface RecordBorrowMapper extends BaseMapper<RecordBorrow> {

    /**
     * 获取列表
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    List<RecordBorrowResult> customList(@Param("paramCondition") RecordBorrowParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") RecordBorrowParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    Page<RecordBorrowResult> customPageList(@Param("page") Page page, @Param("paramCondition") RecordBorrowParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lizheng
     * @Date 2020-09-08
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") RecordBorrowParam paramCondition);

    /**
     * 根据id获取详情
     * @param id
     * @return
     */
    RecordBorrowResult getDetail(Long id);
}
