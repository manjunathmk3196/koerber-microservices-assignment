package com.koerber.order;

import com.koerber.order.client.InventoryClient;
import com.koerber.order.dto.OrderRequest;
import com.koerber.order.entity.Order;
import com.koerber.order.repository.OrderRepository;
import com.koerber.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private InventoryClient inventoryClient;

    @InjectMocks
    private OrderService service;

    @Test
    void shouldPlaceOrderSuccessfully() {

        OrderRequest request = new OrderRequest();
        request.setProductId(1001L);
        request.setQuantity(5);

        when(repository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        var response = service.placeOrder(request);

        assertEquals("PLACED", response.getStatus());
        verify(inventoryClient).updateInventory(1001L, 5);
    }
}
