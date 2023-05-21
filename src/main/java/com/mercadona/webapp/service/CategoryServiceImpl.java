package com.mercadona.webapp.service;

import com.mercadona.webapp.model.Category;
import com.mercadona.webapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    public void saveCategory(Category category){
        categoryRepository.save(category);
    }

    public Category findCategoryByNameCategory(String nameCategory) { return categoryRepository.findByNameCategory(nameCategory); }

    @Override
    public boolean isCategoryNameExist(String categoryName) {
        Category category = categoryRepository.findByNameCategory(categoryName);
        if(category != null){
            return true;
        }
        return false;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
