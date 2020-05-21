package com.iss.icare.service;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class EventPaymentService {

    private final EventPaymentRepository eventPaymentRepository;

    public EventPaymentService(EventPaymentRepository eventPaymentRepository) {
        this.eventPaymentRepository = eventPaymentRepository;
    }

    public List<EventPayment> findAllEventPayments() {
        return eventPaymentRepository.findAllEventPayments();
    }

    public List<EventPayment> findEventPaymentsByEventId(int eventId) {
        return eventPaymentRepository.findAllEventPaymentsByEventId(eventId);
    }

    public List<EventPayment> findEventPaymentsByPaymentId(int paymentId) {
        return eventPaymentRepository.findAllEventPaymentsByPaymentId(paymentId);
    }

    public void insertEventPayment(EventPayment event) {
        eventPaymentRepository.assignPaymentToEvent(event.getEventId(),event.getPayId());
    }

    public boolean exists(int event_id,int payment_id) {
        return eventPaymentRepository.exists(event_id,payment_id);
    }

    public void delete(int event_id,int payment_id) {
        eventPaymentRepository.deletePaymentFromEvent(event_id,payment_id);
    }
}
