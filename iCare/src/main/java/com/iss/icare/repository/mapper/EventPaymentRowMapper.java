package com.iss.icare.repository.mapper;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.jdbc.core.*;

import java.sql.*;

public class EventPaymentRowMapper  implements RowMapper<EventPayment> {

    @Override
    public EventPayment mapRow(ResultSet resultSet, int i) throws SQLException {
        EventPayment event = new EventPayment();

        event.setEventId(resultSet.getInt(EventPaymentRepository.ID_EVENT));
        event.setPayId(resultSet.getInt(EventPaymentRepository.ID_PAYMENT));


        return event;
    }

}
