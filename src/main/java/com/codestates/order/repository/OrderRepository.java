package com.codestates.order.repository;

import com.codestates.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {  // spring data JPA 적용 x

    private final EntityManager em;


    public void saveOrder(Order order) {
        em.persist(order);
    }

    public Order findOrder(Long orderId) {
        return em.find(Order.class, orderId);
    }

    public List<Order> findOrders() {
        List<Order> orders = em.createQuery("select o from Orders o", Order.class)
                .getResultList();
        return orders;
    }
}
