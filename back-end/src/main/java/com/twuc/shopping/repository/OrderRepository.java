package com.twuc.shopping.repository;

import com.twuc.shopping.po.OrderPo;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderPo, String> {

}
