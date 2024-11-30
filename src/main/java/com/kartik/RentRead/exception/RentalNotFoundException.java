package com.kartik.RentRead.exception;


public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(String message) {
        super(message);
    }
}