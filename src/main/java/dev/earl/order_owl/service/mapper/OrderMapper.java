package dev.earl.order_owl.service.mapper;

import dev.earl.order_owl.model.Order;
import dev.earl.order_owl.model.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

     OrderDTO orderToOrderDTO(Order order);
     Order orderDTOToOrder(OrderDTO orderDTO);
}
