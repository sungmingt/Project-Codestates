package com.codestates.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CoffeeRepositoryImpl implements CoffeeRepository{

    @Autowired
    private EntityManager em;


    @Override
    public Coffee findCoffee(Long coffeeId) {
        Coffee coffee = em.find(Coffee.class, coffeeId);
        return coffee;
    }

    @Override
    public List<Coffee> findCoffees() {
        return em.createQuery("select c from Coffee c", Coffee.class)
                .getResultList();
    }
}
