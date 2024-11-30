package com.kartik.RentRead.repositories;

import com.kartik.RentRead.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByAvailabilityStatus(boolean b);
}
