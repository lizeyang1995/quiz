package com.twuc.shopping.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.domain.CartProduct;
import com.twuc.shopping.po.CartProductPo;
import com.twuc.shopping.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CartRepository cartRepository;
    @BeforeEach
    void setUp() {
        cartRepository.deleteAll();
    }

    @Test
    void should_add_a_order() throws Exception {
        CartProduct order = CartProduct.builder()
                            .name("可乐")
                            .price(BigDecimal.valueOf(5.00))
                            .unit("瓶")
                            .count(BigInteger.valueOf(1))
                            .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);
        mockMvc.perform(post("/cartProducts").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_throw_error_when_give_null() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(null);
        mockMvc.perform(post("/cartProducts").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_throw_error_when_value_is_null() throws Exception {
        CartProduct order = CartProduct.builder()
                .name("可乐")
                .price(BigDecimal.valueOf(5.00))
                .unit(null)
                .count(BigInteger.valueOf(1))
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);
        mockMvc.perform(post("/cartProducts").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_get_all_orders() throws Exception {
        CartProductPo order = CartProductPo.builder()
                .name("可乐")
                .price(BigDecimal.valueOf(5.00))
                .unit("瓶")
                .count(BigInteger.valueOf(1))
                .build();
        cartRepository.save(order);
        mockMvc.perform(get("/cartProducts"))
                .andExpect(jsonPath("$[0].name", is("可乐")))
                .andExpect(jsonPath("$[0].price", is(5.00)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[0].count", is(1)))
                .andExpect(status().isOk());
    }
}
