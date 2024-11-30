package com.kartik.RentRead.services;


import com.kartik.RentRead.dto.RentalDto;
import com.kartik.RentRead.entities.BookEntity;
import com.kartik.RentRead.entities.RentalEntity;
import com.kartik.RentRead.entities.Role;
import com.kartik.RentRead.entities.UserEntity;
import com.kartik.RentRead.exception.BookUnavailableException;
import com.kartik.RentRead.exception.RentalLimitExceededException;
import com.kartik.RentRead.exception.UnauthorizedAccessException;
import com.kartik.RentRead.repositories.BookRepository;
import com.kartik.RentRead.repositories.RentalRepository;
import com.kartik.RentRead.repositories.UserRepository;
import com.kartik.RentRead.responseDto.RentalResponseDto;
import com.kartik.RentRead.utils.DtoToEntity;
import com.kartik.RentRead.utils.EntityToDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final DtoToEntity dtoToEntity;
    private final EntityToDtoMapper entityToDtoMapper;

    @Autowired
    public RentalService(RentalRepository rentalRepository, UserRepository userRepository,
                         BookRepository bookRepository, DtoToEntity dtoToEntity,
                         EntityToDtoMapper entityToDtoMapper) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.dtoToEntity = dtoToEntity;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    //@Transactional
    public RentalResponseDto rentBook(RentalDto rentalDto) {
        // Convert DTO to Entity
        RentalEntity rentalEntity = dtoToEntity.toRentalEntity(rentalDto);
        // Fetch user and book entities from database
        UserEntity user = userRepository.findById(rentalEntity.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        BookEntity book = bookRepository.findById(rentalEntity.getBook().getId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        // Check if the user is allowed to rent the book
        if (user.getRole() != Role.ADMIN) {
            throw new UnauthorizedAccessException("You are not authorized to rent books");
        }
        // Check if the user has exceeded rental limit
        Long activeRentals = rentalRepository.countByUserAndReturnDateIsNull(user);
        if (activeRentals >= 2) {
            throw new RentalLimitExceededException("You cannot rent more than 2 books at a time.");
        }

        // Check if the book is available
        if (!book.getAvailabilityStatus()) {
            throw new BookUnavailableException("The requested book is currently unavailable.");
        }
        // Update book availability and save rental
        book.setAvailable(false);
        bookRepository.save(book);

        rentalEntity.setUser(user);
        rentalEntity.setBook(book);
        rentalEntity.setRentDate(LocalDate.now().toString());
        rentalEntity.setReturnDate(null);

        RentalEntity savedRental = rentalRepository.save(rentalEntity);
        return entityToDtoMapper.toRentalResponseDto(savedRental);
    }



    //@Transactional
    public RentalResponseDto returnBook(Long rentalId, Long userId) {
        // Fetch rental by ID
        RentalEntity rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental record not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check if the rental belongs to the user or if the user is an admin
        if (!rental.getUser().getId().equals(userId) && !user.getRole().equals(Role.ADMIN)) {
            throw new UnauthorizedAccessException("You are not authorized to return this book");
        }


        // Set the return date
        rental.setReturnDate(LocalDate.now().toString());       //  We should set it to the current date using LocalDate.now().
        rental.getBook().setAvailable(true); // Mark the book as available again
        bookRepository.save(rental.getBook()); // Save the updated book

        rentalRepository.save(rental); // Save the updated rental

        // Return the DTO
        return entityToDtoMapper.toRentalResponseDto(rental);
    }

    public List<RentalResponseDto> getActiveRentals(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<RentalEntity> activeRentals = rentalRepository.findByUserAndReturnDateIsNull(user);
        return activeRentals.stream()
                .map(entityToDtoMapper::toRentalResponseDto)
                .toList();
    }
}

