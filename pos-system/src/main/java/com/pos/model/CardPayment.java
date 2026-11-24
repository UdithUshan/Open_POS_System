package com.pos.model;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class CardPayment extends Payment {
    private final String cardLast4;
    String authCode;

    public CardPayment(BigDecimal amount, String cardLast4) {
        super(amount);
        this.cardLast4 = cardLast4;
    }

    @Override
    public boolean process() {
        if(cardLast4 == null || !cardLast4.matches("\\d{4}")) {
            status = PaymentStatus.DECLINED;
            return false;
        }
        authCode = String.format("%06d",ThreadLocalRandom.current().nextInt(1_000_000));
        status = PaymentStatus.APPROVED;
        return true;
    }
    @Override
    public String getType() {
        return "CARD";
    }
    @Override
    public String getDetails() {
        return cardLast4 + "|" + (authCode == null ? "" : authCode);
    }
    public String getCardLast4() {
        return cardLast4;
    }
    public String getAuthCode() {
        return authCode;
    }
}