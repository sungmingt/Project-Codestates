package com.codestates.order.mapper;

import com.codestates.order.Order;
import com.codestates.order.dto.OrderPatchDTO;
import com.codestates.order.dto.OrderPostDTO;
import com.codestates.order.dto.OrderResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

//    Order orderPostDtoToOrder(OrderPostDTO orderPostDTO);
//    Order orderPatchDtoToOrder(OrderPatchDTO orderPatchDTO);
    OrderResponseDTO orderToOrderResponseDTO(Order order);

}
