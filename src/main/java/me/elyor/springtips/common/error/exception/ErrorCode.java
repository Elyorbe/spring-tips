package me.elyor.springtips.common.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    RESOURCE_NOT_FOUND("Requested resource doesn't exist", HttpStatus.NOT_FOUND.value()),
    INTERNAL_SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    INVALID_INPUT_VALUE("Invalid input value", HttpStatus.BAD_REQUEST.value()),
    BAD_CREDENTIALS("Username or password is incorrect", HttpStatus.UNAUTHORIZED.value()),

    DUPLICATE_USERNAME("Username is already in use", HttpStatus.CONFLICT.value());

    private final String message;
    private final int status;
}
