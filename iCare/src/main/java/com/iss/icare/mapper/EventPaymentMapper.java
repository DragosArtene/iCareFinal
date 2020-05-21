package com.iss.icare.mapper;
import com.iss.icare.dto.*;
import com.iss.icare.model.*;

public class EventPaymentMapper {
    public EventPaymentDto convertToDto(EventPayment eventPayment) {
        EventPaymentDto eventPaymentDto = new EventPaymentDto();

        eventPaymentDto.setPayId(eventPayment.getPayId());
        eventPaymentDto.setEventId(eventPayment.getEventId());

        return eventPaymentDto;
    }

    public EventPayment convertToEntity(EventPaymentDto eventPaymentDto) {
        EventPayment eventPayment = new EventPayment();

        eventPayment.setPayId(eventPaymentDto.getPayId());
        eventPayment.setEventId(eventPaymentDto.getEventId());

        return eventPayment;
    }
}