package com.twuc.shopping.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.domain.Product;
import com.twuc.shopping.po.ProductPo;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void should_add_product() throws Exception {
        Product product = new Product();
        product.setName("可乐");
        product.setPrice(BigDecimal.valueOf(5.0));
        product.setUnit("瓶");
        product.setUrl("uuu");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(product);
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        List<ProductPo> allProducts = productRepository.findAll();
        assertEquals(1, allProducts.size());
    }

    @Test
    void should_not_add_product_when_product_name_exist() throws Exception {
        Product product = new Product();
        product.setName("可乐");
        product.setPrice(BigDecimal.valueOf(5.0));
        product.setUnit("瓶");
        product.setUrl("uuu");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(product);
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_get_all_product() throws Exception {
        ProductPo productPo = new ProductPo();
        productPo.setName("可乐");
        productPo.setPrice(BigDecimal.valueOf(5));
        productPo.setUnit("瓶");
        productPo.setUrl("uuu");
        productRepository.save(productPo);
        mockMvc.perform(get("/products"))
                .andExpect(jsonPath("$[0].name", is("可乐")))
                .andExpect(jsonPath("$[0].price", is(5.0)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[0].url", is("uuu")))
                .andExpect(status().isOk());
    }
}
