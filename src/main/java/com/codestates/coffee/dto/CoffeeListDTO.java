package com.codestates.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoffeeListDTO<T> {

    private T data;
}
