package com.iss.icare.controller;

import com.iss.icare.dto.*;
import com.iss.icare.exception.*;
import com.iss.icare.mapper.*;
import com.iss.icare.model.*;
import com.iss.icare.service.*;
import io.swagger.annotations.*;
import org.apache.logging.log4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@Api(value = "event controller")
@RestController
@RequestMapping("/events")
public class EventController {

    private static final Logger LOGGER = LogManager.getLogger(EventController.class);
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService) {
        this.eventService = eventService;
        this.eventMapper = new EventMapper();
    }

    @ApiOperation(value = "Find All Events")
    @GetMapping
    public ResponseEntity<List<EventDto>> findAllEvents(@RequestParam(required = false) List<String> categories) {
        LOGGER.info("Getting all events");
        /*List<Event> list = eventService.findAllEvents();

        List<EventDto> result = list.stream().map(eventMapper::convertToDto).collect(Collectors.toList());
         */
        List<EventDto> eventsDtos = new ArrayList<>();
        if ((categories == null || categories.isEmpty())) {
            List<Event> events = eventService.findAllEvents();
            for (Event event : events) {
                eventsDtos.add(eventMapper.convertToDto(event));
            }
        } else {
            eventsDtos = eventService.filterEventsByField(categories).stream().map(eventMapper::convertToDto).collect(Collectors.toList());
        }

        LOGGER.info("Returning {} events", eventsDtos.size());
        return new ResponseEntity<>(eventsDtos, HttpStatus.OK);
    }

    @ApiOperation(value = "Find All On Going Events")
    @GetMapping("/ongoing")
    public ResponseEntity<List<EventDto>> findOnGoingEvents() {
        LOGGER.info("Getting all on going events");
        List<EventDto> eventsDtos = new ArrayList<>();
        List<Event> events = eventService.filterEventsByOnGoing();
        for (Event event : events) {
            eventsDtos.add(eventMapper.convertToDto(event));
        }

        LOGGER.info("Returning {} events", eventsDtos.size());
        return new ResponseEntity<>(eventsDtos, HttpStatus.OK);
    }

    @ApiOperation(value = "Find event by id")
    @GetMapping("/{id}")
    public ResponseEntity<EventDto> findEventById(@PathVariable("id") int eventId) {
        LOGGER.info("Finding event with id:{}", eventId);

        if (!eventService.exists(eventId)) {
            LOGGER.info("Event with id: {} doesn't exists", eventId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Event event = eventService.findEventById(eventId);

        LOGGER.info("Event {} found successfully", eventId);
        return new ResponseEntity<>(eventMapper.convertToDto(event), HttpStatus.OK);
    }

    @ApiOperation(value = "Insert an event")
    @PostMapping
    public ResponseEntity<Void> insertEvent(@RequestBody EventDto eventDto) {

        LOGGER.info("Adding a new event");
        eventService.insertEvent(eventMapper.convertToEntity(eventDto));

        LOGGER.info("Event {} added successfully!", eventDto.getEventId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an event")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody EventDto event) {
        LOGGER.info("Updating  event with id:{}", id);

        if (!eventService.exists(id)) {
            LOGGER.info("Event with id:{} doesn't exists", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        event.setEventId(id);
        eventService.update(eventMapper.convertToEntity(event));
        LOGGER.info("Event with id:{} updated successfully", id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an event")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws ConvertBlobException {
        LOGGER.info("Deleting event with id={}", id);

        eventService.findEventById(id);
        if (!eventService.exists(id)) {
            LOGGER.info("Event with id:{} doesn't exists", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        eventService.delete(id);

        LOGGER.info("Event {} deleted successfully!", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
