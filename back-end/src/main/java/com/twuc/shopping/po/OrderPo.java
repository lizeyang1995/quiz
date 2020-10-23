package com.twuc.shopping.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "orderList")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPo {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private BigDecimal price;
    private String unit;
    private BigDecimal count;
}
