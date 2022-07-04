package com.codestates.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoffeeResponseDTO {

    private Long coffeeId;

    private String engName;

    private String korName;

    private int price;

    private int stockQuantity;
}
