package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.po.ProductPo;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductServiceTest {
    ProductService productService;
    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    void should_add_success() {
        Product product = new Product();
        product.setName("可乐");
        product.setPrice(BigDecimal.valueOf(5));
        product.setUnit("瓶");
        product.setUrl("uuu");
        ProductPo productPo = new ProductPo();
        productPo.setName(product.getName());
        productPo.setPrice(product.getPrice());
        productPo.setUnit(product.getUnit());
        productPo.setUrl(product.getUrl());
        productService.addProduct(product);
        verify(productRepository).save(productPo);
    }
}
