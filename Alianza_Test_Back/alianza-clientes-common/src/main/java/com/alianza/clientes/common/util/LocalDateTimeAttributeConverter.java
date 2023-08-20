package com.alianza.clientes.common.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * JPA Converter para el objeto LocalDateTime del api java.time.
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public final Timestamp convertToDatabaseColumn(LocalDateTime locDate) {
        return (locDate == null ? null : Timestamp.valueOf(locDate));
    }

    @Override
    public final LocalDateTime convertToEntityAttribute(Timestamp sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDateTime());
    }
}
