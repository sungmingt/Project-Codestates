package com.codestates.order;

import com.codestates.coffee.domain.Coffee;
import lombok.Getter;
import javax.persistence.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class OrderCoffee {

    @Id
    @GeneratedValue
    @Column(name = "order_coffee_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    private int orderPrice;
    private int count;


    public OrderCoffee(){}
    protected OrderCoffee(Coffee coffee, int orderPrice, int count) {
        this.coffee = coffee;
        this.orderPrice = orderPrice;
        this.count = count;
    }


    //==========로직===============

    //OrderCoffee 생성  (static 사용이유?? 정적팩토리?)
    public static OrderCoffee createOrderCoffee(Coffee coffee, int orderPrice, int count) {
        coffee.removeStock(count);
        return new OrderCoffee(coffee, orderPrice, count);
    }

    public void setOrder(Order order) {  // order에서 쓰여서 임시생성. 더 나은방법 찾기
        this.order = order;
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }

    public void cancel() {
        getCoffee().addStock(count);
    }
}
