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

@Api(value = "event button controller")
@RestController
@RequestMapping("/buttons")
public class EventButtonController {

    private static final Logger LOGGER = LogManager.getLogger(EventButtonController.class);
    private final EventButtonService eventButtonService;
    private final EventButtonMapper eventButtonMapper;

    public EventButtonController(EventButtonService eventButtonService) {
        this.eventButtonService = eventButtonService;
        this.eventButtonMapper = new EventButtonMapper();
    }

    @ApiOperation(value = "Find All Event Buttons")
    @GetMapping
    public ResponseEntity<List<EventButtonDto>> findAllEventButtons() {
        LOGGER.info("Getting all event buttons");
        List<EventButton> list = eventButtonService.findAllEventButtons();

        List<EventButtonDto> result = list.stream().map(eventButtonMapper::convertToDto).collect(Collectors.toList());


        LOGGER.info("Returning {} event buttons", result.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Find all event buttons for giver")
    @GetMapping("/bygiver/{giver_id}")
    public ResponseEntity<List<EventButtonDto>> findEventButtonByIdGiver(@PathVariable("giver_id") int giverId) {
        LOGGER.info("Finding events with giver id:{}", giverId);


        List<EventButton> events = eventButtonService.findEventButtonByGiverId(giverId);

        LOGGER.info("Event buttons found successfully for giver {}", giverId);
        List<EventButtonDto> buttons = new ArrayList<>();
        for(EventButton b : events) {
            buttons.add(eventButtonMapper.convertToDto(b));
        }
        return new ResponseEntity<>(buttons, HttpStatus.OK);
    }

    @ApiOperation(value = "Find all event buttons for event")
    @GetMapping("/byevent/{event_id}")
    public ResponseEntity<List<EventButtonDto>> findEventButtonByIdEvent(@PathVariable("event_id") int eventId) {
        LOGGER.info("Finding events with event id:{}", eventId);


        List<EventButton> events = eventButtonService.findEventButtonByEventId(eventId);

        LOGGER.info("Event buttons found successfully for event {}", eventId);
        List<EventButtonDto> buttons = new ArrayList<>();
        for(EventButton b : events) {
            buttons.add(eventButtonMapper.convertToDto(b));
        }
        return new ResponseEntity<>(buttons, HttpStatus.OK);
    }

    @ApiOperation(value = "Insert an event button")
    @PostMapping
    public ResponseEntity<Void> insertEvent(@RequestBody EventButtonDto eventButtonDto) {

        LOGGER.info("Adding a new event button");
        eventButtonService.insertEventButton(eventButtonMapper.convertToEntity(eventButtonDto));

        LOGGER.info("Event button added successfully!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an event button")
    @PutMapping(value = "/{event_id}/{giver_id}")
    public ResponseEntity<Void> update(@PathVariable("event_id") int event_id,@PathVariable("giver_id") int giver_id, @RequestBody EventButtonDto event) {
        LOGGER.info("Updating event button");

        if (!eventButtonService.exists(event_id,giver_id)) {
            LOGGER.info("Event button with event_id {} and giver_id {} doesn't exists", event_id,giver_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        event.setEventId(event_id);
        event.setGiverId(giver_id);
        eventButtonService.update(eventButtonMapper.convertToEntity(event));
        LOGGER.info("Event button updated successfully");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an event button")
    @DeleteMapping(value = "/{event_id}/{giver_id}")
    public ResponseEntity<Void> delete(@PathVariable("event_id") int event_id,@PathVariable("giver_id") int giver_id) {
        LOGGER.info("Deleting event button with event_id={} and giver_id={}", event_id,giver_id);

        if (!eventButtonService.exists(event_id,giver_id)) {
            LOGGER.info("Event button with event_id {} and giver_id {} doesn't exists", event_id,giver_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        eventButtonService.delete(event_id,giver_id);

        LOGGER.info("Event button deleted successfully!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
