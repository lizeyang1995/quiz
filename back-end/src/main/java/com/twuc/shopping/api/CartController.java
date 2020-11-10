package com.twuc.shopping.api;

import com.twuc.shopping.domain.CartProduct;
import com.twuc.shopping.po.CartProductPo;
import com.twuc.shopping.repository.CartRepository;
import com.twuc.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;

    @PostMapping("/cartProducts")
    public ResponseEntity addOrder(@RequestBody @Valid CartProduct order) {
        if (Objects.isNull(order)) {
            throw new RuntimeException();
        }
        cartService.addOrder(order);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/cartProducts")
    public ResponseEntity getAllOrders() {
        List<CartProductPo> allOrders = cartService.findAllOrders();
        return ResponseEntity.ok(allOrders);
    }

    @DeleteMapping("/cartProducts/{productName}")
    public ResponseEntity deleteOrder(@PathVariable String productName, @RequestParam(required = false) boolean allProduct) {
        if (allProduct) {
            cartService.deleteProduct(productName);
        } else {
            cartService.reduceProduct(productName);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cartProducts")
    public ResponseEntity deleteAllOrders() {
        cartService.deleteAllOrders();
        return ResponseEntity.noContent().build();
    }
}
