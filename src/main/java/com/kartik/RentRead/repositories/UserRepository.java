package com.kartik.RentRead.repositories;

import com.kartik.RentRead.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Custom method to find a user by their email
    Optional<UserEntity> findByEmail(String email);
}


/**
 *
 *
 * Spring Data JPA provides an automatic implementation of the repository interface.
 * No need to write an implementation class for the repository, Spring will handle it for us.
 * When we call a method like findByEmail(), Spring Data JPA will generate the implementation code dynamically at runtime using proxy classes.
 *  the custom methods we added to the repositories, Spring Data JPA handles them automatically as well.
 *  Spring Data JPA dynamically implements these methods at runtime based on the naming convention or custom queries
 *         (if you use @Query annotations, but we’re not doing that in this case).
 */
