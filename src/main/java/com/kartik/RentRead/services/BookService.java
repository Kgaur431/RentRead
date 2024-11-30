package com.kartik.RentRead.services;

import com.kartik.RentRead.dto.BookDto;
import com.kartik.RentRead.entities.BookEntity;
import com.kartik.RentRead.entities.UserEntity;
import com.kartik.RentRead.exception.UnauthorizedAccessException;
import com.kartik.RentRead.repositories.BookRepository;
import com.kartik.RentRead.repositories.UserRepository;
import com.kartik.RentRead.responseDto.BookResponseDto;
import com.kartik.RentRead.utils.DtoToEntity;
import com.kartik.RentRead.utils.EntityToDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final DtoToEntity dtoToEntity;
    private final EntityToDtoMapper entityToDtoMapper;
    private  final UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, DtoToEntity dtoToEntity, EntityToDtoMapper entityToDtoMapper, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.dtoToEntity = dtoToEntity;
        this.entityToDtoMapper = entityToDtoMapper;
        this.userRepository = userRepository;
    }

    // Add a new book (Admin only)
    public BookResponseDto addBook(Long userId, BookDto bookDto) {
        UserEntity currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Ensure that the user is an Admin
        if (!currentUser.getRole().equals("ADMIN")) {
            throw new UnauthorizedAccessException("You are not authorized to add books.");
        }

        BookEntity bookEntity = dtoToEntity.toBookEntity(bookDto);
        BookEntity savedBook = bookRepository.save(bookEntity);
        return entityToDtoMapper.toBookResponseDto(savedBook);
    }

    // Get all available books
    public List<BookResponseDto> getAllAvailableBooks() {
        List<BookEntity> availableBooks = bookRepository.findByAvailabilityStatus(true);
        return availableBooks.stream()
                .map(entityToDtoMapper::toBookResponseDto)
                .toList();
    }

    // Update book details (Admin only)
    public BookResponseDto updateBook(Long userId, BookDto bookDto) {
        UserEntity currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Ensure that the user is an Admin
        if (!currentUser.getRole().equals("ADMIN")) {
            throw new UnauthorizedAccessException("You are not authorized to update books.");
        }

        BookEntity bookEntity = dtoToEntity.toBookEntity(bookDto);
        BookEntity updatedBook = bookRepository.save(bookEntity);
        return entityToDtoMapper.toBookResponseDto(updatedBook);
    }

    // Delete a book (Admin only)
    public String deleteBook(Long userId, Long bookId) {
        UserEntity currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Ensure that the user is an Admin
        if (!currentUser.getRole().equals("ADMIN")) {
            throw new UnauthorizedAccessException("You are not authorized to update books.");
        }
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalArgumentException("Book not found");
        }
        bookRepository.deleteById(bookId);
        return "Book with ID " + bookId + " deleted successfully.";
    }

    // Get book details by ID
    public BookResponseDto getBookById(Long bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return entityToDtoMapper.toBookResponseDto(bookEntity);
    }
}
