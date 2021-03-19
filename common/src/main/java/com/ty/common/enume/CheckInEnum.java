package com.ty.common.enume;

public enum CheckInEnum {
    WAIT_PAYED(0,"待付款"),
    WAIT_CHECK_IN(1, "待入住"),
    SUCCESS_CHECK_IN(2,"成功入住"),
    CHECK_OUT(3,"已离店");

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
