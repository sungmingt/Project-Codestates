package com.codestates.coffee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public Coffee findCoffee(Long coffeeId) {
        return coffeeRepository.findCoffee(coffeeId);
    }

    public List<Coffee> findCoffees() {
        return coffeeRepository.findCoffees();
    }
}
