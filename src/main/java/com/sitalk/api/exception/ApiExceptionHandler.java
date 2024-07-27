package com.sitalk.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.sitalk.api.dto.ApiResponse;
import com.sitalk.api.enums.ErrorCodeEnum;
/**
 * packageName      : com.teamsoft.api.exception
 * fileName         : ApiExceptionHandler
 * description      :
 * ======================================================
 * DATE                     AUTHOR                  NOTE
 * ======================================================
 * 2023-07-04               최재혁 (9876006)         최초생성
 */

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler<T> extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {

        final String errorMsg = ex.getAllErrors().stream().findFirst().get().getDefaultMessage();
        final String errorCode = ErrorCodeEnum.BAD_REQUEST.getCode();
        final HttpStatus errorStatus = ErrorCodeEnum.BAD_REQUEST.getHttpStatus();
        log.error("[ApiExceptionHandler handleMethodArgumentNotValid] Error Msg : {}", errorMsg);

        ApiResponse response = new ApiResponse(errorCode, errorMsg);
        return new ResponseEntity<>(response, errorStatus);
    }

    /**
     * 제어되는 에러처리, ApiException
     * @param ex ApiException
     * @return ResponseEntity
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex) {

        String stackTrace = ExceptionUtils.getStackTrace(ex);

        if(stackTrace != null)
            log.error("ApiExceptionHandler ApiException: {}", stackTrace);

        final String errorCode = ex.getErrorCode();
        final String errorMsg = ex.getErrorMsg();
        final HttpStatus errorStatus = ex.getHttpStatus();

        ApiResponse response = new ApiResponse(errorCode, errorMsg);
        return new ResponseEntity<>(response, errorStatus);
    }

    /**
     * 제어되지 않는 에러처리, Exception
     * @param ex Exception
     * @return 오류응답
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {

        String stackTrace = ExceptionUtils.getStackTrace(ex);

        if(stackTrace != null)
            log.error("ApiExceptionHandler Exception: {}", stackTrace);

        final String errorCode = ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode();
        final String errorMsg = ExceptionUtils.getRootCauseMessage(ex);
        final HttpStatus errorStatus = ErrorCodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus();

        ApiResponse response = new ApiResponse(errorCode, errorMsg);
        return new ResponseEntity<>(response, errorStatus);
    }

    /**
     * 스프링 Exception
     * 스프링 내부에서 나는 에러(ex: JSON 형식 에러)라서 에러내용만 응답
     */
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.error("[ApiExceptionHandler Spring Internal Error] Reason : {}", ex.getMessage());

        final String errorCode = ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode();
        final String errorMsg = ex.getMessage() != null ? ex.getMessage() : ErrorCodeEnum.INTERNAL_SERVER_ERROR.getMsg();
        final HttpStatus errorStatus = ErrorCodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus();

        ApiResponse response = new ApiResponse(errorCode, errorMsg);
        return new ResponseEntity<>(response, errorStatus);
    }

}
