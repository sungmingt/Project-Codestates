package com.codestates.order.dto;

import com.codestates.order.Order.OrderStatus;
import com.codestates.order.OrderCoffee;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponseDTO {


    private Long orderId;

    private OrderStatus orderStatus;

    private LocalDateTime createdAt;

    private List<OrderCoffee> orderCoffees;
}
