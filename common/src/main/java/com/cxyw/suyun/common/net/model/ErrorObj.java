package com.cxyw.suyun.common.net.model;

/**
 * Created by liub on 16/2/24.
 */
public class ErrorObj {
    private int errorCode;
    private String errorMsg;

    public ErrorObj(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorObj() {
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
