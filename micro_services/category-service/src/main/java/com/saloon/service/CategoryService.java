package com.saloon.service;

import java.util.Set;

import com.saloon.dto.SaloonDTO;
import com.saloon.modal.Category;

public interface CategoryService {

    Category saveCategory(Category category, SaloonDTO saloonDTO);
    Set<Category> getAllCategoriesBySaloon(Long id);
    Category getCategoryById(Long id) throws Exception;
    void deleteCategoryById(Long id, Long saloonId) throws Exception;

}
