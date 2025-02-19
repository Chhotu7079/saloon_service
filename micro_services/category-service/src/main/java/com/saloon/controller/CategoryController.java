package com.saloon.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saloon.modal.Category;
import com.saloon.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/saloon/{id}")
    public ResponseEntity<Set<Category>> getCategoriesBySaloon(@PathVariable Long id){
        Set<Category> categories = categoryService.getAllCategoriesBySaloon(id);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws Exception{
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

}
