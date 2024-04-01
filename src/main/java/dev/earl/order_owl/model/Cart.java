package dev.earl.order_owl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * source : https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
 *
 * A @OneToOne Bidirecitonal association can be an expensive operation that calls an additional select query
 * of the child when coming from the parent in the relationship. This occurs because the parent "eagerly"
 * searches for the child table no matter what because the Persistence Context needs both the entity type and the identifier
 * for the child, which must be known when loading the parent entity.
 *
 * @MapsId solved this problem by sharing the primary key and foreign key column. NOTICE cardId does not have the @GeneratedValue
 * annotation. the cart can be identified from the customer field that is population from the customer association
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Cart {

    @Id
    private Integer cartId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    @MapsId //read about this further https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
    private Customer customer;


    //@OneToMany
    @ElementCollection
    @CollectionTable(name = "product_to_quantity",
                     joinColumns = @JoinColumn(name ="product"))
    @Column(name = "quantity_of_product")
    @MapKeyJoinColumn(name = "product_id", referencedColumnName = "productId")
    private Map<Product, Integer> productToQuantity;
    private double subtotal;
    @Min(value = 0, message = "{cart.invalid.quantity}")
    private int numberOfItems;



}
