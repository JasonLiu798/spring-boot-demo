package com.jason798.error;

/**
 * 系统错误、常用错误码
 */
public class CommonErrorCode {


    public static final String OK = "ok";
    public static final String OK_MSG = "成功";
    public static final String FAIL = "fail";



    public static final Integer SUCCESS = 0;
    public static final String SUCCESS_MSG = "成功";
    
    public static final int PARAM_NULL = 1001;
    public static final String PARAM_NULL_MSG = "参数为空";

    public static final int SYS_ERROR_CODE = 1002;
    public static final String SYS_ERROR_MSG = "系统错误";

    public static final int JSON_PARSE_ERROR = 1003;
    public static final String JSON_PARSE_ERROR_MSG = "json解析错误";

    public static final int PARAM_FORMAT_ERROR = 1004;
    public static final String PARAM_FORMAT_ERROR_MSG = "参数不合法";

    public static final int DB_EXECUTE_ERROR = 1008;
    public static final String DB_EXECUTE_ERROR_MSG = "db执行错误";

    public static final int PARAMETER_FORMAT_ERROR = 1009;
    public static final String PARAMETER_FORMAT_ERROR_MSG = "参数格式错误";

    public static final int UNKNOWN_ERROR = 1004;
    public static final String UNKNOWN_ERROR_MSG = "未知异常";

    public static final int FLOW_PROCESSOR_GET_ERROR = 1005;
    public static final String FLOW_PROCESSOR_GET_ERROR_MSG = "流程处理器获取失败";

    public static final int FLOW_INVOKE_ERROR = 1006;
    public static final String FLOW_INVOKE_ERROR_MSG = "业务流程执行异常";
}
