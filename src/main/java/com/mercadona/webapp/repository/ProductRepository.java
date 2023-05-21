package com.mercadona.webapp.repository;

import com.mercadona.webapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(long id);

    void deleteById(long id);
}
