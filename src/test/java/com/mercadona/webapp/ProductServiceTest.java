package com.mercadona.webapp;


import com.mercadona.webapp.repository.ProductRepository;
import com.mercadona.webapp.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testReducePrice() {
        float price = 50.00f;
        float discount = 50.00f;

        float result = productService.reducePrice(price, discount);

        assertEquals(25.00f, result);

    }
}
