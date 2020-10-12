package com.lfxwkj.sur.core.quartz.exception;

import cn.stylefeng.roses.kernel.model.exception.AbstractBaseExceptionEnum;

public enum TaskOperationEnum implements AbstractBaseExceptionEnum {

    /**
     * 任务加载类
     */
    CLASS_ERROR(1001, "映射加载类失败,请检测任务执行类"),

    /**
     * 任务删除失败
     */
    JOB_DELETE_ERROR(1003, "任务移除失败"),

    /**
     *
     */
    JOB_UPDATE_DELETE(1004, "更新失败，原任务移除失败"),

    /**
     *
     */
    JOB_UPDATE_CLASS(1005, "更新失败，请检测任务执行类"),

    /**
     *
     */
    JOB_UPDATE_CRON(1006, "更新失败，请检测任务执行表达式"),

    /**
     * 表达式异常
     */
    CRON_ERROR(1002, "任务装载失败,任务表达式不核规范");

    TaskOperationEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}