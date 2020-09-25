package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.po.ProductPo;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        ProductPo productPo = new ProductPo();
        productPo.setName(product.getName());
        productPo.setPrice(product.getPrice());
        productPo.setUnit(product.getUnit());
        productPo.setUrl(product.getUrl());
        productRepository.save(productPo);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(
                        item -> {
                            Product product = new Product();
                            product.setName(item.getName());
                            product.setPrice(item.getPrice());
                            product.setUnit(item.getUnit());
                            product.setUrl(item.getUrl());
                            return product;
                        })
                .collect(Collectors.toList());
    }
}
