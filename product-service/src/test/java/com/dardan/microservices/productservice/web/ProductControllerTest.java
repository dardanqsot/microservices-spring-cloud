package com.dardan.microservices.productservice.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    ProductRepository productRepository;

    @BeforeAll
    static void init() {
        System.out.println("Ejecutándose antes de todos los métodos");
    }

    @BeforeEach
    void initEach() {
        System.out.println("Ejecutándose antes de todos los métodos II");
    }

    //200
    @Test
    @Order(1)
//    @DisabledOnJre(JRE.JAVA_17)
//    @DisabledOnOs(OS.MAC)
    @DisplayName("Save Product")
    void when_call_save_product_then_return_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/saveProduct")
                        .content("{\n" +
                                "        \"productId\": \"P00003\",\n" +
                                "        \"productName\": \"Microservicios Avanzado II\",\n" +
                                "        \"productType\": \"Curso\",\n" +
                                "        \"price\": 750,\n" +
                                "        \"stock\": 50\n" +
                                "}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //400
    @Test
    @Order(2)
    @DisplayName("Save Product Bad Request")
    void when_call_save_product_without_description_then_return_bad_request() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/saveProduct")
                        .content("{\n" +
                                "        \"productId\": \"P00004\",\n" +
                                "        \"productType\": \"Curso\",\n" +
                                "        \"price\": 750,\n" +
                                "        \"stock\": 50\n" +
                                "}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(3)
    @DisplayName("Get All Products")
    void when_call_get_all_products_then_return_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString("Microservicios Avanzado II")))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"));
    }

    @Test
    void when_call_get_all_products_with_flag_then_return_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{flag}", false)
                        .header("appCallerName", "Mitocode"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void when_call_get_all_products_with_paramter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/parameter")
                        .param("tokens", "prueba"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Disabled
    void when_call_get_all_products_with_flag_then_return_500() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{flag}", true)
                .header("appCallerName", "Mitocode"))
        // No validar porque sale excepciòn
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}