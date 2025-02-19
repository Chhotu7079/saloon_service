package com.saloon.service.Impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.saloon.dto.SaloonDTO;
import com.saloon.modal.Category;
import com.saloon.repository.CategoryRepository;
import com.saloon.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public Category saveCategory(Category category, SaloonDTO saloonDTO) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setSaloonId(saloonDTO.getId());
        newCategory.setImage(category.getImage());

        return categoryRepository.save(newCategory);

    }

    @Override
    public Set<Category> getAllCategoriesBySaloon(Long id) {
        return categoryRepository.findBySaloonId(id);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new Exception("category not exist with id " +id);
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Long id, Long saloonId) throws Exception {
        Category category = getCategoryById(id);
        if (!category.getSaloonId().equals(saloonId)) {
            throw new Exception("you don't have permission to delete this category");
        }
        categoryRepository.deleteById(id);
    }

}
