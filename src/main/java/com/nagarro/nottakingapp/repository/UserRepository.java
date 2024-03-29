package com.nagarro.nottakingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.nottakingapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

	Optional<User> findByFullName(String fullname);
	Optional<User> findByEmail (String email);

}
