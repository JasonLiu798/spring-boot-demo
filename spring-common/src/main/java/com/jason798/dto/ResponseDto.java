package com.jason798.dto;

import com.jason798.error.CommonErrorCode;
import java.io.Serializable;

/**
 *
 */
public class ResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    protected int code = CommonErrorCode.SUCCESS;
    /**
     * succ
     */
    protected String succ = CommonErrorCode.OK;
    /**
     * msg
     */
    protected String msg = CommonErrorCode.OK_MSG;

    public ResponseDto() {
    }

    public ResponseDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getSucc() {
        return code>CommonErrorCode.SUCCESS?CommonErrorCode.FAIL:CommonErrorCode.OK;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "code=" + code +
                ", succ='" + succ + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
