package com.iss.icare.repository;

import com.iss.icare.model.*;
import com.iss.icare.repository.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;

import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

@Repository
public class EventRepository {

    public static final String ID = "event_id";
    public static final String ID_ONG = "ong_id";
    public static final String EVENT_PICTURE = "event_picture";
    public static final String EVENT_TITLE= "event_title";
    public static final String EVENT_TYPE = "event_type";
    public static final String EVENT_DESCRIPTION = "event_description";
    public static final String EVENT_DATE_BEGINNING = "event_dateBeginning";
    public static final String EVENT_DATE_ENDING = "event_dateEnding";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.n]");

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EventRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Event> findAllEvents() {
        String sql = "SELECT * from event";

        return namedParameterJdbcTemplate.query(sql, getSqlParameterSourceFromModel(null), new EventRowMapper());
    }

    public Event findEventById(int eventId) {
        String sql = "SELECT * from event WHERE " + ID + "=:event_id";
        List<Event> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID, eventId), new EventRowMapper());

        return result.isEmpty() ? null : result.get(0);
    }

    public List<Event> filterEventsByFields(List<String> categories) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "SELECT * from event WHERE true ";
        if (!categories.isEmpty()) {
            parameters.addValue(EVENT_TYPE, categories);
            sql += " and " + EVENT_TYPE+ " IN (:event_type)";
        }
        return namedParameterJdbcTemplate.query(sql, parameters, new EventRowMapper());
    }

    private SqlParameterSource getSqlParameterSourceFromModel(Event event) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (event != null) {
            parameterSource.addValue(ID, event.getEventId());
            parameterSource.addValue(ID_ONG, event.getOngId());
            parameterSource.addValue(EVENT_TYPE, event.getEventType());
            parameterSource.addValue(EVENT_TITLE, event.getEventTitle());
            parameterSource.addValue(EVENT_DESCRIPTION, event.getEventDescription());
            parameterSource.addValue(EVENT_PICTURE, event.getEventPicture());
            parameterSource.addValue(EVENT_DATE_BEGINNING, event.getEventDateBeginning().format(EventRepository.formatter));
            parameterSource.addValue(EVENT_DATE_ENDING, event.getEventDateEnding().format(EventRepository.formatter));
        }
        return parameterSource;
    }

    public int insertEvent(Event event) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = MessageFormat.format("INSERT INTO event({0}, {1}, {2}, {3}, {4}, {5}, {6}) VALUES (:{0}, :{1}, :{2}, :{3}, :{4}, :{5},:{6})",
                EVENT_TITLE,EVENT_TYPE, EVENT_DESCRIPTION, EVENT_DATE_BEGINNING, EVENT_DATE_ENDING,EVENT_PICTURE, ID_ONG);

        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromModel(event), keyHolder);
        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
    }

    private SqlParameterSource getSqlParameterSourceFromModelWithoutId(Event event) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (event != null) {
            parameterSource.addValue(ID_ONG, event.getOngId());
            parameterSource.addValue(EVENT_TYPE, event.getEventType());
            parameterSource.addValue(EVENT_TITLE, event.getEventTitle());
            parameterSource.addValue(EVENT_PICTURE, event.getEventPicture());
            parameterSource.addValue(EVENT_DESCRIPTION, event.getEventDescription());
            parameterSource.addValue(EVENT_DATE_BEGINNING, event.getEventDateBeginning().format(EventRepository.formatter));
            parameterSource.addValue(EVENT_DATE_ENDING, event.getEventDateEnding().format(EventRepository.formatter));
        }
        return parameterSource;
    }

    public boolean exists(int id) {
        String sql = "SELECT * from event WHERE " + ID + " =:event_id";

        List<Event> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID, id), new EventRowMapper());

        return !result.isEmpty();
    }

    public void update(Event event) {
        String sql = MessageFormat.format("UPDATE event SET {0}=:{0},  {1}=:{1},  {2}=:{2}, {3}=:{3}, {4}=:{4}, {5}=:{5} WHERE {6}=:{6} ", EVENT_TITLE,EVENT_TYPE, EVENT_DESCRIPTION, EVENT_DATE_BEGINNING, EVENT_DATE_ENDING,EVENT_PICTURE, ID);
        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromModel(event));
    }


    public void delete(int event_id) {
        String sql = MessageFormat.format("DELETE FROM event WHERE {0}=:{0}", ID);
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue(ID, event_id));
    }


}
