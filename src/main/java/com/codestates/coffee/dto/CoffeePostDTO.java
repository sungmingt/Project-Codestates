package com.codestates.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class CoffeePostDTO {

    @NotEmpty
    private String engName;

    private String korName;

    @NotEmpty
    private int price;

    @NotEmpty
    private int stockQuantity;

    protected CoffeePostDTO(){}
}
