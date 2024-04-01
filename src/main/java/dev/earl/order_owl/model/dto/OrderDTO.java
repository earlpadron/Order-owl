package dev.earl.order_owl.model.dto;

import dev.earl.order_owl.model.Customer;
import dev.earl.order_owl.model.Product;
import dev.earl.order_owl.model.converter.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record OrderDTO(
                        @NotNull
                        @FutureOrPresent
                        LocalDate orderDate,

                        @NotNull
                        String text,

                        @NotNull
                        Customer customer) {
}
