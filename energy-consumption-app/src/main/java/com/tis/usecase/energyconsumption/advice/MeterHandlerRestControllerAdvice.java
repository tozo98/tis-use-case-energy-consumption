package com.tis.usecase.energyconsumption.advice;

import com.tis.usecase.energyconsumption.controller.MeterHandlerRestController;
import com.tis.usecase.energyconsumption.exception.MeterReadingValidationException;
import com.tis.usecase.energyconsumption.exception.ProfileNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes = {MeterHandlerRestController.class})
public class MeterHandlerRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProfileNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<Object> handleProfileNotFoundException(Exception exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MeterReadingValidationException.class)
    public ResponseEntity<Object> handleMeterReadingValidationException(MeterReadingValidationException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleDbRelatedException(SQLIntegrityConstraintViolationException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
