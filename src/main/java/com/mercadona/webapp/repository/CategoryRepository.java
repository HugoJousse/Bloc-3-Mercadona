package com.mercadona.webapp.repository;

import com.mercadona.webapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNameCategory(String nameCategory);
}
