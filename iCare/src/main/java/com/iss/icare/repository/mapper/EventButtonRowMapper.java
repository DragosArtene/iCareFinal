package com.iss.icare.repository.mapper;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.jdbc.core.*;

import java.sql.*;

public class EventButtonRowMapper implements RowMapper<EventButton> {

    @Override
    public EventButton mapRow(ResultSet resultSet, int i) throws SQLException {
        EventButton event = new EventButton();

        event.setEventId(resultSet.getInt(EventButtonRepository.ID_EVENT));
        event.setButtonType(resultSet.getBoolean(EventButtonRepository.BUTTON));
        event.setGiverId(resultSet.getInt(EventButtonRepository.ID_GIVER));


        return event;
    }

}
