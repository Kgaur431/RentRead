package com.kartik.RentRead.repositories;

import com.kartik.RentRead.entities.BookEntity;
import com.kartik.RentRead.entities.RentalEntity;
import com.kartik.RentRead.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
    // Custom method to find active rentals by a specific user (rentals with no return date)
    List<RentalEntity> findByUserAndReturnDateIsNull(UserEntity user);

    long  countByUserAndReturnDateIsNull(UserEntity user);
}
