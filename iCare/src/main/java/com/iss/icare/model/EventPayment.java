package com.iss.icare.model;

import java.util.Objects;

public class EventPayment {
    private int eventId;
    private int payId;

    public EventPayment(){}
    public EventPayment(int eventId, int payId) {
        this.eventId = eventId;
        this.payId = payId;
    }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public int getPayId() { return payId; }
    public void setPayId(int payId) { this.payId = payId; }
}
