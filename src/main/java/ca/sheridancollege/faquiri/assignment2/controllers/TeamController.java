package ca.sheridancollege.faquiri.assignment2.controllers;

import ca.sheridancollege.faquiri.assignment2.database.DatabaseAccess;
import ca.sheridancollege.faquiri.assignment2.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//7.00 of 2/5/21
@Controller
public class TeamController {

    @Autowired
    DatabaseAccess da; //global variable for DatabaseAccess so we can use it in different methods of controller

    //home page
    @GetMapping("/")
    public String home(){

        return "home";
    }

    //add a team

    //edit a team

    //delete a team

    //display all teams
    @GetMapping("/displayTeams")
    public ModelAndView display(){
        //go to the all teams page and pass in the list of all teams
        List<Team>t = da.getTeams();
        //

        return new ModelAndView("displayResults", "teams", da.getTeams());
    }
}
