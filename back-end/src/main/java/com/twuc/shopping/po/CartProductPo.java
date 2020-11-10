package com.twuc.shopping.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "cartList")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductPo {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private BigDecimal price;
    private String unit;
    private BigInteger count;
    @ManyToOne
    private OrderPo orderPo;
}
