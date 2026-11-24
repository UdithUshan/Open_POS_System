package com.pos.model;

import java.math.BigDecimal;

public interface Discount {
    BigDecimal apply(BigDecimal amount);

    String describe();
}
