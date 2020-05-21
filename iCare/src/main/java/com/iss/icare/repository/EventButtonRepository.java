package com.iss.icare.repository;

import com.iss.icare.model.*;
import com.iss.icare.repository.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.*;

import java.text.*;
import java.util.*;

@Repository
public class EventButtonRepository {

    public static final String ID_GIVER = "giver_id";
    public static final String ID_EVENT = "event_id";
    public static final String BUTTON = "event_button_type";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EventButtonRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<EventButton> findAllEventButtons() {
        String sql = "SELECT * from event_button";

        return namedParameterJdbcTemplate.query(sql, getSqlParameterSourceFromModel(null), new EventButtonRowMapper());
    }

    public List<EventButton> findAllEventsByEventId(int eventId) {
        String sql = "SELECT * from event_button WHERE " + ID_EVENT + "=:event_id";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID_EVENT,eventId), new EventButtonRowMapper());
    }

    public List<EventButton> findAllEventsByGiverId(int giverId) {
        String sql = "SELECT * from event_button WHERE " + ID_GIVER + "=:giver_id";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID_GIVER,giverId), new EventButtonRowMapper());
    }

    private SqlParameterSource getSqlParameterSourceFromModel(EventButton event) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (event != null) {
            parameterSource.addValue(ID_EVENT, event.getEventId());
            parameterSource.addValue(ID_GIVER, event.getGiverId());
            parameterSource.addValue(BUTTON, event.getButtonType());

        }
        return parameterSource;
    }

    public void assignGiverToEvent(int id_event, int id_giver,boolean button) {
        String sql = "INSERT INTO event_button(" + ID_EVENT + ", " + ID_GIVER + ", "+ BUTTON +") VALUES (:event_id, :giver_id,:event_button_type)";

        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromParametersFromModel(id_event, id_giver,button));
    }

    public void deleteGiverFromEvent(int id_event, int id_giver) {
        String sql = "DELETE FROM event_button WHERE " + ID_EVENT + " =:event_id and " + ID_GIVER + " =:giver_id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromParameters(id_event, id_giver));
    }

    public boolean exists(int id_event, int id_giver) {
        String sql = "SELECT * FROM event_button WHERE " + ID_EVENT + " =:event_id and " + ID_GIVER + " =:giver_id";

        List<EventButton> result = namedParameterJdbcTemplate.query(sql, getSqlParameterSourceFromParameters(id_event, id_giver), new EventButtonRowMapper());

        return !result.isEmpty();
    }

    public void update(EventButton event) {
        String sql = MessageFormat.format("UPDATE event_button SET {0}=:{0} WHERE  {1}=:{1} AND {2}=:{2}",
                BUTTON, ID_EVENT,ID_GIVER);

        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromModel(event));
    }

    private SqlParameterSource getSqlParameterSourceFromParameters(Integer idEvent, Integer idGiver) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(ID_EVENT, idEvent);
        parameterSource.addValue(ID_GIVER, idGiver);

        return parameterSource;
    }

    private SqlParameterSource getSqlParameterSourceFromParameter(boolean button) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(BUTTON, button);

        return parameterSource;
    }

    private SqlParameterSource getSqlParameterSourceFromParametersFromModel(int idEvent, int idGiver,boolean button) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(ID_EVENT, idEvent);
        parameterSource.addValue(ID_GIVER, idGiver);
        parameterSource.addValue(BUTTON, button);

        return parameterSource;
    }

}
