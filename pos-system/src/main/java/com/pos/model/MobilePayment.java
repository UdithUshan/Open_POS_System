package com.pos.model;

import java.math.BigDecimal;

public class MobilePayment extends Payment {
    private final String walletProvider;
    private final String transactionRef;

    public MobilePayment(BigDecimal amount, String walletProvider, String transactionRef) {
        super(amount);
        this.walletProvider = walletProvider;
        this.transactionRef = transactionRef;
    }

    @Override
    public boolean process() {
        boolean ok = transactionRef != null && !transactionRef.isBlank();
        status = ok ? PaymentStatus.APPROVED : PaymentStatus.DECLINED;
        return ok;
    }

    @Override
    public String getType() {
        return "MOBILE";
    }
    @Override
    public String getDetails() {
        return walletProvider + "|" + transactionRef;
    }
    public String getWalletProvider() {
        return walletProvider;
    }
    public String getTransactionRef() {
        return transactionRef;
    }
}
