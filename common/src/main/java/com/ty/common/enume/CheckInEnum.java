package com.ty.common.enume;

public enum CheckInEnum {
    WAIT_CHECK_IN(0, "待入住"),
    SUCCESS_CHECK_IN(1,"成功入住"),
    CHECK_OUT(2,"已离店");

    private Integer code;
    private String msg;

    CheckInEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
