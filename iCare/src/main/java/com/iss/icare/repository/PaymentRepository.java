package com.iss.icare.repository;

import com.iss.icare.model.*;
import com.iss.icare.repository.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.format.datetime.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;

import java.text.*;
import java.time.format.*;
import java.util.*;


@Repository
public class PaymentRepository {

    public static final String ID = "pay_id";
    public static final String CARD_NUMBER = "card_nr";
    public static final String CARD_NAME = "card_name";
    public static final String KEY_CODE = "key_code";
    public static final String PAYED_SUM = "pay_sum";
    public static final String EXP_DATE = "exp_date";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PaymentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public List<Payment> findAllPayments() {
        String sql = "SELECT * from payment";

        return namedParameterJdbcTemplate.query(sql, getSqlParameterSourceFromModel(null), new PaymentRowMapper());
    }

    private SqlParameterSource getSqlParameterSourceFromModel(Payment payment) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (payment != null) {
            parameterSource.addValue(ID, payment.getPayId());
            parameterSource.addValue(CARD_NUMBER, payment.getCardNumber());
            parameterSource.addValue(CARD_NAME, payment.getCardName());
            parameterSource.addValue(KEY_CODE, payment.getKeyCode());
            parameterSource.addValue(PAYED_SUM, payment.getPayedSum());
            parameterSource.addValue(EXP_DATE, payment.getExpDate().format(EventRepository.formatter));
        }
        return parameterSource;
    }

    public Payment findById(int pay_id) {
        String sql = "SELECT * from payment WHERE " + ID + "=:pay_id";
        List<Payment> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID, pay_id), new PaymentRowMapper());

        return result.isEmpty() ? null : result.get(0);
    }

    public boolean exists(int pay_id) {
        String sql = "SELECT * from payment WHERE " + ID + "=:pay_id";
        List<Payment> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID, pay_id), new PaymentRowMapper());

        return !result.isEmpty();
    }

    public int insert(Payment payment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = MessageFormat.format("INSERT INTO payment({0}, {1}, {2}, {3}, {4}) VALUES (:{0}, :{1}, :{2}, :{3}, :{4})",
                CARD_NUMBER, CARD_NAME, KEY_CODE, PAYED_SUM, EXP_DATE);

        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromModel(payment), keyHolder);

        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
    }

    public void update(Payment payment) {
        String sql = MessageFormat.format("UPDATE payment SET {0}=:{0},  {1}=:{1},  {2}=:{2}, {3}=:{3}, {4}=:{4} WHERE  {5}=:{5} ",
                CARD_NUMBER, CARD_NAME, KEY_CODE, PAYED_SUM, EXP_DATE, ID);

        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromModel(payment));
    }

    public void delete(int pay_id) {
        String sql = MessageFormat.format("DELETE FROM payment WHERE {0}=:{0}", ID);
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue(ID, pay_id));
    }

}
