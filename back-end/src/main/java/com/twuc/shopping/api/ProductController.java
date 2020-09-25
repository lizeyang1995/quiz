package com.twuc.shopping.api;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody @Valid Product product) {
        productService.addProduct(product);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/products")
    public ResponseEntity getAllProduct() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
