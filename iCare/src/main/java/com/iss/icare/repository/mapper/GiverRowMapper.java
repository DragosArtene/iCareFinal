package com.iss.icare.repository.mapper;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.jdbc.core.*;

import java.sql.*;
import java.time.*;

public class GiverRowMapper implements RowMapper<Giver> {

    @Override
    public Giver mapRow(ResultSet resultSet, int i) throws SQLException {
       Giver giver = new Giver();

       giver.setGiverId(resultSet.getInt(GiverRepository.ID));
       giver.setGiverFirstName(resultSet.getString(GiverRepository.GIVER_FIRST_NAME));
       giver.setGiverLastName(resultSet.getString(GiverRepository.GIVER_LAST_NAME));
       giver.setEmail(resultSet.getString(GiverRepository.GIVER_EMAIL));
       giver.setPassword(resultSet.getString(GiverRepository.GIVER_PASSWORD));
       giver.setGiverDescription(resultSet.getString(GiverRepository.GIVER_DESC));
       giver.setUsername(resultSet.getString(GiverRepository.GIVER_USERNAME));
       giver.setPhoneNumber(resultSet.getString(GiverRepository.GIVER_PHONE));

        return giver;
    }

}
