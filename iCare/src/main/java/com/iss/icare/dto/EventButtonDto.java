package com.iss.icare.dto;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonRootName(value = "eventButton")
@JsonInclude(Include.NON_NULL)
public class EventButtonDto {
    private int eventId;
    private int giverId;
    private boolean buttonType;

    public EventButtonDto(){}
    public EventButtonDto(int eventId, int giverId, boolean buttonType) {
        this.eventId = eventId;
        this.giverId = giverId;
        this.buttonType = buttonType;
    }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public int getGiverId() { return giverId; }
    public void setGiverId(int giverId) { this.giverId = giverId; }

    public boolean getButtonType() { return buttonType; }
    public void setButtonType(boolean buttonType) { this.buttonType = buttonType; }
}
