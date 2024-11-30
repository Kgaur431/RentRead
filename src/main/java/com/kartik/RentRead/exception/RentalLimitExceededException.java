package com.kartik.RentRead.exception;

public class RentalLimitExceededException extends RuntimeException {
    public RentalLimitExceededException(String message) {
        super(message);
    }
}