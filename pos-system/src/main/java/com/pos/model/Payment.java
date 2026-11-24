package com.pos.model;

import com.pos.util.PosExeption;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Payment {
    protected Long id;
    protected BigDecimal amount;
    protected LocalDateTime timestamp;
    protected PaymentStatus status = PaymentStatus.PENDING;

    protected Payment(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new PosExeption("Payment amount must be positive.");
        }
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public abstract boolean process();
    public abstract String getType();
    public abstract String getDetails();

    public boolean refund() {
        if (status != PaymentStatus.APPROVED) {
            return false;
        }
        status = PaymentStatus.REFUNDED;
        return true;
    }

    public static Payment restore(String type,Long id, BigDecimal amount, LocalDateTime timeStamp, PaymentStatus status, String details) {
        Payment p;
        switch (type) {
            case "CASH": {
                p = new CashPayment(amount, new BigDecimal(details));
                break;
            }
            case "CARD": {
                String[] d = details.split("\\|", -1);
                p = new CardPayment(amount, d[0]);
                ((CardPayment) p).authCode = d.length > 1 ? d[1] : null;
                break;
            }
            case "MOBILE": {
                String[] d = details.split("\\|", -1);
                p = new MobilePayment(amount, d[0], d.length > 1 ? d[1] : "");
                break;
            }
            default:
                throw new PosException("Unknown payment type : "+ type);
        }
        p.id = id;
        p.timestamp = timeStamp;
        p.status = status;
        return p;
    }

    public Long getId() {
        return id;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public PaymentStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
