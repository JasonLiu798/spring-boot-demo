package com.jason798.dto;

import com.jason798.error.CommonErrorCode;

import java.io.Serializable;


public class ResponseDataDto extends ResponseDto implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private Object data = null;

    public ResponseDataDto() {
        super();
    }

    public ResponseDataDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseDataDto(String succ, String msg) {
        this.succ = succ;
        this.msg = msg;
    }

    public static ResponseDataDto buildOk() {
        return new ResponseDataDto(CommonErrorCode.OK, CommonErrorCode.OK_MSG);
    }

    public static ResponseDataDto buildOk(Object obj) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setData(obj);
        return resp;
    }

    public static ResponseDataDto buildFail(String msg) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setCode(CommonErrorCode.UNKNOWN_ERROR);
        resp.setMsg(msg);
        return resp;
    }

    public static ResponseDataDto buildFail(int code, String msg) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public static ResponseDataDto buildFail(int code, String msg, Object data) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setData(data);
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }


    public ResponseDataDto(Object data) {
        super();
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        if (this.code == 0) {
            return true;
        }
        return false;
    }


    public boolean isFail() {
        return !isSuccess();
    }

    @Override
    public ResponseDataDto clone() {
        ResponseDataDto res = new ResponseDataDto();
        res.setData(this.data);
        res.setCode(this.code);
        res.setMsg(this.msg);
        return res;
    }

    @Override
    public String toString() {
        return "ResponseDataDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                "data=" + data +
                '}';
    }
}

