package com.tis.usecase.energyconsumption.advice;

import com.tis.usecase.energyconsumption.exception.InvalidProfileException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProfileHandlerRestControllerAdvice  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidProfileException.class)
    public ResponseEntity<Object> handleInvalidProfileException(InvalidProfileException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
