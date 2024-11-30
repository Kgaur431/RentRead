package com.kartik.RentRead.dto;

import com.kartik.RentRead.entities.Role;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private  String password;
    private Role role;
}

