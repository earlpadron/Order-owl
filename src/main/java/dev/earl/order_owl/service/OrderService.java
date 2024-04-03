package dev.earl.order_owl.service;

import dev.earl.order_owl.exception.custom_exception.order.*;
import dev.earl.order_owl.model.Order;
import dev.earl.order_owl.model.dto.OrderDTO;
import dev.earl.order_owl.repository.OrderRepository;
import dev.earl.order_owl.service.mapper.OrderMapper;
import dev.earl.order_owl.service.validator.OrderValidator;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@PropertySource(value = "classpath:exceptionMessages.properties")
public class OrderService {

    private final OrderRepository orderRepository;
    private final Environment environment;
    private final OrderMapper orderMapper;
    private final OrderValidator orderValidator;


    public OrderService(OrderRepository orderRepository, Environment environment, OrderMapper orderMapper, OrderValidator orderValidator) {
        this.orderRepository = orderRepository;
        this.environment = environment;
        this.orderMapper = orderMapper;
        this.orderValidator = orderValidator;
    }

    public OrderDTO getOrder(Integer id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(environment.getProperty("service.order.not.found")));
        return orderMapper.orderToOrderDTO(order);
    }

    //getAll
    public List<OrderDTO> getAllOrders() throws OrderListEmptyException {
        List<OrderDTO> orderList = orderRepository.findAll().stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
        if(orderList.isEmpty()) throw new OrderListEmptyException(environment.getProperty("service.order.list.empty"));
        return orderList;
    }
    //create
    public OrderDTO postOrder(OrderDTO newOrder) throws OrderAlreadyExistsException, OrderCreateNotValidException {
        orderValidator.validate(newOrder, "Invalid fields on order creation");
        //TODO check if it already exists
        Order order = orderMapper.orderDTOToOrder(newOrder);
        orderRepository.save(order);

        return newOrder;
    }


    public OrderDTO updateOrder(Integer id, OrderDTO updateOrder) throws OrderNotFoundException, OrderUpdateNotValidException {
        orderValidator.validate(updateOrder, "Invalid updated fields of the order");
        Order orderToUpdate = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(environment.getProperty("service.order.not.found")));

        orderToUpdate.setOrderDate(updateOrder.orderDate());
        orderToUpdate.setText(updateOrder.text());
        orderToUpdate.setCustomer(updateOrder.customer());

        orderRepository.save(orderToUpdate);
        return updateOrder;

    }

    public void deleteOrder(Integer id) throws OrderNotFoundException {
        if(!orderRepository.existsById(id)){
            throw new OrderNotFoundException(environment.getProperty("{service.order.not.found}"));
        }
        orderRepository.deleteById(id);
    }
}
