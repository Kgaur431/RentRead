package com.kartik.RentRead.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private String genre;
    private Boolean isAvailable;
}

