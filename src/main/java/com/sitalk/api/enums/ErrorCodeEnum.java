package com.sitalk.api.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName      : com.teamsoft.api.enums
 * fileName         : ErrorCodeEnum
 * description      :
 * ======================================================
 * DATE                     AUTHOR                  NOTE
 * ======================================================
 * 2023-07-04               최재혁 (9876006)         최초생성
 */

@Getter
public enum ErrorCodeEnum {

    OK ("200", "정상 처리되었습니다.", HttpStatus.OK),
    INTERNAL_SERVER_ERROR("500", "서버오류", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("404", "해당 서비스를 찾을수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_SESSION("401", "로그인 확인이 필요합니다.", HttpStatus.UNAUTHORIZED),
    //NOT_FOUND_USER("401", "사용자가 유효하지 않습니다.", HttpStatus.UNAUTHORIZED),

    FORBIDDEN("403", "아이디 혹은 비밀번호가 올바르지 않습니다.", HttpStatus.FORBIDDEN),
    NOT_FOUND_USER("404", "존재하지않는 관리자입니다.", HttpStatus.FORBIDDEN),
    BAN_USER("405", "해당계정은 이용정지 상태입니다 관리자에게 문의해주세요.", HttpStatus.FORBIDDEN),
    BAD_REQUEST("400", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    NO_DATA("502","해당 조회데이터가 없습니다.", HttpStatus.BAD_GATEWAY),

    NOT_VALID("400", "필수값 확인이 필요합니다.", HttpStatus.BAD_REQUEST),
    NOT_ACCEPTABLE("406", "잘못된 미디어타입 요청입니다.", HttpStatus.NOT_ACCEPTABLE),
    UNSUPPORTED_MEDIA_TYPE("415", "지원하지않는 미디어타입 요청입니다.", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    DUPLICATE_VALUE_ERROR("409", "이미 존재하는 값입니다.", HttpStatus.CONFLICT),
    FILE_NOT_FOUND_DIR("500","파일 경로를 찾는데 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    FILE_FAIL_SAVE("500","파일 저장에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_NOT_FOUND("404","파일을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FILE_FAIL_DELETE("500","파일 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final String code;
    private final String msg;
    private final HttpStatus httpStatus;

    ErrorCodeEnum(String code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

}
