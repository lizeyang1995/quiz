package com.twuc.shopping.service;

import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.OrderPo;
import com.twuc.shopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<OrderPo> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(
                        item -> {
                            OrderPo orderPo = new OrderPo();
                            orderPo.setName(item.getName());
                            orderPo.setPrice(item.getPrice());
                            orderPo.setUnit(item.getUnit());
                            orderPo.setCount(item.getCount());
                            return orderPo;
                        })
                .collect(Collectors.toList());
    }

    public void deleteOrder(String productName) {
        orderRepository.deleteByNameWithMaxId(productName);
    }

    public void deleteOrders(String productName) {

    }

    public void deleteAllOrders() {

    }
}
