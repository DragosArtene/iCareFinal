package com.iss.icare.model;

import java.time.*;
import java.util.*;

public class Payment {
    private int payId;
    private String cardNumber;
    private String cardName;
    private int keyCode;
    private int payedSum;
    private LocalDate expDate;

    public Payment(){}
    public Payment(String cardNumber, String cardName, int keyCode, int payedSum, LocalDate expDate) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.keyCode = keyCode;
        this.payedSum = payedSum;
        this.expDate = expDate;
    }
    public Payment(int payId, String cardNumber, String cardName, int keyCode, int payedSum, LocalDate expDate) {
        this(cardNumber,cardName,keyCode,payedSum,expDate);
        this.payId = payId;
    }
    public int getPayId() { return payId; }
    public void setPayId(int payId) { this.payId = payId; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardName() { return cardName; }
    public void setCardName(String cardName) { this.cardName = cardName; }

    public int getKeyCode() { return keyCode; }
    public void setKeyCode(int keyCode) { this.keyCode = keyCode; }

    public int getPayedSum() { return payedSum; }
    public void setPayedSum(int payedSum) { this.payedSum = payedSum; }

    public LocalDate getExpDate() { return expDate; }
    public void setExpDate(LocalDate expDate) { this.expDate = expDate; }

    @Override
    public String toString() {
        return "Payment{" +
                "payId=" + payId +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardName='" + cardName + '\'' +
                ", keyCode=" + keyCode +
                ", payedSum=" + payedSum +
                ", expDate=" + expDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return getPayId() == payment.getPayId() &&
                getKeyCode() == payment.getKeyCode() &&
                getPayedSum() == payment.getPayedSum() &&
                getCardNumber().equals(payment.getCardNumber()) &&
                getCardName().equals(payment.getCardName()) &&
                getExpDate().equals(payment.getExpDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPayId(), getCardNumber(), getCardName(), getKeyCode(), getPayedSum(), getExpDate());
    }
}
