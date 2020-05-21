package com.iss.icare.repository;

import com.iss.icare.model.*;
import com.iss.icare.repository.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;

import java.text.*;
import java.util.*;

@Repository
public class OrganisationRepository {

    public static final String ID = "ong_id";
    public static final String ONG_FIRST_NAME = "ong_first_name";
    public static final String ONG_LAST_NAME = "ong_last_name";
    public static final String ONG_EMAIL = "email";
    public static final String ONG_PASSWORD = "password";
    public static final String ONG_DESC = "ong_desc";
    public static final String ONG_PHONE = "phoneNumber";
    public static final String ONG_USERNAME = "ong_username";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OrganisationRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public List<Organisation> findAllOngs() {
        String sql = "SELECT * from organisation";

        return namedParameterJdbcTemplate.query(sql, getSqlParameterSourceFromModel(null), new OrganisationRowMapper());
    }

    public Organisation findOrganisationById(int ongId) {
        String sql = "SELECT * from organisation WHERE " + ID + "=:ong_id";
        List<Organisation> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID, ongId), new OrganisationRowMapper());

        return result.isEmpty() ? null : result.get(0);
    }

    public Organisation findOrganisationByUsername(String username) {
        String sql = "SELECT * from organisation WHERE " + ONG_USERNAME+ "=:ong_username";
        List<Organisation> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID, username), new OrganisationRowMapper());

        return result.isEmpty() ? null : result.get(0);
    }

    private SqlParameterSource getSqlParameterSourceFromModel(Organisation organisation) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (organisation != null) {
            parameterSource.addValue(ID, organisation.getOngId());
            parameterSource.addValue(ONG_FIRST_NAME, organisation.getOngFirstName());
            parameterSource.addValue(ONG_LAST_NAME, organisation.getOngLastName());
            parameterSource.addValue(ONG_EMAIL, organisation.getEmail());
            parameterSource.addValue(ONG_PASSWORD, organisation.getPassword());
            parameterSource.addValue(ONG_DESC, organisation.getOngDescription());
            parameterSource.addValue(ONG_PHONE, organisation.getPhoneNumber());
            parameterSource.addValue(ONG_USERNAME, organisation.getUsername());

        }
        return parameterSource;
    }

    public int insertOrganisation(Organisation organisation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = MessageFormat.format("INSERT INTO organisation({0}, {1}, {2}, {3},{4},{5},{6}) VALUES (:{0}, :{1}, :{2}, :{3},:{4},:{5},:{6})",
                ONG_FIRST_NAME,ONG_LAST_NAME,ONG_EMAIL,ONG_PASSWORD,ONG_DESC,ONG_PHONE,ONG_USERNAME );

        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromModel(organisation), keyHolder);
        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
    }

    public boolean exists(int ong_id) {
        String sql = "SELECT * from organisation WHERE " + ID + " =:ong_id";

        List<Organisation> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID, ong_id), new OrganisationRowMapper());

        return !result.isEmpty();
    }

    public boolean existsUsername(String username) {
        String sql = "SELECT * from organisation WHERE " + ONG_USERNAME + " =:ong_username";

        List<Organisation> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ONG_USERNAME,username), new OrganisationRowMapper());

        return !result.isEmpty();
    }

    public void update(Organisation organisation) {
        String sql = MessageFormat.format("UPDATE organisation SET {0}=:{0},  {1}=:{1},  {2}=:{2}, {3}=:{3},{4}=:{4},{5}=:{5}, {6}=:{6} WHERE {7}=:{7} ", ONG_FIRST_NAME,ONG_LAST_NAME,ONG_EMAIL,ONG_PASSWORD,ONG_DESC,ONG_PHONE,ONG_USERNAME, ID);
        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromModel(organisation));
    }

    private SqlParameterSource getSqlParameterSourceFromPass(int id, String pass) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();


        parameterSource.addValue(ID, id);
        parameterSource.addValue(ONG_PASSWORD, pass);


        return parameterSource;
    }


    public void updatePassword(int id, String encodedPassword) {
        String sql = MessageFormat.format("UPDATE organisation SET {0}=:{0} WHERE {1}=:{1} ", ONG_PASSWORD,ID);
        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromPass(id,encodedPassword));
    }


    public void delete(int ong_id) {
        String sql = MessageFormat.format("DELETE FROM organisation WHERE {0}=:{0}", ID);
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue(ID, ong_id));
    }

}
