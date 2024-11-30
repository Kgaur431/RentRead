package com.kartik.RentRead.controller;


import com.kartik.RentRead.dto.BookDto;
import com.kartik.RentRead.responseDto.BookResponseDto;
import com.kartik.RentRead.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Get all books
    @GetMapping("/")
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllAvailableBooks();
    }

    // Create a new book
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto createBook(@RequestBody BookDto bookDto, @PathVariable Long userId) {
        return bookService.addBook(userId, bookDto);
    }

    // Update book details
    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable Long userId, @RequestBody BookDto bookDto) {
        return bookService.updateBook(userId, bookDto);
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long bookId, @PathVariable Long userId) {
        bookService.deleteBook(userId, bookId);
    }

    // Get unique book
    @GetMapping("/{id}")
    public BookResponseDto getBook(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

}
