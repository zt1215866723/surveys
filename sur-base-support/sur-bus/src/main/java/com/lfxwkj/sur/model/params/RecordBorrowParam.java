package com.lfxwkj.sur.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 借阅记录表
 * </p>
 *
 * @author lizheng
 * @since 2020-09-08
 */
@Data
public class RecordBorrowParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 借阅人
     */
    private String borrower;

    /**
     * 文档id
     */
    private Long documentId;

    private String documentName;

    /**
     * 借出时间
     */
    private Date addTime;

    /**
     * 归还时间
     */
    private Date returnTime;

    /**
     * 管理人id
     */
    private Long administrator;

    /**
     * 备注
     */
    private String note;

    private String timeLimit;

    private Date startTime;

    private Date endTime;

    @Override
    public String checkParam() {
        return null;
    }

}
