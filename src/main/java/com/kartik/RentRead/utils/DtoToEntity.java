package com.kartik.RentRead.utils;

import com.kartik.RentRead.dto.BookDto;
import com.kartik.RentRead.dto.RentalDto;
import com.kartik.RentRead.dto.UserDto;
import com.kartik.RentRead.entities.BookEntity;
import com.kartik.RentRead.entities.RentalEntity;
import com.kartik.RentRead.entities.Role;
import com.kartik.RentRead.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntity {


    public UserEntity toUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())  // Ensure this is encoded at the service layer
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .role(userDto.getRole() != null ? userDto.getRole() : Role.USER)
                .build();
    }

    public RentalEntity toRentalEntity(RentalDto rentalDto) {
        return RentalEntity.builder()
                .user(toUserEntity(rentalDto.getUser()))
                .book(toBookEntity(rentalDto.getBook()))
                .rentalDate(rentalDto.getRentalDate())
                .returnDate(rentalDto.getReturnDate())
                .build();
    }

    public BookEntity toBookEntity(BookDto bookDto) {
        return BookEntity.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .genre(bookDto.getGenre())
                .availabilityStatus(bookDto.getIsAvailable())
                .build();
    }

}


/**
 *
 *  we have implemented  a builder method to convert a UserDto to a UserEntity in every entity class.
 *
 */
