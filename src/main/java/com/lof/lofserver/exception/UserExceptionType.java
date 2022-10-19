package com.lof.lofserver.exception;

import org.springframework.http.HttpStatus;

public enum UserExceptionType implements BaseExceptionType {

    //==닉네임 설정 시==//
    NICKNAME_ALREADY_EXIST(400, HttpStatus.OK, "이미 존재하는 닉네임입니다."),
    NICKNAME_IS_EMPTY(400, HttpStatus.OK, "닉네임을 입력해주세요."),
    NICKNAME_LENGTH_ERROR(400, HttpStatus.OK, "닉네임은 3자 이상~16자 이내로 입력해주세요."),
    NICKNAME_TYPE_ERROR(400, HttpStatus.OK, "닉네임은 영어, 숫자만 입력 가능합니다."),
    NICKNAME_IS_NOT_ALLOWED(400, HttpStatus.OK, "닉네임에 특수문자는 사용할 수 없습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    UserExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
