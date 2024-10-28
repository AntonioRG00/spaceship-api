package com.antoniorg.spaceship.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.antoniorg.spaceship.domain.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	
    @Query("SELECT u FROM UserModel u WHERE LOWER(u.username) = LOWER(:username)")
    Optional<UserModel> findByUsername(@Param("username") String username);
}
