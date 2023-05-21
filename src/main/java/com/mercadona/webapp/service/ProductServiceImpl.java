package com.mercadona.webapp.service;

import com.mercadona.webapp.model.Product;
import com.mercadona.webapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();

    @Override
    public void saveProduct(MultipartFile file, Product product) throws IOException {
        product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        symbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        product.setPrice(Float.parseFloat(decimalFormat.format(product.getPrice())));
        productRepository.save(product);

    }

    public float reducePrice(float price, float discount) {
        float reducedPrice = price - price * (discount / 100f);
        symbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        return Float.parseFloat(decimalFormat.format(reducedPrice));
    }
    
    public void checkDiscountDate(List<Product> products) {
        for (int ii =0; ii < products.size(); ii++) {
            if(products.get(ii).getDiscount() != 0 &&
                    products.get(ii).getDiscountStart() != null &&
                    products.get(ii).getDiscountEnd() != null){
                LocalDate now = LocalDate.now();
                //Check if today, we are during the period of time of the discount
                if(!now.isAfter(products.get(ii).getDiscountStart().minus(1, ChronoUnit.DAYS)) ||
                        !now.isBefore(products.get(ii).getDiscountEnd().plus(1, ChronoUnit.DAYS))){
                    products.get(ii).setDiscount(0);
                }

            };
        }
    }

    /**
     *
     * @param product
     */

    public void editProduct(Product product) {
        Product productEdit = productRepository.findById(product.getId()).get();
        productEdit.setName(product.getName());
        productEdit.setDescription(product.getDescription());
        if(product.getDiscount() != 0) {
            productEdit.setDiscount(product.getDiscount());
            float reducedPrice = reducePrice(product.getPrice(), product.getDiscount());
            productEdit.setPriceReduced(reducedPrice);
        }
        symbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        productEdit.setPrice(Float.parseFloat(decimalFormat.format(product.getPrice())));
        productEdit.setDiscount(product.getDiscount());
        productEdit.setCategory(product.getCategory());
        productEdit.setDiscountStart(product.getDiscountStart());
        productEdit.setDiscountEnd(product.getDiscountEnd());
        productRepository.save(productEdit);
    }

    public void editProductWithImage(MultipartFile file, Product product) throws IOException {
        editProduct(product);
        Product productEdit = productRepository.findById(product.getId()).get();
        productEdit.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        symbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        product.setPrice(Float.parseFloat(decimalFormat.format(product.getPrice())));
        productRepository.save(productEdit);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }
}
