package com.codestates.coffee.controller;

import com.codestates.coffee.domain.Coffee;
import com.codestates.coffee.dto.CoffeeListDTO;
import com.codestates.coffee.dto.CoffeePatchDTO;
import com.codestates.coffee.dto.CoffeePostDTO;
import com.codestates.coffee.dto.CoffeeResponseDTO;
import com.codestates.coffee.mapper.CoffeeMapper;
import com.codestates.coffee.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/coffees")
@RequiredArgsConstructor
@Slf4j
public class CoffeeController {

    private final CoffeeService coffeeService;
    private final CoffeeMapper mapper;


    @PostMapping  //커피 생성
    public CoffeeResponseDTO postCoffee(@RequestBody CoffeePostDTO coffeePostDTO) {
        Coffee coffee = mapper.coffeePostDtoToCoffee(coffeePostDTO);

        Coffee createdCoffee = coffeeService.createCoffee(coffee);
        return mapper.coffeeToCoffeeResponseDto(createdCoffee);
    }

    @PatchMapping  //커피정보 수정
    public CoffeeResponseDTO updateCoffee(@RequestBody CoffeePatchDTO coffeePatchDTO) {
//        Coffee coffee = mapper.coffeePatchDtoToCoffee(coffeePatchDTO);
        log.info("engName ={}", coffeePatchDTO.getEngName());
        log.info("coffeeId ={}", coffeePatchDTO.getCoffeeId());

        Coffee coffee = coffeeService.updateCoffee(coffeePatchDTO);
        return mapper.coffeeToCoffeeResponseDto(coffee);
    }

    @GetMapping("/{coffeeId}")  //단일 조회
    public CoffeeResponseDTO getCoffee(@Positive @PathVariable long coffeeId) {

        Coffee coffee = coffeeService.findCoffee(coffeeId);
        return mapper.coffeeToCoffeeResponseDto(coffee);
    }

    @GetMapping   //전체 조회
    public CoffeeListDTO getCoffees() {
        List<Coffee> coffees = coffeeService.findCoffees();
        List<CoffeeResponseDTO> coffeeList = coffees.stream()
                .map(mapper::coffeeToCoffeeResponseDto)
                .collect(Collectors.toList());

        return new CoffeeListDTO(coffeeList);
    }
}
