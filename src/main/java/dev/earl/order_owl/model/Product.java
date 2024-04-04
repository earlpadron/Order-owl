package dev.earl.order_owl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer productId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String category;

    private double price;
    @NotEmpty
    private String description;
    @NotEmpty
    private String image;
    private int stock;


}
