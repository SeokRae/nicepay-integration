package kr.co.nicepay.untact.common.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IdConverter implements AttributeConverter<Id<String>, String> {

    @Override
    public String convertToDatabaseColumn(Id<String> attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public Id<String> convertToEntityAttribute(String dbData) {
        return dbData != null ? new Id<>(dbData) : null;
    }
}