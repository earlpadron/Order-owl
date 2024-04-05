package dev.earl.order_owl.controller;

import dev.earl.order_owl.exception.custom_exception.product.ProductAlreadyExistsException;
import dev.earl.order_owl.exception.custom_exception.product.ProductNotFoundException;
import dev.earl.order_owl.model.PaginationResponse;
import dev.earl.order_owl.model.Product;

import dev.earl.order_owl.service.ProductService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "order_owl/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{product_id}")
    public ResponseEntity<Product> getProduct(@PathVariable("product_id") @Min(1) Integer id){
       Product product = productService.getProduct(id);
       return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<Product>> getAllProducts(
            @RequestParam(value = "pageNo" , defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue =  "10") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "productId") String sortBy
    ){
        PaginationResponse<Product> productPaginationResponse= productService.getAllProduct(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(productPaginationResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> postProduct(@RequestBody Product newProduct) throws ProductAlreadyExistsException {
        Product product = productService.postProduct(newProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("product_id") Integer id,
                                                 @RequestBody Product updateProduct) throws ProductNotFoundException{
        Product product = productService.updateProduct(id, updateProduct);
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);

    }

    @DeleteMapping(value = "/{product_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("product_id") Integer id) throws ProductNotFoundException {
        productService.deleteProduct(id);
    }
}
