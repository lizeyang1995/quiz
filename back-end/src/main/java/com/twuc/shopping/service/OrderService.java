package com.twuc.shopping.service;

import com.twuc.shopping.domain.CartProduct;
import com.twuc.shopping.po.CartProductPo;
import com.twuc.shopping.po.OrderPo;
import com.twuc.shopping.repository.CartRepository;
import com.twuc.shopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public void createOrder(List<CartProduct> orders) {
        List<CartProductPo> cartProductPos = new ArrayList<>();
        for (CartProduct cartProduct : orders) {
            CartProductPo cartProductPo = CartProductPo.builder()
                    .name(cartProduct.getName())
                    .price(cartProduct.getPrice())
                    .unit(cartProduct.getUnit())
                    .count(cartProduct.getCount()).build();
            cartProductPos.add(cartProductPo);
        }
        OrderPo orderPo = OrderPo.builder().cartProductPos(cartProductPos).build();
        orderRepository.save(orderPo);
        updateCartProduct(orderPo);
    }

    private void updateCartProduct(OrderPo orderPo) {
        List<CartProductPo> allCartProduct = cartRepository.findAll();
        for (CartProductPo cartProductPo : allCartProduct) {
            cartProductPo.setOrderPo(orderPo);
            cartRepository.save(cartProductPo);
        }
    }
}
