package com.kartik.RentRead.dto;

import lombok.Data;

@Data
public class RentalDto {
    private UserDto user;
    private BookDto book;
    private String rentalDate;
    private String returnDate;
}

