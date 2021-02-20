package ca.sheridancollege.faquiri.assignment2.controllers;

import ca.sheridancollege.faquiri.assignment2.database.DatabaseAccess;
import ca.sheridancollege.faquiri.assignment2.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //2. in addTeam.html, it calls processTeam in the action
    @PostMapping("/processTeam")
    public String processTeam(@ModelAttribute Team team){
        da.addTeam(team);

        return "redirect:/addNewTeam";
    }


    //1. from home, goes to add team where it passes in a empty object and returns "addTeam.html:
    //@RequestParam String teamName, @RequestParam String continent, @RequestParam int gamesPlayed, @RequestParam int wins, @RequestParam int draws, @RequestParam int losses
    //add a team
    @GetMapping("/addNewTeam")
    public ModelAndView addNewTeam(){
//        model.addAttribute("team", new Team());
        //placeholder for team id

        //pass in empty team be filled out in form
        Team newTeam = new Team();
        //da.addTeam(newTeam);

        return new ModelAndView("addTeam", "team", newTeam);
    }

    //edit a team

    //delete a team

    //display all teams
    @GetMapping("/displayTeams")
    public ModelAndView display(){
        //go to the all teams page and pass in the list of all teams
//        List<Team>t = da.getTeams();
        //

        return new ModelAndView("displayResults", "teams", da.getTeams());
    }
}
