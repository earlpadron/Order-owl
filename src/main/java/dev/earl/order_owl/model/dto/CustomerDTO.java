package dev.earl.order_owl.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.earl.order_owl.model.embedded.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CustomerDTO(
    @NotEmpty(message = "{customer.name.not.empty}")
    @JsonProperty(value = "name")
    String customerName,

    @NotEmpty(message = "{customer.email.not.empty}")
    @Email
    String email,
    String phone,
    Address address,
    double creditLimit
) {
}
