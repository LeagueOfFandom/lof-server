package com.lof.lofserver.exception;

import lombok.Getter;

@Getter
public class UserException extends BaseException {
    private UserExceptionType exceptionType;

    public UserException(UserExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
