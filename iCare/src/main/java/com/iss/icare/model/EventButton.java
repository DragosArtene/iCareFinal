package com.iss.icare.model;

public class EventButton {
    private int eventId;
    private int giverId;
    private boolean buttonType;

    public EventButton(){}
    public EventButton(int eventId, int giverId, boolean buttonType) {
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
