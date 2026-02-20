package com.koerber.order;

import com.koerber.order.controller.OrderController;
import com.koerber.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    @Test
    void shouldPlaceOrder() throws Exception {

        mockMvc.perform(post("/order")
                        .contentType("application/json")
                        .content("""
                                {
                                  "productId": 1001,
                                  "quantity": 5
                                }
                                """))
                .andExpect(status().isOk());
    }
}
