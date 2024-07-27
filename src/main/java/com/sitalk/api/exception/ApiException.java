package com.sitalk.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.sitalk.api.enums.ErrorCodeEnum;

/**
 * packageName      : com.teamsoft.api.exception
 * fileName         : ApiException
 * description      :
 * ======================================================
 * DATE                     AUTHOR                  NOTE
 * ======================================================
 * 2023-07-04               최재혁 (9876006)         최초생성
 */

@Getter
public class ApiException extends RuntimeException{

    private final String errorCode;
    private final String errorMsg;
    private final HttpStatus httpStatus;

    public ApiException(ErrorCodeEnum errEnum, String errMsg) {
        super();
        this.errorCode = errEnum.getCode();
        this.errorMsg = errMsg;
        this.httpStatus = errEnum.getHttpStatus();
    }

    public ApiException(ErrorCodeEnum errorCodeEnum) {
        super();
        this.errorCode = errorCodeEnum.getCode();
        this.errorMsg = errorCodeEnum.getMsg();
        this.httpStatus = errorCodeEnum.getHttpStatus();
    }

    public ApiException(String errorCode, String errorMsg, HttpStatus httpStatus) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.httpStatus = httpStatus;
    }

    public ApiException(Throwable ex) {
        super(ex);
        this.errorCode = ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode();
        this.errorMsg = ex.getMessage();
        this.httpStatus = ErrorCodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus();
    }

    public ApiException(ErrorCodeEnum errEnum, Throwable ex) {
        super(ex);
        this.errorCode = errEnum.getCode();
        this.errorMsg = ex.getMessage();
        this.httpStatus = errEnum.getHttpStatus();
    }

}
