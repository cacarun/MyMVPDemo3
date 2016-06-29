package com.android.mvp2.data.model;

/**
 * Created by cjw on 2016/6/29.
 */
public class HttpResult<T> {

    private int errorCode;
    private String errorMsg;

    //用来模仿Data
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("errorCode=" + errorCode + " errorMsg=" + errorMsg);
        if (null != data) {
            sb.append(" data:" + data.toString());
        }
        return sb.toString();
    }
}
