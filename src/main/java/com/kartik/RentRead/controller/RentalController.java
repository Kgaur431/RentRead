package com.kartik.RentRead.controller;

import com.kartik.RentRead.dto.RentalDto;
import com.kartik.RentRead.responseDto.RentalResponseDto;
import com.kartik.RentRead.services.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // Rent a book
    @PostMapping("/{bookId}/rent")
    @ResponseStatus(HttpStatus.CREATED)
    public RentalResponseDto rentBook(@RequestBody RentalDto rentalDto) {
        return rentalService.rentBook(rentalDto);
    }

    // Return a book
    @PostMapping("/{bookId}/return")
    @ResponseStatus(HttpStatus.OK)
    public void returnBook(@PathVariable Long rentalid, @PathVariable Long userId) {
        rentalService.returnBook(rentalid, userId);
    }

    // Get active rentals
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RentalResponseDto>> getActiveRentals(@PathVariable Long userId) {
        return ResponseEntity.ok(rentalService.getActiveRentals(userId));
    }
}
