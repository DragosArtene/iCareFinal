package com.iss.icare.repository.mapper;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.jdbc.core.*;

import java.sql.*;
import java.time.*;
import java.time.format.*;

public class PaymentRowMapper implements RowMapper<Payment> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

    @Override
    public Payment mapRow(ResultSet resultSet, int i) throws SQLException {
        Payment payment = new Payment();


        payment.setPayId(resultSet.getInt(PaymentRepository.ID));
        payment.setCardNumber(resultSet.getString(PaymentRepository.CARD_NUMBER));
        payment.setCardName(resultSet.getString(PaymentRepository.CARD_NAME));
        payment.setKeyCode(resultSet.getInt(PaymentRepository.KEY_CODE));
        payment.setPayedSum(resultSet.getInt(PaymentRepository.PAYED_SUM));
        String expirationDate = resultSet.getString(PaymentRepository.EXP_DATE);
        if (expirationDate != null) {
            payment.setExpDate(LocalDate.parse(expirationDate, formatter));
        }

        return payment;
    }

}
