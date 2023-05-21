package com.mercadona.webapp.service;

import com.mercadona.webapp.model.Category;

import java.util.List;

public interface CategoryService {

    public void saveCategory(Category category);

    public boolean isCategoryNameExist(String categoryName);

    public Category findCategoryByNameCategory(String nameCategory);

    public List<Category> getAll();
}
