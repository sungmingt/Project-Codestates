package com.codestates.order.service;

import com.codestates.coffee.domain.Coffee;
import com.codestates.coffee.repository.CoffeeRepository;
import com.codestates.member.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.order.Order;
import com.codestates.order.OrderCoffee;
import com.codestates.order.dto.OrderPostDTO;
import com.codestates.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final CoffeeRepository coffeeRepository;


    //주문
    public Order order(Long memberId, Long coffeeId, int count) { //하나의 주문에 여러상품이 들어가는데 단일 coffeeId와 count??

        //조회
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Coffee> coffee = coffeeRepository.findById(coffeeId);

        //주문커피 생성
        OrderCoffee orderCoffee = OrderCoffee.createOrderCoffee(coffee.get(), coffee.get().getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member.get(), orderCoffee);
        order.setMember(member.get());  ////
        order.addOrderCoffee(orderCoffee);  ////

        //주문 저장
        orderRepository.saveOrder(order);

        return order;
    }

//    //주문내용 변경
//    public Order updateOrder(Order order) {  //로직을 여기에 작성 or repository에 작성
//        return orderRepository.updateOrder(order);
//    }

    //주문 취소
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository.findOrder(orderId);

        order.cancelOrder();
        return order;   ///////////////////////////// 취소확인 API
    }

    //주문 조회
    @Transactional(readOnly = true)
    public Order findOrder(Long orderId) {
        return orderRepository.findOrder(orderId);
    }

    //전체주문 조회
    @Transactional(readOnly = true)
    public List<Order> findOrders() {
        return orderRepository.findOrders();
    }
}
