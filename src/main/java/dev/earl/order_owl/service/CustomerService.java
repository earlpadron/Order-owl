package dev.earl.order_owl.service;

import dev.earl.order_owl.exception.custom_exception.customer.*;
import dev.earl.order_owl.model.Customer;
import dev.earl.order_owl.model.PaginationResponse;
import dev.earl.order_owl.model.dto.CustomerDTO;
import dev.earl.order_owl.repository.CustomerRepository;
import dev.earl.order_owl.service.mapper.CustomerMapper;
import dev.earl.order_owl.service.validator.CustomerValidator;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@PropertySource(value = "classpath:exceptionMessages.properties")
public class CustomerService {

    /**
     * TODO:
     *  check - create a loggingAspect class to log any errors
     *  check - update the throws to be customized exceptions rather than generic NullPointerException
     *  check - create invalid messages for each exception in a custom .properties file
     */

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final Environment environment;
    private final CustomerValidator validator;


    public CustomerService(CustomerRepository repository, CustomerMapper mapper, Environment environment, CustomerValidator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.environment = environment;
        this.validator = validator;
    }

    public CustomerDTO getCustomer(Integer id) throws CustomerNotFoundException {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(environment.getProperty("service.customer.not.found")));
        return mapper.customerToCustomerDTO(customer);

    }

    public PaginationResponse<CustomerDTO> getAllCustomers(int pageNo, int pageSize) throws CustomerListEmptyException {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        //retrieve a page of posts
        Page<Customer> customerPage = repository.findAll(pageable);

        List<CustomerDTO> customerDTOS = customerPage.stream()
                .map(mapper::customerToCustomerDTO)
                .toList();

        if(customerDTOS.isEmpty()){
            throw new CustomerListEmptyException(environment.getProperty("service.customer.list.empty"));
        }

        return PaginationResponse.<CustomerDTO>builder()
                .content(customerDTOS)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(customerPage.getTotalElements())
                .totalPages(customerPage.getTotalPages())
                .last(customerPage.isLast())
                .build();

    }

    public PaginationResponse<CustomerDTO> getAllCustomerSortedByNameDescAndEmailAsc(int pageNo, int pageSize, String sortBy1, String sortBy2) throws CustomerListEmptyException{
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy1).descending().and(Sort.by(sortBy2).ascending()));

        Page<Customer> customerPage = repository.findAll(pageable);

        List<CustomerDTO> customerDTOS = customerPage.stream()
                .map(mapper::customerToCustomerDTO)
                .toList();

        if(customerDTOS.isEmpty()){
            throw new CustomerListEmptyException(environment.getProperty("{service.customer.list.empty}"));
        }

        return PaginationResponse.<CustomerDTO>builder()
                .content(customerDTOS)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(customerPage.getTotalElements())
                .totalPages(customerPage.getTotalPages())
                .last(customerPage.isLast())
                .build();
    }


    //customize queries

    public CustomerDTO createCustomer(CustomerDTO customerDTO) throws CustomerAlreadyExistsException, CustomerCreateNotValidException {
        //first validate the DTO being sent in
        validator.validate(customerDTO, "Invalid fields of new customer") ;
        //check if this customer already exists
        if(repository.findByNameIgnoreCase(customerDTO.name()) == null){
            Customer customer= mapper.customerDTOToCustomer(customerDTO);
            repository.save(customer);
        } else {
            throw new CustomerAlreadyExistsException(environment.getProperty("service.customer.already.exists"));
        }
        return customerDTO;
    }

    public CustomerDTO updateCustomer(Integer id, CustomerDTO updateCustomerDTO) throws CustomerNotFoundException, CustomerUpdateNotValidException {
        validator.validate(updateCustomerDTO, "Invalid updated fields on customer");
        Customer customerToBeUpdated = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(environment.getProperty("service.customer.not.found")));
            customerToBeUpdated.setName(updateCustomerDTO.name());
            customerToBeUpdated.setAddress(updateCustomerDTO.address());
            customerToBeUpdated.setPhone(updateCustomerDTO.phone());
            customerToBeUpdated.setShipmentList(updateCustomerDTO.shipments());
            customerToBeUpdated.setCart(updateCustomerDTO.cart());
            repository.save(customerToBeUpdated);

        return mapper.customerToCustomerDTO(customerToBeUpdated);

    }

   public void deleteCustomer(Integer id) throws CustomerNotFoundException{
        if(!repository.existsById(id)){
            throw new CustomerNotFoundException(environment.getProperty("service.customer.not.found"));
        }
       repository.deleteById(id);
   }
}
