package com.lfxwkj.sur.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 借阅记录表
 * </p>
 *
 * @author lizheng
 * @since 2020-09-08
 */
@TableName("sur_record_borrow")
public class RecordBorrow implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 借阅人
     */
    @TableField("borrower")
    private String borrower;

    /**
     * 文档id
     */
    @TableField("document_id")
    private Long documentId;

    /**
     * 借出时间
     */
    @TableField("add_time")
    private Date addTime;

    /**
     * 归还时间
     */
    @TableField("return_time")
    private Date returnTime;

    /**
     * 管理人id
     */
    @TableField("administrator")
    private Long administrator;

    /**
     * 备注
     */
    @TableField("note")
    private String note;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Long getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Long administrator) {
        this.administrator = administrator;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "RecordBorrow{" +
        "id=" + id +
        ", borrower=" + borrower +
        ", documentId=" + documentId +
        ", addTime=" + addTime +
        ", returnTime=" + returnTime +
        ", administrator=" + administrator +
        ", note=" + note +
        "}";
    }
}
