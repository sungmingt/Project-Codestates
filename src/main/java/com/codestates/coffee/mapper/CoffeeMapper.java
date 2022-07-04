package com.codestates.coffee.mapper;

import com.codestates.coffee.domain.Coffee;
import com.codestates.coffee.dto.CoffeePatchDTO;
import com.codestates.coffee.dto.CoffeePostDTO;
import com.codestates.coffee.dto.CoffeeResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {

    Coffee coffeePostDtoToCoffee(CoffeePostDTO coffeePostDTO);

    Coffee coffeePatchDtoToCoffee(CoffeePatchDTO coffeePatchDTO);

    CoffeeResponseDTO coffeeToCoffeeResponseDto(Coffee coffee);
}
