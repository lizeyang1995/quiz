package com.twuc.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
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
    private BigDecimal count;
}
