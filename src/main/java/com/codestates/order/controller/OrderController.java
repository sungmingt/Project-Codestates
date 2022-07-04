package com.codestates.order.controller;

import com.codestates.order.Order;
import com.codestates.order.dto.OrderResponseDTO;
import com.codestates.order.mapper.OrderMapper;
import com.codestates.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper mapper;


    @PostMapping
    public ResponseEntity postOrder(//@RequestBody OrderPostDTO orderPostDTO) { //하나의 주문에 여러개의 상품이 있을수 있고, 상품별 개수가 다르다.
                                    @RequestParam Long memberId,
                                    @RequestParam Long coffeeId,
                                    @RequestParam int count){
//        for (Long key : orderPostDTO) {
//            Integer value = orderPostDTO.getCoffeeInfo().get(key);
//            orderService.order(orderPostDTO.getMemberId(), key, value);
//        }
        Order order = orderService.order(memberId, coffeeId, count);

        OrderResponseDTO response = mapper.orderToOrderResponseDTO(order);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") long orderId) {
        System.out.println("# orderId: " + orderId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        System.out.println("# get Orders");

        return new ResponseEntity(HttpStatus.OK);
    }
}
