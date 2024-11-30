package com.kartik.RentRead.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String genre;

    @Column(name = "availability_status", nullable = false)
    private Boolean availabilityStatus;

    public BookEntity() {
    }

    public void setAvailable(boolean b) {
        this.availabilityStatus = b;
    }

    public boolean isAvailabilityStatus() {
        return availabilityStatus;
    }
    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

}


/**
 *
 *
 * Used @Entity for database mapping.
 * Used @Data from Lombok to reduce boilerplate code for getters/setters.
 *
 *
 * Entities handle persistence (database mapping).
 * DTOs handle data transfer between layers.
 */