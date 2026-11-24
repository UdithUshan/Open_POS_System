package com.pos.model;

import com.pos.util.Money;
import com.pos.util.PosExeption;
import java.math.BigDecimal;

public class PercentageDiscount implements Discount{
    private final BigDecimal percent;

    public PercentageDiscount(BigDecimal percent) {
        if (percent.signum() < 0 || percent.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new PosExeption("Percentage must be between 0 and 100");
        }
        this.percent = percent;
    }

    @Override
    public BigDecimal apply(BigDecimal amount) {
        BigDecimal factor = BigDecimal.valueOf(100).subtract(percent).divide(BigDecimal.valueOf(100));
        return Money.round(amount.multiply(factor));
    }

    @Override
    public String describe() {
        return percent.stripTrailingZeros().toPlainString() + "% off";
    }

    public BigDecimal getPercent() {
        return percent;
    }
}
