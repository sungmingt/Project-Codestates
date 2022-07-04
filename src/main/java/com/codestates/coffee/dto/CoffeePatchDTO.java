package com.codestates.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class CoffeePatchDTO {

    private Long coffeeId;

    private String engName;

    private String korName;

    private int price;

    private int stockQuantity;

    public CoffeePatchDTO(){}
}
