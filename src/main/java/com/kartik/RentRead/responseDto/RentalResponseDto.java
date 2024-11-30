package com.kartik.RentRead.responseDto;

import lombok.Data;

@Data
public class RentalResponseDto {
    private Long id;
    private UserResponseDto user;
    private BookResponseDto book;
    private String rentalDate;
    private String returnDate;
}
