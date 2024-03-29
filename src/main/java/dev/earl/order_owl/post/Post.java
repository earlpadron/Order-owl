package dev.earl.order_owl.post;

public record
Post(
        Integer id,
        Integer userId,
        String title,
        String body
) {
}
