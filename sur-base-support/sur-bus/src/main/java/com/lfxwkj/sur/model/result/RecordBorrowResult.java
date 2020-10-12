package com.lfxwkj.sur.model.result;

import lombok.Data;
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
public class RecordBorrowResult implements Serializable {

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

    private String administratorName;

    /**
     * 备注
     */
    private String note;

}
