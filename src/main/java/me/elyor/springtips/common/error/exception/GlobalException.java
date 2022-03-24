package me.elyor.springtips.common.error.exception;

import lombok.Getter;
import me.elyor.springtips.common.error.ErrorResponse;

@Getter
public class GlobalException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public GlobalException(ErrorCode errorCode) {
        this.errorResponse = ErrorResponse.of(errorCode);
    }
}
