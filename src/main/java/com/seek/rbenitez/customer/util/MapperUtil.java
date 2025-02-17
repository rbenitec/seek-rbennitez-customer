package com.seek.rbenitez.customer.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class MapperUtil {

    private  static final DateTimeFormatter DATE_FORMATTER;

    private MapperUtil() {
    }

    static {
        DATE_FORMATTER = DateTimeFormatter.ofPattern(ConstantUtil.CONSTANT_DATE_FORMATTER);
    }

    public static LocalDate getLocalDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }
}
