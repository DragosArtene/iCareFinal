package com.iss.icare.dto;
import java.time.*;
import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonRootName(value = "payment")
@JsonInclude(Include.NON_NULL)
public class PaymentDto {
    private int payId;
    private String cardNumber;
    private String cardName;
    private int keyCode;
    private int payedSum;
    private LocalDate expDate;

    public PaymentDto(){}
    public PaymentDto(int payId, String cardNumber, String cardName, int keyCode, int payedSum, LocalDate expDate) {
        this.payId = payId;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.keyCode = keyCode;
        this.payedSum = payedSum;
        this.expDate = expDate;
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
        return "PaymentDto{" +
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
        if (!(o instanceof PaymentDto)) return false;
        PaymentDto that = (PaymentDto) o;
        return getPayId() == that.getPayId() &&
                getKeyCode() == that.getKeyCode() &&
                getPayedSum() == that.getPayedSum() &&
                getCardNumber().equals(that.getCardNumber()) &&
                getCardName().equals(that.getCardName()) &&
                getExpDate().equals(that.getExpDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPayId(), getCardNumber(), getCardName(), getKeyCode(), getPayedSum(), getExpDate());
    }
}
