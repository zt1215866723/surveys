package com.lfxwkj.sur.util;

public enum Code {

    STATE_ORDINARY(0, "普通的"),
    STASE_DELETED(1, "已删除"),
    STASE_DISABLED(2, "已禁用"),
    ;

    private Integer key;

    private String text;

    private Code(Integer key, String text) {
        this.key = key;
        this.text = text;
    }

    public Integer getKey(){
        return key;
    }

    public String getText(){
        return text;
    }

}
