package dev.earl.order_owl.service.mapper;

import dev.earl.order_owl.model.Customer;
import dev.earl.order_owl.model.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

//    @Mapping(target = "customerNumber", ignore = true)
//    CustomerDTO createCustomerDTOWithoutId(Customer customer);

}
