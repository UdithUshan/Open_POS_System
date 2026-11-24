package com.pos.model;

import com.pos.util.Money;
import java.math.BigDecimal;

public class CashPayment extends Payment {
    private final BigDecimal amountTendered;

    public CashPayment(BigDecimal amount, BigDecimal amountTendered) {
        super(amount);
        this.amountTendered = amountTendered;
    }

    public BigDecimal calculateChange() {
        return Money.round(amountTendered.subtract(amount).max(BigDecimal.ZERO));
    }

    @Override
    public boolean process() {
        status = amountTendered.compareTo(amount) >= 0 ? PaymentStatus.APPROVED : PaymentStatus.DECLINED;
        return status == PaymentStatus.APPROVED;
    }

    @Override
    public String getType() {
        return "CASH";
    }
    @Override
    public String getDetails() {
        return amountTendered.toPlainString();
    }

    public BigDecimal getAmountTendered() {
        return amountTendered;
    }
}
