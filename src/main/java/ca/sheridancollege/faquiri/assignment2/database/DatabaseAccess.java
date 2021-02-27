package ca.sheridancollege.faquiri.assignment2.database;

import ca.sheridancollege.faquiri.assignment2.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    //global vars so we dont have to define multiple times
    @Autowired
    NamedParameterJdbcTemplate jdbc;

    MapSqlParameterSource namedParameters = new MapSqlParameterSource();

    //CREATE
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

    //READ: get teams query, grabs all of the teams depending on choice
    public List<Team> getTeams( int sortChoice){
        String query = "";
        switch (sortChoice){
            case 1:
                query = "SELECT * FROM Teams ORDER BY UPPER(TeamName) ASC"; //must make it upper case so its not case sensitive
                break;
            case 2:
                query = "SELECT * FROM Teams ORDER BY UPPER(Continent) ASC";
                break;
            case 3:
                query = "SELECT * FROM Teams ORDER BY ((3 * Won) + Drawn) DESC";
                break;

        }

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }

    //UPDATE: Update a user, update all fields
    public void updateTeamById(Team updatedTeam){
        String query = "UPDATE Teams SET TeamName = :name, Continent = :continent, Played = :gamesPlayed, Won = :wins, Drawn = :draws, Lost = :losses WHERE TeamID = :id";

        namedParameters.addValue("id", updatedTeam.getTeamID());
        namedParameters.addValue("name", updatedTeam.getTeamName());
        namedParameters.addValue("continent", updatedTeam.getContinent());
        namedParameters.addValue("gamesPlayed", updatedTeam.getPlayed());
        namedParameters.addValue("wins", updatedTeam.getWon());
        namedParameters.addValue("draws", updatedTeam.getDrawn());
        namedParameters.addValue("losses", updatedTeam.getLost());

        jdbc.update(query, namedParameters);

    }

    //Grab a specific team
    public List<Team> getTeamById(Long id){
        String query = "SELECT * FROM Teams WHERE TeamID = :id";
        namedParameters.addValue("id", id);

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }


    //DELETE
    public void deleteTeamById(Long id){
        String query = "DELETE FROM Teams WHERE TeamID = :id";

        namedParameters.addValue("id", id);

        jdbc.update(query,namedParameters);
    }
}
