package com.iss.icare.service;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class EventButtonService {

    private final EventButtonRepository eventButtonRepository;

    public EventButtonService(EventButtonRepository eventButtonRepository) {
        this.eventButtonRepository = eventButtonRepository;
    }

    public List<EventButton> findAllEventButtons() {
        return eventButtonRepository.findAllEventButtons();
    }

    public List<EventButton> findEventButtonByEventId(int eventId) {
        return eventButtonRepository.findAllEventsByEventId(eventId);
    }

    public List<EventButton> findEventButtonByGiverId(int giverId) {
        return eventButtonRepository.findAllEventsByGiverId(giverId);
    }

    public void insertEventButton(EventButton event) {
        eventButtonRepository.assignGiverToEvent(event.getEventId(),event.getGiverId(),event.getButtonType());
    }

    public boolean exists(int event_id,int giver_id) {
        return eventButtonRepository.exists(event_id,giver_id);
    }

    public void delete(int event_id,int giver_id) {
        eventButtonRepository.deleteGiverFromEvent(event_id,giver_id);
    }

    public void update(EventButton event) {
        eventButtonRepository.update(event);
    }

}
