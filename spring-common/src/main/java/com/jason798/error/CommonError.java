package com.jason798.error;


import com.jason798.dto.ResponseDataDto;

/**
 * 常用错误
 */
public class CommonError {

    public static ResponseDataDto SUCCESS = ResponseDataDto.buildOk();

    /**
     * 系统错误
     */
    public static final ResponseDataDto SYS_ERROR = ResponseDataDto.buildFail(CommonErrorCode.SYS_ERROR_CODE, CommonErrorCode.SYS_ERROR_MSG);

    /**
     * 参数为空
     */
    public static final ResponseDataDto PARAM_NULL = ResponseDataDto.buildFail(CommonErrorCode.PARAM_NULL, CommonErrorCode.PARAM_NULL_MSG);

    /**
     * 参数错误
     */
    public static final ResponseDataDto PARAM_FORMAT_ERROR = ResponseDataDto.buildFail(CommonErrorCode.PARAM_FORMAT_ERROR, CommonErrorCode.PARAM_FORMAT_ERROR_MSG);



//    public static final ResponseDataDto DB_EXECUTE_ERROR = new ResponseDataDto(CommonErrorCode.DB_EXECUTE_ERROR, CommonErrorCode.DB_EXECUTE_ERROR_MSG);

//    public static final ResponseDataDto FLOW_PROCESSOR_GET_ERROR = new ResponseDataDto(CommonErrorCode.FLOW_PROCESSOR_GET_ERROR, CommonErrorCode.FLOW_PROCESSOR_GET_ERROR_MSG);


}
