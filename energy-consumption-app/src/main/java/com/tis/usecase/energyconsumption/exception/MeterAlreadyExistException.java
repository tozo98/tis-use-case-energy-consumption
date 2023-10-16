package com.tis.usecase.energyconsumption.exception;

public class MeterAlreadyExistException extends RuntimeException {
    public MeterAlreadyExistException(String message) {
        super(message);
    }
}
