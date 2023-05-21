package com.mercadona.webapp.service;

import com.mercadona.webapp.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    public void saveProduct(MultipartFile file, Product product) throws IOException;

    public void editProduct(Product product);

    public void editProductWithImage(MultipartFile file, Product product) throws IOException;

    public List<Product> getAllProducts();

    public Optional<Product> getProductById(long id);

    public void deleteProduct(long id);

}
