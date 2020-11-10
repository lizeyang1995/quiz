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
public class CartProduct {
    @NotNull
    @Valid
    private String name;
    @NotNull
    @Valid
    private BigDecimal price;
    @NotNull
    @Valid
    private String unit;
    @NotNull
    @Valid
    private BigInteger count;
}
