package com.kartik.RentRead.utils;

import com.kartik.RentRead.entities.BookEntity;
import com.kartik.RentRead.entities.RentalEntity;
import com.kartik.RentRead.entities.UserEntity;
import com.kartik.RentRead.responseDto.BookResponseDto;
import com.kartik.RentRead.responseDto.RentalResponseDto;
import com.kartik.RentRead.responseDto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoMapper {

    // UserEntity -> UserResponseDto
    public UserResponseDto toUserResponseDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserResponseDto dto = new UserResponseDto();
        dto.setId(userEntity.getId());
        dto.setEmail(userEntity.getEmail());
        dto.setRole(userEntity.getRole());
        return dto;
    }

    // BookEntity -> BookResponseDto
    public BookResponseDto toBookResponseDto(BookEntity bookEntity) {
        if (bookEntity == null) {
            return null;
        }
        BookResponseDto dto = new BookResponseDto();
        dto.setId(bookEntity.getId());
        dto.setTitle(bookEntity.getTitle());
        dto.setAuthor(bookEntity.getAuthor());
        dto.setGenre(bookEntity.getGenre());
        dto.setAvailabilityStatus(bookEntity.getAvailabilityStatus());
        return dto;
    }

    // RentalEntity -> RentalResponseDto
    public RentalResponseDto toRentalResponseDto(RentalEntity rentalEntity) {
        if (rentalEntity == null) {
            return null;
        }
        RentalResponseDto dto = new RentalResponseDto();
        dto.setId(rentalEntity.getId());
        dto.setUser(toUserResponseDto(rentalEntity.getUser()));
        dto.setBook(toBookResponseDto(rentalEntity.getBook()));
        dto.setRentalDate(rentalEntity.getRentalDate());
        dto.setReturnDate(rentalEntity.getReturnDate());
        return dto;
    }

}
