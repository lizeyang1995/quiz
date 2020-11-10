package com.twuc.shopping.api;

import com.twuc.shopping.domain.CartProduct;
import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.CartProductPo;
import com.twuc.shopping.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity createOrder(@RequestBody List<CartProduct> orders) {
        if (Objects.isNull(orders) || orders.size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        orderService.createOrder(orders);
        return ResponseEntity.created(null).build();
    }
}
