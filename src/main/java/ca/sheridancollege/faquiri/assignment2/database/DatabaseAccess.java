package ca.sheridancollege.faquiri.assignment2.database;

import ca.sheridancollege.faquiri.assignment2.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//39.00 2/5/21
@Repository
public class DatabaseAccess {

    @Autowired
    NamedParameterJdbcTemplate jdbc;
    //1.05

    //get teams query
    public List<Team> getTeams(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM Teams";

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }
}
