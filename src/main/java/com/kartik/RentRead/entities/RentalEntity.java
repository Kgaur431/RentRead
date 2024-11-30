package com.kartik.RentRead.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "rentals")
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Column(nullable = false)
    private String rentalDate;

    @Column
    private String returnDate;

    public RentalEntity() {

    }

    public UserEntity getUser() {
        return user;
    }

    public BookEntity getBook() {
        return book;
    }

public  void setUser (UserEntity user) {
    this.user = user;
}

public void setBook(BookEntity book) {
        this.book = book;
}

    public void setRentDate(String now) {
        this.rentalDate = now;
    }

    public String getRentDate() {
        return rentalDate;
    }
}
