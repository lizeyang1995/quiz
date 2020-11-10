package com.twuc.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class Order {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String unit;
    @NotNull
    private BigInteger count;
}
