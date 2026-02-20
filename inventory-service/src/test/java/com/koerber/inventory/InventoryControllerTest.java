package com.koerber.inventory;

import com.koerber.inventory.controller.InventoryController;
import com.koerber.inventory.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService service;

    @Test
    void shouldUpdateInventory() throws Exception {

        mockMvc.perform(post("/inventory/update")
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
