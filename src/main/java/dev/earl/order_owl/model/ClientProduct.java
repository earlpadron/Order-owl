package dev.earl.order_owl.model;

public record ClientProduct(
        Integer id,
        String title,
        Double price,
        String category,
        String description,
        String image
) {
}
