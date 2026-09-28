package com.alpha.autoparts;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.sql.Date;

@Converter(autoApply = false)
public class DateToIntegerConverter implements AttributeConverter<Date, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Date attribute) {
        if (attribute == null) return null;
        // Convert Date "yyyy-MM-dd" to Integer yyyyMMdd
        String str = attribute.toString().replace("-", "");
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Date convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        // Convert Integer yyyyMMdd to Date "yyyy-MM-dd"
        String str = String.valueOf(dbData);
        if (str.length() == 8) {
            try {
                String dateStr = str.substring(0,4) + "-" + str.substring(4,6) + "-" + str.substring(6,8);
                return Date.valueOf(dateStr);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
