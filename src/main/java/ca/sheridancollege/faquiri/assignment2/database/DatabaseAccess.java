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
    MapSqlParameterSource namedParameters = new MapSqlParameterSource();

    //add team
    public void addTeam(Team newTeam){
        String query = "INSERT INTO Teams (TeamName, Continent, Played, Won, Drawn, Lost) VALUES (:name, :continent, :gamesPlayed, :wins, :draws, :losses)";

        namedParameters.addValue("name", newTeam.getTeamName());
        namedParameters.addValue("continent", newTeam.getContinent());
        namedParameters.addValue("gamesPlayed", newTeam.getPlayed());
        namedParameters.addValue("wins", newTeam.getWon());
        namedParameters.addValue("draws", newTeam.getDrawn());
        namedParameters.addValue("losses", newTeam.getLost());

        //update the database
        jdbc.update(query, namedParameters);

    }

    //get teams query
    public List<Team> getTeams(){
        String query = "SELECT * FROM Teams";

        //Reference: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/RowMapper.html
//        return jdbc.query(query, namedParameters, new RowMapper<Team>() {
//            @Override
//            public Team mapRow(ResultSet resultSet, int i) throws SQLException {
//                return new Team(resultSet.getLong("TeamID"), resultSet.getString("TeamName"),
//                        resultSet.getString("Continent"), resultSet.getInt("Played"), resultSet.getInt("Won"),
//                        resultSet.getInt("Drawn"), resultSet.getInt("Lost"));
//            }
//        });
        //todo fix bean property row mapper
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }
}
