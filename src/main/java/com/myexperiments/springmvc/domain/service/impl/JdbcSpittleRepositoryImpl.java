package com.myexperiments.springmvc.domain.service.impl;

import com.myexperiments.springmvc.domain.model.Spittle;
import com.myexperiments.springmvc.domain.service.SpittleRepository;
import com.myexperiments.springmvc.security.condition.JdbcCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Conditional(JdbcCondition.class)
public class JdbcSpittleRepositoryImpl implements SpittleRepository {

    private static final String SELECT_SPITTLE_BY_ID = "select id, message, time, latitude, longitude " +
            "from spittle " +
            "where id = :id";

    private static final String INSERT_SPITTER =
            "insert into Spittle (message, time, latitude, longitude) " +
            "values (:message, :time, :latitude, :longitude)";

    @Qualifier("namedParameterJdbcTemplate")
    private JdbcOperations jdbcOperations;
    private DataSource dataSource;

    @Autowired
    public JdbcSpittleRepositoryImpl(JdbcOperations jdbcOperations, DataSource dataSource) {
        this.jdbcOperations = jdbcOperations;
        this.dataSource = dataSource;
    }

    @Override
    public Spittle addSpittle(Spittle spittle) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("message", spittle.getMessage());
        paramMap.put("time", spittle.getTime());
        paramMap.put("latitute", spittle.getLatitude());
        paramMap.put("longitude", spittle.getLongitude());
        jdbcOperations.update(INSERT_SPITTER, paramMap);
        return spittle;
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return null;
    }

    @Override
    public Spittle findOne(long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(SELECT_SPITTLE_BY_ID, paramSource);

        if (sqlRowSet.first()) {
            return new Spittle(
                    sqlRowSet.getLong(1),
                    sqlRowSet.getString(2),
                    sqlRowSet.getDate(3),
                    sqlRowSet.getDouble(4),
                    sqlRowSet.getDouble(5)
            );
        }
        return null;
    }

}
