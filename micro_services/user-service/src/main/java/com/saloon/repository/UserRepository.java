package com.saloon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saloon.modal.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
