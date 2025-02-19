package com.saloon.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saloon.modal.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    Set<Category> findBySaloonId(Long saloonId);
}
