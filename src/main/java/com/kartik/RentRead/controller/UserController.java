package com.kartik.RentRead.controller;


import com.kartik.RentRead.dto.UserDto;
import com.kartik.RentRead.responseDto.UserResponseDto;
import com.kartik.RentRead.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto registerUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    // Authenticate user
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestParam String email, @RequestParam String password) {
        boolean isAuthenticated = userService.authenticateUser(email, password);

        if (isAuthenticated) {
            return new ResponseEntity<>("Welcome! Authentication successful.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password. Please try again.", HttpStatus.UNAUTHORIZED);
        }
    }
    // Get user by email
    @GetMapping("/email/{email}")
    public UserResponseDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


}
