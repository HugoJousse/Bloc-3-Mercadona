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

    /**
     * Save a new category
     * @param category
     */
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }

    /**
     * Find a category by a name and return it
     * @param nameCategory
     * @return Category object
     */
    public Category findCategoryByNameCategory(String nameCategory) { return categoryRepository.findByNameCategory(nameCategory); }

    /**
     * Check if the name of the category already exist
     * @param categoryName
     * @return boolean
     */
    @Override
    public boolean isCategoryNameExist(String categoryName) {
        Category category = categoryRepository.findByNameCategory(categoryName);
        if(category != null){
            return true;
        }
        return false;
    }

    /**
     * get a list of all Categories
     * @return List<Category>
     */
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
