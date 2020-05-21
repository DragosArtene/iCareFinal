package com.iss.icare.repository.mapper;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.jdbc.core.*;

import java.sql.*;
import java.time.*;
import java.time.format.*;

public class EventRowMapper implements RowMapper<Event> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.n]");

    @Override
    public Event mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();

        event.setEventId(resultSet.getInt(EventRepository.ID));
        event.setEventTitle(resultSet.getString(EventRepository.EVENT_TITLE));
        event.setEventPicture(resultSet.getBlob(EventRepository.EVENT_PICTURE));
        event.setEventType(resultSet.getString(EventRepository.EVENT_TYPE));
        event.setEventDescription(resultSet.getString(EventRepository.EVENT_DESCRIPTION));

        String beginningDate = resultSet.getString(EventRepository.EVENT_DATE_BEGINNING);
        if (beginningDate != null) {
            event.setEventDateBeginning(LocalDateTime.parse(beginningDate, formatter));
        }

        String endingDate = resultSet.getString(EventRepository.EVENT_DATE_ENDING);
        if (endingDate != null) {
            event.setEventDateEnding(LocalDateTime.parse(endingDate, formatter));
        }

        event.setOngId(resultSet.getInt(EventRepository.ID_ONG));


        return event;
    }
}