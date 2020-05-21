package com.iss.icare.controller;

import com.iss.icare.dto.*;
import com.iss.icare.mapper.*;
import com.iss.icare.model.*;
import com.iss.icare.service.*;
import io.swagger.annotations.*;
import org.apache.logging.log4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@Api(value = "event payment controller")
@RestController
@RequestMapping("/eventpayments")
public class EventPaymentController {

    private static final Logger LOGGER = LogManager.getLogger(EventPaymentController.class);
    private final EventPaymentService eventPaymentService;
    private final EventPaymentMapper eventPaymentMapper;

    public EventPaymentController(EventPaymentService eventPaymentService) {
        this.eventPaymentService = eventPaymentService;
        this.eventPaymentMapper = new EventPaymentMapper();
    }

    @ApiOperation(value = "Find All Event Payments")
    @GetMapping
    public ResponseEntity<List<EventPaymentDto>> findAllEventPayments() {
        LOGGER.info("Getting all event payments");
        List<EventPayment> list = eventPaymentService.findAllEventPayments();

        List<EventPaymentDto> result = list.stream().map(eventPaymentMapper::convertToDto).collect(Collectors.toList());


        LOGGER.info("Returning {} event payments", result.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Find all event payments for payment")
    @GetMapping("/bypayment/{pay_id}")
    public ResponseEntity<List<EventPaymentDto>> findEventPaymentsByIdPayment(@PathVariable("pay_id") int payId) {
        LOGGER.info("Finding events with payment id:{}", payId);


        List<EventPayment> events = eventPaymentService.findEventPaymentsByPaymentId(payId);

        LOGGER.info("Event payments found successfully for payment {}", payId);
        List<EventPaymentDto> payments = new ArrayList<>();
        for(EventPayment p : events) {
            payments.add(eventPaymentMapper.convertToDto(p));
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @ApiOperation(value = "Find all event payments for event")
    @GetMapping("/byevent/{event_id}")
    public ResponseEntity<List<EventPaymentDto>> findEventPaymentsByIdEvent(@PathVariable("event_id") int eventId) {
        LOGGER.info("Finding events with event id:{}", eventId);


        List<EventPayment> events = eventPaymentService.findEventPaymentsByEventId(eventId);

        LOGGER.info("Event payments found successfully for event {}", eventId);
        List<EventPaymentDto> payments = new ArrayList<>();
        for(EventPayment p : events) {
            payments.add(eventPaymentMapper.convertToDto(p));
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @ApiOperation(value = "Insert an event payment")
    @PostMapping
    public ResponseEntity<Void> insertEvent(@RequestBody EventPaymentDto eventPaymentDto) {

        LOGGER.info("Adding a new event payment");
        eventPaymentService.insertEventPayment(eventPaymentMapper.convertToEntity(eventPaymentDto));

        LOGGER.info("Event payment added successfully!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete an event payment")
    @DeleteMapping(value = "/{event_id}/{pay_id}")
    public ResponseEntity<Void> delete(@PathVariable("event_id") int event_id,@PathVariable("pay_id") int pay_id) {
        LOGGER.info("Deleting event payment with event_id={} and pay_id={}", event_id,pay_id);

        if (!eventPaymentService.exists(event_id,pay_id)) {
            LOGGER.info("Event payment with event_id {} and pay_id {} doesn't exists", event_id,pay_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        eventPaymentService.delete(event_id,pay_id);

        LOGGER.info("Event payment deleted successfully!");
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
