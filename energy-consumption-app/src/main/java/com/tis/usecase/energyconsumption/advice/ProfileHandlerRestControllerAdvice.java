package com.tis.usecase.energyconsumption.advice;

import com.tis.usecase.energyconsumption.controller.ProfileHandlerRestController;
import com.tis.usecase.energyconsumption.exception.InvalidProfileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = {ProfileHandlerRestController.class})
@Slf4j
public class ProfileHandlerRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidProfileException.class)
    public ResponseEntity<Object> handleInvalidProfileException(InvalidProfileException exception, WebRequest request) {
        log.error("Invalid Profile - returning with 400 Bad request.", exception);
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
