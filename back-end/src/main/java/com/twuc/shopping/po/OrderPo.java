package com.twuc.shopping.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "orderList")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "orderPo")
    private List<CartProductPo> cartProductPos;
}
