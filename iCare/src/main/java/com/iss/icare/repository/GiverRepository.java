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
public class GiverRepository {

    public static final String ID = "giver_id";
    public static final String GIVER_FIRST_NAME = "giver_first_name";
    public static final String GIVER_LAST_NAME = "giver_last_name";
    public static final String GIVER_EMAIL = "email";
    public static final String GIVER_PASSWORD = "password";
    public static final String GIVER_DESC = "giver_desc";
    public static final String GIVER_PHONE = "phoneNumber";
    public static final String GIVER_USERNAME = "giver_username";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GiverRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public List<Giver> findAllGivers() {
        String sql = "SELECT * from giver";

        return namedParameterJdbcTemplate.query(sql, getSqlParameterSourceFromModel(null), new GiverRowMapper());
    }

    public Giver findGiverById(int giverId) {
        String sql = "SELECT * from giver WHERE " + ID + "=:giver_id";
        List<Giver> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID, giverId), new GiverRowMapper());

        return result.isEmpty() ? null : result.get(0);
    }

    public Giver findGiverByUsername(String username) {
        String sql = "SELECT * from giver WHERE " + GIVER_USERNAME+ "=:giver_username";
        List<Giver> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID, username), new GiverRowMapper());

        return result.isEmpty() ? null : result.get(0);
    }

    private SqlParameterSource getSqlParameterSourceFromModel(Giver giver) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (giver != null) {
            parameterSource.addValue(ID, giver.getGiverId());
            parameterSource.addValue(GIVER_FIRST_NAME, giver.getGiverFirstName());
            parameterSource.addValue(GIVER_LAST_NAME, giver.getGiverLastName());
            parameterSource.addValue(GIVER_EMAIL, giver.getEmail());
            parameterSource.addValue(GIVER_PASSWORD, giver.getPassword());
            parameterSource.addValue(GIVER_DESC, giver.getGiverDescription());
            parameterSource.addValue(GIVER_PHONE, giver.getPhoneNumber());
            parameterSource.addValue(GIVER_USERNAME, giver.getUsername());

        }
        return parameterSource;
    }

    public int insertGiver(Giver giver) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = MessageFormat.format("INSERT INTO giver({0}, {1}, {2}, {3},{4},{5},{6}) VALUES (:{0}, :{1}, :{2}, :{3},:{4},:{5},:{6})",
                GIVER_FIRST_NAME,GIVER_LAST_NAME,GIVER_EMAIL,GIVER_PASSWORD,GIVER_DESC,GIVER_PHONE,GIVER_USERNAME );

        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromModel(giver), keyHolder);
        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
    }

    public boolean exists(int giver_id) {
        String sql = "SELECT * from giver WHERE " + ID + " =:giver_id";

        List<Giver> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(ID, giver_id), new GiverRowMapper());

        return !result.isEmpty();
    }

    public boolean existsUsername(String username) {
        String sql = "SELECT * from giver WHERE " + GIVER_USERNAME + " =:giver_username";

        List<Giver> result = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue(GIVER_USERNAME,username), new GiverRowMapper());

        return !result.isEmpty();
    }

    public void update(Giver giver) {
        String sql = MessageFormat.format("UPDATE giver SET {0}=:{0},  {1}=:{1},  {2}=:{2}, {3}=:{3} , {4}=:{4}, {5}=:{5}, {6}=:{6} WHERE {7}=:{7} ", GIVER_FIRST_NAME,GIVER_LAST_NAME,GIVER_EMAIL,GIVER_PASSWORD,GIVER_DESC,GIVER_PHONE,GIVER_USERNAME, ID);
        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromModel(giver));
    }

    private SqlParameterSource getSqlParameterSourceFromPass(int id, String pass) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();


            parameterSource.addValue(ID, id);
            parameterSource.addValue(GIVER_PASSWORD, pass);


        return parameterSource;
    }


    public void updatePassword(int id, String encodedPassword) {
        String sql = MessageFormat.format("UPDATE giver SET {0}=:{0} WHERE {1}=:{1} ", GIVER_PASSWORD,ID);
        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromPass(id,encodedPassword));
    }


    public void delete(int giver_id) {
        String sql = MessageFormat.format("DELETE FROM giver WHERE {0}=:{0}", ID);
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue(ID, giver_id));
    }


}
