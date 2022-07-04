package com.codestates.order;

import com.codestates.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "order")
    private List<OrderCoffee> orderCoffees = new ArrayList<>();


    @Getter
    public enum OrderStatus {
        ORDER(1, "주문 완료"),
        CANCEL(2, "주문 취소");

        private final int num;
        private final String description;

        OrderStatus(int num, String description) {
            this.num = num;
            this.description = description;
        }
    }

    protected Order(){}
    protected Order(Member member, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime modifiedAt, List<OrderCoffee> orderCoffees) {
        this.member = member;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.orderCoffees = orderCoffees;
    }


    //===========연관관계 메서드============
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderCoffee(OrderCoffee orderCoffee) {
        this.orderCoffees.add(orderCoffee);
        orderCoffee.setOrder(this);
    }


    //===========주문 생성==============
    public static Order createOrder(Member member, OrderCoffee... orderCoffees) {
        return new Order(member, OrderStatus.ORDER, LocalDateTime.now(),
                    LocalDateTime.now(), List.of(orderCoffees));  ///////////리팩토링 필요
    }

    //===========주문 취소============
    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;

        for (OrderCoffee orderCoffee : orderCoffees) {
            orderCoffee.cancel();
        }
    }

    //=======

    //전체 주문 가격 조회
    public int getTotalPrice() {
        return orderCoffees
                .stream()
                .mapToInt(OrderCoffee::getTotalPrice)
                .sum();
    }
}
