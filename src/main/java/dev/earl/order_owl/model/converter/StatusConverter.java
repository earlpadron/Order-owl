package dev.earl.order_owl.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * A class that implements this interface can be used to convert entity attribute state into database column representation and back again. Note that the X and Y types may be the same Java type.
 * Type parameters:
 * <X> – the type of the entity attribute <Y> – the type of the database column
 */
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status statusEntity){
        return statusEntity.getStatusType();
    }

    @Override
    public Status convertToEntityAttribute(String statusDB){
        return Status.fromStatusType(statusDB);
    }
}
