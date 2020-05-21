package com.iss.icare.mapper;
import com.iss.icare.dto.*;
import com.iss.icare.model.*;

public class EventMapper {
public EventDto convertToDto(Event event){
        EventDto eventDto = new EventDto();

        eventDto.setEventId(event.getEventId());
        eventDto.setEventPicture(event.getEventPicture());
        eventDto.setEventType(event.getEventType());
        eventDto.setEventDescription(event.getEventDescription());
        eventDto.setEventDateBeginning(event.getEventDateBeginning());
        eventDto.setEventDateEnding(event.getEventDateEnding());
        eventDto.setEventTitle(event.getEventTitle());
        eventDto.setOngId(event.getOngId());
        return eventDto;
}

public Event convertToEntity(EventDto eventDto){
        Event event = new Event();

        event.setEventId(eventDto.getEventId());
        event.setEventTitle(eventDto.getEventTitle());
        event.setEventType(eventDto.getEventType());
        event.setEventDescription(eventDto.getEventDescription());
        event.setEventDateBeginning(eventDto.getEventDateBeginning());
        event.setEventDateEnding(eventDto.getEventDateEnding());
        event.setEventPicture(eventDto.getEventPicture());
        event.setOngId(eventDto.getOngId());

        return event;
}

}
