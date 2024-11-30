package com.kartik.RentRead.responseDto;

import com.kartik.RentRead.entities.Role;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private Role role;
}
