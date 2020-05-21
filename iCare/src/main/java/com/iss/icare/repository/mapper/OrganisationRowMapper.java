package com.iss.icare.repository.mapper;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.jdbc.core.*;

import java.sql.*;

public class OrganisationRowMapper implements RowMapper<Organisation> {

    @Override
    public Organisation mapRow(ResultSet resultSet, int i) throws SQLException {
        Organisation ong = new Organisation();

        ong.setOngId(resultSet.getInt(OrganisationRepository.ID));
        ong.setOngFirstName(resultSet.getString(OrganisationRepository.ONG_FIRST_NAME));
        ong.setOngLastName(resultSet.getString(OrganisationRepository.ONG_LAST_NAME));
        ong.setEmail(resultSet.getString(OrganisationRepository.ONG_EMAIL));
        ong.setPassword(resultSet.getString(OrganisationRepository.ONG_PASSWORD));
        ong.setOngDescription(resultSet.getString(OrganisationRepository.ONG_DESC));
        ong.setUsername(resultSet.getString(OrganisationRepository.ONG_USERNAME));
        ong.setPhoneNumber(resultSet.getString(OrganisationRepository.ONG_PHONE));

        return ong;
    }

}
