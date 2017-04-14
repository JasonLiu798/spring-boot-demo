package com.jason798.error;

public class ErrorCode {
    public final String code;
    public final String message;
    public final String action;
    public final ErrorCode cause;

    public ErrorCode(String code, String message, String action) {
        this(code, message, action, null);
    }

    public ErrorCode(String code, String message, String action, ErrorCode cause) {
        this.code = code;
        this.message = message;
        this.action = action;
        this.cause = cause;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getAction() {
        return action;
    }

    public ErrorCode getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return code + ":" + message + "," + action;
    }

    public static ErrorCode UNKNOW_ERROR = new ErrorCode("S00001", "未知错误", "请查看错误信息");
    public static ErrorCode CONFIGURE_ERROR = new ErrorCode("S00002", "配置信息读取错误", "请查看错误信息");

}
