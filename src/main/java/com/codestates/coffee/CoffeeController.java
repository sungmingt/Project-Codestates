package com.codestates.coffee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/coffees")
@RequiredArgsConstructor
@Slf4j
public class CoffeeController {

    private final CoffeeService coffeeService;

//    @PostMapping
//    public Coffee postCoffee(@RequestParam String engName,
//                                     @RequestParam String korName,
//                                     @RequestParam long price) {
//        System.out.println("# engName = " + engName);
//        System.out.println("# korName = " + korName);
//        System.out.println("# price = " + price);
//
//        return new Coffee(engName, korName, price, equals());
//    }

    @GetMapping("/{coffeeId}")
    public Coffee getCoffee(@PathVariable @Positive long coffeeId) {
        log.info("coffeeId ={}", coffeeId);

        Coffee coffee = coffeeService.findCoffee(coffeeId);
        return coffee;
    }

    @GetMapping
    public List<Coffee> getCoffees() {
        List<Coffee> coffees = coffeeService.findCoffees();
        return coffees;
    }
}
