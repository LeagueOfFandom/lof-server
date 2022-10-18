package com.lof.lofserver.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
    public abstract int getErrorCode();
    public abstract HttpStatus getHttpStatus();
    public abstract String getErrorMessage();
}
