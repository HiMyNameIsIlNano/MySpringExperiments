package com.myexperiments.springmvc.domain.service.impl;

import com.myexperiments.springmvc.domain.model.Spitter;
import com.myexperiments.springmvc.domain.service.SpitterRepository;
import com.myexperiments.springmvc.security.condition.JdbcCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Conditional(JdbcCondition.class)
public class JdbcSpitterRepositoryImpl implements SpitterRepository {

    private static final String INSERT_SPITTER = "insert into spitter (username, password, fullname) values (?, ?, ?)";
    private static final String SELECT_SPITTER_BY_NAME = "select id, username, fullname from spitter where name = ?";
    private static final String SELECT_SPITTER_BY_ID = "select id, username, fullname from spitter where id = ?";

    @Qualifier("jdbcTemplate")
    private JdbcOperations jdbcOperations;
    private DataSource dataSource;

    /**
     * The constructor is annotated with @Autowired so that when it is created, it will be given a JdbcOperations object.
     * JdbcOperations is an interface defining operations implemented by JdbcTemplate.
     */
    @Autowired
    public JdbcSpitterRepositoryImpl(JdbcOperations jdbcOperations, DataSource dataSource) {
        this.jdbcOperations = jdbcOperations;
        this.dataSource = dataSource;
    }

    public void savePlainJdbc(Spitter spitter) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(INSERT_SPITTER);
            stmt.setString(1, spitter.getUsername());
            stmt.setString(2, spitter.getPassword());
            stmt.setString(3, spitter.getFirstName());
            stmt.execute();
        } catch (SQLException e) {
            // do something...not sure what, though
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // I'm even less sure about what to do here
            }
        }
    }

    @Override
    public Spitter save(Spitter spitter) {
        jdbcOperations.update(INSERT_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFirstName());

        return spitter;
    }

    /**
     * This Method is using a Row Mapper to provide a Spitter as a result from the query.
     */
    @Override
    public Spitter findByUserName(String user) {
        return jdbcOperations.queryForObject(SELECT_SPITTER_BY_NAME,
                new SpitterRowMapper(),
                user);
    }

    /**
     * This Method is using a Lambdas to provide a Spitter as a result from the query.
     */
    @Override
    public Spitter findById(Long id) {
        return jdbcOperations.queryForObject(SELECT_SPITTER_BY_ID,
                (rs, rowNumber) -> new Spitter(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("firstName"),
                        rs.getString("lastName")),
                id);
    }

    static final class SpitterRowMapper implements RowMapper<Spitter> {
        /**
         * For every row that results from the query, JdbcTemplate calls the mapRow() method of the RowMapper, passing
         * in a ResultSet and an integer carrying the row number.
         */
        public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spitter(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("firstName"),
                    rs.getString("lastName"));
        }
    }
}
