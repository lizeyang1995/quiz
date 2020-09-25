package com.twuc.shopping.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @PostMapping("/product")
    ResponseEntity addProduct() {
        return ResponseEntity.created(null).build();
    }
}
