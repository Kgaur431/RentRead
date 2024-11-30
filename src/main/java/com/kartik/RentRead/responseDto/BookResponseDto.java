package com.kartik.RentRead.responseDto;

import lombok.Data;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private Boolean availabilityStatus;
}
