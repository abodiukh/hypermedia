package com.bodiukh.infrastructure;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Long> {

    @Override
    public Long convertToDatabaseColumn(final LocalDateTime attribute) {
        return (attribute != null) ? attribute.toInstant(ZoneOffset.UTC).toEpochMilli() : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(final Long dbData) {
        return (dbData != null) ? LocalDateTime.ofInstant(Instant.ofEpochMilli(dbData), ZoneOffset.UTC) : null;
    }

}
