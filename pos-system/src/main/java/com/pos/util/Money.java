package com.pos.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money {
    public static final BigDecimal ZERO = new BigDecimal("0.00");
    private Money() {
    }

    public static BigDecimal round(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal parse(String text) {
        try {
            return round(new BigDecimal(text.trim()));
        }
        catch (NumberFormatException | NullPointerException e) {
            throw new PosExeption("Invalid amount: "+ text);
        }
    }

    public static String format(BigDecimal value) {
        return String.format("%.2f", value);
    }
}
