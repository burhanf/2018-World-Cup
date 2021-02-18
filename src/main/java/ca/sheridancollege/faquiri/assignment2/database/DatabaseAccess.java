package ca.sheridancollege.faquiri.assignment2.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

//39.00 2/5/21
@Repository
public class DatabaseAccess {

    @Autowired
    NamedParameterJdbcTemplate jdbc;
    //1.05
}
