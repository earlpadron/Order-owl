package dev.earl.order_owl.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.earl.order_owl.model.Shipment;
import dev.earl.order_owl.model.embedded.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CustomerDTO(
    @NotEmpty(message = "{customer.name.not.empty}")
    String name,

    @NotEmpty(message = "{customer.email.not.empty}")
    @Email
    String email,
    String phone,
    Address address
) {
}
