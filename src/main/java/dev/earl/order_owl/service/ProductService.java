package dev.earl.order_owl.service;

import dev.earl.order_owl.exception.custom_exception.product.ProductAlreadyExistsException;
import dev.earl.order_owl.exception.custom_exception.product.ProductNotFoundException;
import dev.earl.order_owl.model.Product;
import dev.earl.order_owl.model.ClientProduct;
import dev.earl.order_owl.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final RestClient restClient;

    private final ProductRepository productRepository;
    private final Environment environment;


    public ProductService(ProductRepository productRepository, Environment environment) {
        this.productRepository = productRepository;
        this.environment = environment;
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
                                .quantityInStock((int)(Math.random() * 100 + 1))
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

    public List<Product> getAllProduct(){
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()){
            throw new NullPointerException();
        }
        return productList;
    }

    public Product postProduct(Product newProduct) throws ProductAlreadyExistsException {
        if(productRepository.findByTitle(newProduct.getTitle()) != null){
            throw new ProductAlreadyExistsException(environment.getProperty("service.product.already.exists"));
        }
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Integer id, Product updateProduct) throws ProductNotFoundException {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(environment.getProperty("service.product.not.found")));
        productToUpdate.setTitle(updateProduct.getTitle());
        productToUpdate.setDescription(updateProduct.getDescription());
        productToUpdate.setCategory(updateProduct.getCategory());
        productToUpdate.setImage(updateProduct.getImage());
        productToUpdate.setPrice(updateProduct.getPrice());
        productToUpdate.setQuantityInStock(updateProduct.getProductId());

       return productRepository.save(productToUpdate);
    }

    public void deleteProduct(Integer id) throws ProductNotFoundException {
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException(environment.getProperty("service.product.not.found"));
        }
        productRepository.deleteById(id);
    }





}
