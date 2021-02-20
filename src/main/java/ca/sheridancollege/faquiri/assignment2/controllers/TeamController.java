package ca.sheridancollege.faquiri.assignment2.controllers;

import ca.sheridancollege.faquiri.assignment2.database.DatabaseAccess;
import ca.sheridancollege.faquiri.assignment2.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//7.00 of 2/5/21
@Controller
public class TeamController {

    @Autowired
    DatabaseAccess da; //global variable for DatabaseAccess so we can use it in different methods of controller

    ModelAndView mv;

    //home page
    @GetMapping("/")
    public String home(){

        return "home";
    }

    //CREATE
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
    //2. in addTeam.html, it calls processTeam in the action
    @PostMapping("/processTeam")
    public String processTeam(@ModelAttribute Team team){
        da.addTeam(team);

        return "redirect:/addNewTeam";
    }

    //READ
    //display all teams
    @GetMapping("/displayTeams")
    public ModelAndView display(){
        //go to the all teams page and pass in the list of all teams
//        List<Team>t = da.getTeams();
        //

        return new ModelAndView("displayResults", "teams", da.getTeams());
    }


    //UPDATE
    //edit a team

    //DELETE
    //delete a team
    //2 parts, load the page with all teams like displayTeams
    @GetMapping("/delete")
    public ModelAndView delete(){
        //load the delete page with all of the teams

        return new ModelAndView("deleteTeam", "teams", da.getTeams());
    }

    //mapping for the actual record thats to be deleted
    @GetMapping("/deleteTeamById/{id}")
    public ModelAndView deleteTeam(@PathVariable Long id){

        //pass in id to method in DatabaseAcess
        da.deleteTeamById(id);

        //data is changed so need to refresh database
        mv = new ModelAndView("redirect:/delete", "teams", da.getTeams());

        //no empty student needed since we make one in the home page
        return mv;
    }
}
