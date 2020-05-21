package com.iss.icare.dto;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.*;

import java.sql.*;
import java.time.*;
import java.util.Objects;

@JsonRootName(value = "event")
@JsonInclude(Include.NON_NULL)
public class EventDto {
    private int eventId;
    private String eventTitle;
    private String eventType;
    private String eventDescription;
    private LocalDateTime eventDateBeginning;
    private LocalDateTime eventDateEnding;
    private Blob eventPicture;
    private int ongId;

    public EventDto(){}
    public EventDto(int eventId, String eventTitle, String eventType, String eventDescription, LocalDateTime eventDateBeginning, LocalDateTime eventDateEnding, Blob eventPicture, int ongId) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.eventDateBeginning = eventDateBeginning;
        this.eventDateEnding = eventDateEnding;
        this.eventPicture = eventPicture;
        this.ongId = ongId;
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
        return "EventDto{" +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDto)) return false;
        EventDto eventDto = (EventDto) o;
        return getEventId() == eventDto.getEventId() &&
                getOngId() == eventDto.getOngId() &&
                getEventType().equals(eventDto.getEventType()) &&
                getEventDescription().equals(eventDto.getEventDescription()) &&
                getEventDateBeginning().equals(eventDto.getEventDateBeginning()) &&
                getEventDateEnding().equals(eventDto.getEventDateEnding());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventId(), getEventType(), getEventDescription(), getEventDateBeginning(), getEventDateEnding(), getOngId());
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
