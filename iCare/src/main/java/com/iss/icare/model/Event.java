package com.iss.icare.model;
import java.sql.*;
import java.time.*;

public class Event {

    private int eventId;
    private String eventTitle;
    private String eventType;
    private String eventDescription;
    private LocalDateTime eventDateBeginning;
    private LocalDateTime eventDateEnding;
    private Blob eventPicture;
    private int ongId;

    public Event(){}

    public Event(String eventTitle, String eventType, String eventDescription, LocalDateTime eventDateBeginning, LocalDateTime eventDateEnding, Blob eventPicture, int ongId) {
        this.eventTitle = eventTitle;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.eventDateBeginning = eventDateBeginning;
        this.eventDateEnding = eventDateEnding;
        this.eventPicture = eventPicture;
        this.ongId = ongId;
    }
    public Event(int eventId, String eventType, String eventDescription, LocalDateTime eventDateBeginning, LocalDateTime eventDateEnding, int ongId, Blob eventPicture, String eventTitle) {
        this(eventTitle, eventType,eventDescription,eventDateBeginning,eventDateEnding, eventPicture, ongId);
        this.eventId = eventId;
    }

    public int getEventId() { return eventId;}
    public void setEventId(int eventId) { this.eventId = eventId;}

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getEventDescription() { return eventDescription; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }

    public LocalDateTime getEventDateBeginning() { return eventDateBeginning; }
    public void setEventDateBeginning(LocalDateTime eventDateBeginning) { this.eventDateBeginning = eventDateBeginning; }

    public LocalDateTime getEventDateEnding() { return eventDateEnding; }
    public void setEventDateEnding(LocalDateTime eventDateEnding) { this.eventDateEnding = eventDateEnding; }

    public int getOngId() { return ongId; }
    public void setOngId(int ongId) { this.ongId = ongId; }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                "eventTitle=" + eventTitle +
                ", eventType='" + eventType + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventDateBeginning=" + eventDateBeginning +
                ", eventDateEnding=" + eventDateEnding +
                ", eventPicture=" + eventPicture +
                ", ongId=" + ongId +
                '}';
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Blob getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(Blob eventPicture) {
        this.eventPicture = eventPicture;
    }
}
