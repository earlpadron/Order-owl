package dev.earl.order_owl.model.embedded;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Embeddable
public class Address implements Serializable {

    @NotEmpty(message = "{address.line.not.empty}")
    private String addressLine1;
    private String addressLine2;
    @NotEmpty(message = "{address.city.not.empty}")
    private String city;
    @NotEmpty(message = "{address.state.not.empty}")
    private String state;
    @NotEmpty(message = "{address.country.not.empty}")
    private String country;
    @NotEmpty(message = "{address.postalCode.not.empty}")
    private String postalCode;
}
