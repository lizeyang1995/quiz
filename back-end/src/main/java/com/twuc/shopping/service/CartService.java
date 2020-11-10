package com.twuc.shopping.service;

import com.twuc.shopping.domain.CartProduct;
import com.twuc.shopping.po.CartProductPo;
import com.twuc.shopping.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public void addOrder(CartProduct order) {
        CartProductPo cartProductPo = CartProductPo.builder()
                .name(order.getName())
                .price(order.getPrice())
                .unit(order.getUnit())
                .count(order.getCount()).build();
        cartRepository.save(cartProductPo);
    }

    public List<CartProductPo> findAllOrders() {
        return cartRepository.findAll().stream()
                .map(
                        item -> {
                            CartProductPo cartProductPo = new CartProductPo();
                            cartProductPo.setName(item.getName());
                            cartProductPo.setPrice(item.getPrice());
                            cartProductPo.setUnit(item.getUnit());
                            cartProductPo.setCount(item.getCount());
                            return cartProductPo;
                        })
                .collect(Collectors.toList());
    }

    public void reduceProduct(String productName) {
        cartRepository.reduceByNameWithMaxId(productName);
    }

    public void deleteAllOrders() {
        cartRepository.deleteAllProducts();
    }

    public void deleteProduct(String productName) {
        cartRepository.deleteByName(productName);
    }
}
