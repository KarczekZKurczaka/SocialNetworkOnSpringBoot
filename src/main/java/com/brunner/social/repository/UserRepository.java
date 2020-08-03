package com.brunner.social.repository;

import com.brunner.social.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long UserId);

    boolean existsByEmail(String username);

    User findByEmail(String email);

    @Query(value = "Select * from user u where u.email=:email", nativeQuery = true)
    Optional<User> getUserByEmail(@Param("email") String email);
}
