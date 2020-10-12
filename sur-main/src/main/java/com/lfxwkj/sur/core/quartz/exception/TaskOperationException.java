package com.lfxwkj.sur.core.quartz.exception;

import cn.stylefeng.roses.kernel.model.exception.AbstractBaseExceptionEnum;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

public class TaskOperationException extends ServiceException {

    private Integer code;
    private String errorMessage;

    public TaskOperationException(AbstractBaseExceptionEnum exception) {
        super(exception);
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}