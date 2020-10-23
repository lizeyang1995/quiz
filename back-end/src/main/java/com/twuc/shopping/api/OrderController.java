package com.twuc.shopping.api;

import com.twuc.shopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
}
