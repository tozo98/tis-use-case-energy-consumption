package com.tis.usecase.energyconsumption.advice;

import com.tis.usecase.energyconsumption.controller.MeterHandlerRestController;
import com.tis.usecase.energyconsumption.exception.MeterAlreadyExistException;
import com.tis.usecase.energyconsumption.exception.MeterReadingValidationException;
import com.tis.usecase.energyconsumption.exception.ProfileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes = {MeterHandlerRestController.class})
@Slf4j
public class MeterHandlerRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProfileNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<Object> handleProfileNotFoundException(Exception exception, WebRequest request) {
        log.error("Profile not found - returning with 400 - Bad request.", exception);
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MeterReadingValidationException.class)
    public ResponseEntity<Object> handleMeterReadingValidationException(MeterReadingValidationException exception, WebRequest request) {
        log.error("Error in validation - returning with 400 - Bad request.", exception);
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MeterAlreadyExistException.class)
    public ResponseEntity<Object> handleMeterAlreadyExistException(MeterAlreadyExistException exception, WebRequest request) {
        log.error("Meter already exists in the database - returning with 400 - Bad request.", exception);
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
