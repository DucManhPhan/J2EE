package com.manhpd.jpaconverter.shared.db.converter;


import com.manhpd.jpaconverter.shared.utils.DataUtils;
import com.manhpd.jpaconverter.shared.utils.DateTimeUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return Objects.isNull(localDateTime) ? null : Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return Objects.isNull(timestamp) ? null : timestamp.toLocalDateTime();
    }
}
