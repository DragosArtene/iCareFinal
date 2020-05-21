package com.iss.icare.dto;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonRootName(value = "eventPayment")
@JsonInclude(Include.NON_NULL)
public class EventPaymentDto {
    private int eventId;
    private int payId;

    public EventPaymentDto(){}
    public EventPaymentDto(int eventId, int payId) {
        this.eventId = eventId;
        this.payId = payId;
    }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public int getPayId() { return payId; }
    public void setPayId(int payId) { this.payId = payId; }
}
