package com.iss.icare.repository;

import com.iss.icare.model.*;
import com.iss.icare.repository.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class EventPaymentRepository {

    public static final String ID_PAYMENT = "pay_id";
    public static final String ID_EVENT = "event_id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EventPaymentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<EventPayment> findAllEventPayments() {
        String sql = "SELECT * from event_payment";

        return namedParameterJdbcTemplate.query(sql, getSqlParameterSourceFromModel(null), new EventPaymentRowMapper());
    }

    public List<EventPayment> findAllEventPaymentsByEventId(int eventId) {
        String sql = "SELECT * from event_payment WHERE " + ID_EVENT + "=:event_id";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID_EVENT,eventId), new EventPaymentRowMapper());
    }

    public List<EventPayment> findAllEventPaymentsByPaymentId(int paymentId) {
        String sql = "SELECT * from event_payment WHERE " + ID_PAYMENT + "=:pay_id";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID_PAYMENT,paymentId), new EventPaymentRowMapper());
    }

    private SqlParameterSource getSqlParameterSourceFromModel(EventPayment event) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (event != null) {
            parameterSource.addValue(ID_EVENT, event.getEventId());
            parameterSource.addValue(ID_PAYMENT, event.getPayId());

        }
        return parameterSource;
    }

    public void assignPaymentToEvent(int id_event, int id_payment) {
        String sql = "INSERT INTO event_payment(" + ID_EVENT + ", " + ID_PAYMENT +") VALUES (:event_id, :pay_id)";

        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromParameters(id_event, id_payment));
    }

    public void deletePaymentFromEvent(int id_event, int id_payment) {
        String sql = "DELETE FROM event_payment WHERE " + ID_EVENT + " =:event_id and " + ID_PAYMENT + " =:pay_id";

        namedParameterJdbcTemplate.update(sql,  getSqlParameterSourceFromParameters(id_event, id_payment));
    }

    public boolean exists(int id_event, int id_payment) {
        String sql = "SELECT * FROM event_payment WHERE " + ID_EVENT + " =:event_id and " + ID_PAYMENT + " =:pay_id";

        List<EventPayment> result = namedParameterJdbcTemplate.query(sql, getSqlParameterSourceFromParameters(id_event, id_payment), new EventPaymentRowMapper());

        return !result.isEmpty();
    }

    private SqlParameterSource getSqlParameterSourceFromParameters(int idEvent, int idPayment) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(ID_EVENT, idEvent);
        parameterSource.addValue(ID_PAYMENT, idPayment);

        return parameterSource;
    }

}
