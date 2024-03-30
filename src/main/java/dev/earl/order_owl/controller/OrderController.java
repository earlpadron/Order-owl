package dev.earl.order_owl.controller;

import dev.earl.order_owl.exception.custom_exception.order.*;
import dev.earl.order_owl.model.Order;
import dev.earl.order_owl.model.dto.OrderDTO;
import dev.earl.order_owl.service.OrderService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("order_owl/v1/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{order_id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable(value = "order_id") @Min(1) Integer id) throws OrderNotFoundException {
        OrderDTO orderDto = orderService.getOrder(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() throws OrderListEmptyException {
        List<OrderDTO> orderDtoList = orderService.getAllOrders();
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> postOrder(@RequestBody  OrderDTO newOrder) throws OrderAlreadyExistsException, OrderCreateNotValidException {
        OrderDTO orderDto = orderService.postOrder(newOrder);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable("order_id") @Min(1) Integer id,
                                                @RequestBody OrderDTO updateOrder) throws OrderNotFoundException, OrderUpdateNotValidException {
        OrderDTO updatedOrder = orderService.updateOrder(id, updateOrder);
        return new ResponseEntity<>(updatedOrder, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{order_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("order_id") @Min(1) Integer id) throws OrderNotFoundException{
        orderService.deleteOrder(id);
    }


}
