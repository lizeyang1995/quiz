package com.twuc.shopping.repository;

import com.twuc.shopping.po.CartProductPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends CrudRepository<CartProductPo, Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from cart_list where id=(select id from ( select MAX(id) id from cart_list where name=(:name) and order_po_id is null) a)", nativeQuery = true)
    void reduceByNameWithMaxId(@Param("name") String name);
    @Modifying
    @Transactional
    @Query(value = "delete from cart_list where name=(:name) and order_po_id is null", nativeQuery = true)
    void deleteByName(@Param("name") String name);
    @Override
    @Query(value = "select * from cart_list where order_po_id is null", nativeQuery = true)
    List<CartProductPo> findAll();
    @Modifying
    @Transactional
    @Query(value = "delete from cart_list where order_po_id is null", nativeQuery = true)
    void deleteAllProducts();
}
