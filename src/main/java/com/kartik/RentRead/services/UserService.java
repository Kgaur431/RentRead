package com.kartik.RentRead.services;


import com.kartik.RentRead.dto.UserDto;
import com.kartik.RentRead.entities.Role;
import com.kartik.RentRead.entities.UserEntity;
import com.kartik.RentRead.exception.DuplicateUserException;
import com.kartik.RentRead.exception.PasswordMismatchException;
import com.kartik.RentRead.exception.UserNotFoundException;
import com.kartik.RentRead.repositories.UserRepository;
import com.kartik.RentRead.responseDto.UserResponseDto;
import com.kartik.RentRead.utils.DtoToEntity;
import com.kartik.RentRead.utils.EntityToDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final DtoToEntity dtoToEntity;
    private final EntityToDtoMapper entityToDtoMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder,
                       DtoToEntity dtoToEntity, EntityToDtoMapper entityToDtoMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dtoToEntity = dtoToEntity;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    // Register a new user
    //@Transactional
    public UserResponseDto registerUser(UserDto userDto) {
        // Check if user already exists
        userRepository.findByEmail(userDto.getEmail())
                .ifPresent(user -> {
                    throw new DuplicateUserException("User with email " + userDto.getEmail() + " already exists!");
                });

        // Map DTO to Entity
        UserEntity userEntity = dtoToEntity.toUserEntity(userDto);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword())); // Encrypt password

        // Set default role if not provided
        if (userEntity.getRole() == null) {
            userEntity.setRole(Role.USER);
        }

        // Save user and map back to Response DTO
        UserEntity savedUser = userRepository.save(userEntity);
        return entityToDtoMapper.toUserResponseDto(savedUser);
    }

    public boolean authenticateUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                        throw new PasswordMismatchException("Password does not match for the given email.");
                    }
                    return true;
                })
                .orElseThrow(() -> new IllegalArgumentException("User with the given email does not exist."));
    }


    // Get user by email
    public UserResponseDto getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
        return entityToDtoMapper.toUserResponseDto(user);
    }

    // Get user by ID
    public UserResponseDto getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        return entityToDtoMapper.toUserResponseDto(user);
    }
}


/**
 *
 * Correct Approach
 * Accept the UserDto as input in the registerUser method.
 * Map the UserDto to UserEntity.
 * Save the UserEntity in the repository.
 *
 *
 *
 *
 * @Transactional is an annotation in Spring that ensures the method (or class) it is applied to runs inside a transactional context.
 *
 * A transaction is a sequence of operations that are executed as a single unit. If any operation fails, the entire transaction is rolled back to maintain consistency.
 *
 */

