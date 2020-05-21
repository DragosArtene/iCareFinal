package com.iss.icare.service;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAllEvents();
    }

    public Event findEventById(int eventId) {
        return eventRepository.findEventById(eventId);
    }

    public int insertEvent(Event event) {
        return eventRepository.insertEvent(event);
    }

    public boolean exists(int id) {
        return eventRepository.exists(id);
    }

    public void delete(int event_id) {
        eventRepository.delete(event_id);
    }

    public void update(Event event) {
        eventRepository.update(event);
    }

    public List<Event> filterEventsByField(List<String> categories) {
        return eventRepository.filterEventsByFields(categories);
    }

    public List<Event> filterEventsByOnGoing() {
        List<Event> allEvents = this.findAllEvents();
        List<Event> events = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        for(Event e: allEvents) {
            if(e.getEventDateBeginning()!=null || e.getEventDateEnding()!=null) {
                int checkBefore = e.getEventDateBeginning().compareTo(currentDateTime);
                int checkAfter = e.getEventDateEnding().compareTo(currentDateTime);
                if ((checkBefore <= 0 && checkAfter >= 0) || (checkBefore==0 || checkAfter==0) || (checkBefore<0 && checkAfter>0)) {
                    events.add(e);
                }
            }
        }
        return events;
    }
}
