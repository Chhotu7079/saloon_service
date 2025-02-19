package com.saloon.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saloon.dto.SaloonDTO;
import com.saloon.modal.Category;
import com.saloon.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories/saloon-owner")
public class SaloonCategoryController {

     private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        SaloonDTO saloonDTO = new SaloonDTO();
        saloonDTO.setId(1L);

        Category savedCategory = categoryService.saveCategory(category, saloonDTO);
        return ResponseEntity.ok(savedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws Exception{
        SaloonDTO saloonDTO = new SaloonDTO();
        saloonDTO.setId(1L);

        categoryService.deleteCategoryById(id, saloonDTO.getId());

        return ResponseEntity.ok("category deleted successfully");
    }

    
}
