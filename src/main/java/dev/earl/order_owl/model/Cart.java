package dev.earl.order_owl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;
import java.util.Objects;

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
@Entity
@SuperBuilder
public class Cart {

    @Id
    @GeneratedValue
    private Integer cartId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    //@MapsId //read about this further https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
    private Customer customer;


    @ElementCollection(fetch = FetchType.LAZY)
    /**
     *   Declare that a collection is mapped to a dedicated table,
     *   @ElementCollection is one of Hibernate's least-favorite features of JPA. Even the name of the annotation is bad.
     */

    @CollectionTable(name = "product_quantity", // table name
                     joinColumns = @JoinColumn(name ="cart_id")) // column holding foreign key of owner
    @Column(name = "quantity") //column holding map values
    @MapKeyJoinColumn(name = "product_id", referencedColumnName = "productId")
    //@MapKeyJoinColumn specifies the column used to persist the keys of a map (used when the key is an entity)
    private Map<Product, Integer> productToQuantity;

    @Min(value = 0, message = "{cart.invalid.subtotal}")
    private double subtotal;
    @Min(value = 0, message = "{cart.invalid.number.of.items}")
    private int numberOfItems;

    public Cart(){}
    public Cart(Customer customer, Map<Product, Integer> productToQuantity, double subtotal, int numberOfItems) {
        this.customer = customer;
        this.productToQuantity = productToQuantity;
        this.subtotal = subtotal;
        this.numberOfItems = numberOfItems;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Product, Integer> getProductToQuantity() {
        return productToQuantity;
    }

    public void setProductToQuantity(Map<Product, Integer> productToQuantity) {
        this.productToQuantity = productToQuantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Double.compare(cart.subtotal, subtotal) == 0 && numberOfItems == cart.numberOfItems && Objects.equals(cartId, cart.cartId) && Objects.equals(customer, cart.customer) && Objects.equals(productToQuantity, cart.productToQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, customer, productToQuantity, subtotal, numberOfItems);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", customer=" + customer.getName() +
              //  ", productToQuantity=" + productToQuantity.size() +
                ", subtotal=" + subtotal +
                ", numberOfItems=" + numberOfItems +
                '}';
    }
}
