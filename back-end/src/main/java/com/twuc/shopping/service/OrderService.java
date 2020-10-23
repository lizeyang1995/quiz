package com.twuc.shopping.service;

import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.OrderPo;
import com.twuc.shopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void addOrder(Order order) {
        OrderPo orderPo = OrderPo.builder()
                .name(order.getName())
                .price(order.getPrice())
                .unit(order.getUnit())
                .count(order.getCount()).build();
        orderRepository.save(orderPo);
    }
}
