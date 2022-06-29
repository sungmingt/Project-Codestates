package com.codestates.coffee;

import java.util.List;

public interface CoffeeRepository {

    Coffee findCoffee(Long coffeeId);
    List<Coffee> findCoffees();
}
