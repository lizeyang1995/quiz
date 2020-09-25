package com.twuc.shopping.api;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody @Valid Product product) {
        productService.addProduct(product);
        return ResponseEntity.created(null).build();
    }
}
