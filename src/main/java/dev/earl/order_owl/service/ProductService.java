package dev.earl.order_owl.service;

import dev.earl.order_owl.exception.custom_exception.product.ProductAlreadyExistsException;
import dev.earl.order_owl.exception.custom_exception.product.ProductConstraintViolationException;
import dev.earl.order_owl.exception.custom_exception.product.ProductListEmptyException;
import dev.earl.order_owl.exception.custom_exception.product.ProductNotFoundException;
import dev.earl.order_owl.model.Product;
import dev.earl.order_owl.model.ClientProduct;
import dev.earl.order_owl.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final RestClient restClient;
    private final ProductRepository productRepository;
    private final Environment environment;
    private final Validator validator;


    public ProductService(ProductRepository productRepository, Environment environment, Validator validator) {
        this.productRepository = productRepository;
        this.environment = environment;
        this.validator = validator;
        this.restClient = RestClient.builder()
                .baseUrl("https://fakestoreapi.com")
                .build();
    }

    public void fillDbWithProducts(){
        List<ClientProduct> clientProductsList = restClient.get()
                .uri("/products")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ClientProduct>>(){
                });


        if(clientProductsList != null){
            //convert clientProduct -> product
            List<Product> productList = clientProductsList.stream()
                    .map(clientProduct -> {
                        return Product.builder()
                                .title(clientProduct.title())
                                .price((clientProduct.price()))
                                .category(clientProduct.category())
                                .description(clientProduct.description())
                                .image(clientProduct.image())
                                .stock((int)(Math.random() * 100 + 1))
                                .build();
                    })
                    .collect(Collectors.toList());

            //store in the database
            productRepository.saveAll(productList);
        }
    }

    public Product getProduct(Integer id) throws ProductNotFoundException {
       return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(environment.getProperty("service.product.not.found")));

    }

    public List<Product> getAllProduct() throws ProductListEmptyException {
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()){
            throw new ProductListEmptyException(environment.getProperty("service.product.list.empty"));
        }
        return productList;
    }

    public Product postProduct(Product newProduct) throws ProductAlreadyExistsException, ProductConstraintViolationException {
        Set<ConstraintViolation<Product>> violations = validator.validate(newProduct);
        if(!violations.isEmpty()){
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ProductConstraintViolationException(
                    environment.getProperty("service.product.invalid.create"),
                    errorMessages);
        }
        if(productRepository.findByTitle(newProduct.getTitle()) != null){
            throw new ProductAlreadyExistsException(
                    environment.getProperty("service.product.already.exists"));
        }
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Integer id, Product updateProduct) throws ProductNotFoundException, ProductConstraintViolationException{
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(environment.getProperty("service.product.not.found")));
        Set<ConstraintViolation<Product>> violations = validator.validate(updateProduct);
        if(!violations.isEmpty()){
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ProductConstraintViolationException(
                    environment.getProperty("service.product.invalid.update"),
                    errorMessages);
        }
        productToUpdate.setTitle(updateProduct.getTitle());
        productToUpdate.setDescription(updateProduct.getDescription());
        productToUpdate.setCategory(updateProduct.getCategory());
        productToUpdate.setImage(updateProduct.getImage());
        productToUpdate.setPrice(updateProduct.getPrice());
        productToUpdate.setStock(updateProduct.getProductId());

       return productRepository.save(productToUpdate);
    }

    public void deleteProduct(Integer id) throws ProductNotFoundException {
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException(environment.getProperty("service.product.not.found"));
        }
        productRepository.deleteById(id);
    }
}
