package com.iss.icare.mapper;
import com.iss.icare.dto.*;
import com.iss.icare.model.*;

public class EventButtonMapper {
    public EventButtonDto convertToDto(EventButton eventButton) {
        EventButtonDto eventButtonDto = new EventButtonDto();

        eventButtonDto.setEventId(eventButton.getEventId());
        eventButtonDto.setGiverId(eventButton.getGiverId());
        eventButtonDto.setButtonType(eventButton.getButtonType());

        return eventButtonDto;
    }

    public EventButton convertToEntity(EventButtonDto eventButtonDto) {
        EventButton eventButton = new EventButton();

        eventButton.setEventId(eventButtonDto.getEventId());
        eventButton.setGiverId(eventButtonDto.getGiverId());
        eventButton.setButtonType(eventButtonDto.getButtonType());

        return eventButton;
    }
}
