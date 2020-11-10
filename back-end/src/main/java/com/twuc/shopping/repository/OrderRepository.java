package com.twuc.shopping.repository;

import com.twuc.shopping.po.OrderPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderPo, Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from order_list where id=(select id from ( select MAX(id) id from order_list where name=(:name)) a)", nativeQuery = true)
    void deleteByNameWithMaxId(@Param("name") String name);
    @Override
    List<OrderPo> findAll();
}
