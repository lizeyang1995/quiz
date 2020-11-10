package com.twuc.shopping.api;

import com.twuc.shopping.domain.Order;
import com.twuc.shopping.domain.Product;
import com.twuc.shopping.po.OrderPo;
import com.twuc.shopping.repository.OrderRepository;
import com.twuc.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity addOrder(@RequestBody @Valid Order order) {
        if (Objects.isNull(order)) {
            throw new RuntimeException();
        }
        orderService.addOrder(order);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/orders")
    public ResponseEntity getAllOrders() {
        List<OrderPo> allOrders = orderService.findAllOrders();
        return ResponseEntity.ok(allOrders);
    }

    @DeleteMapping("/order/{productName}")
    public ResponseEntity deleteOrder(@PathVariable String productName, @RequestParam(required = false) boolean allProduct) {
        if (allProduct) {
            orderService.deleteProduct(productName);
        } else {
            orderService.reduceProduct(productName);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/orders")
    public ResponseEntity deleteAllOrders() {
        orderService.deleteAllOrders();
        return ResponseEntity.noContent().build();
    }
}
