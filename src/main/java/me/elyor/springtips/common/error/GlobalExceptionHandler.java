package me.elyor.springtips.common.error;

import me.elyor.springtips.common.error.exception.ErrorCode;
import me.elyor.springtips.common.error.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     *  Handles all the business logic exceptions
     */
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> globalHandler(GlobalException exception) {
        logger.error("GlobalException", exception);
        final ErrorResponse errorResponse = exception.getErrorResponse();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }

    /**
     *  Handles the cases for javax.validation.Valid or @Validated
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        logger.error("MethodArgumentNotValidException", ex);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, ex.getBindingResult());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }
}
