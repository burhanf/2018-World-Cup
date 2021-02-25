package ca.sheridancollege.faquiri.assignment2.controllers;

import ca.sheridancollege.faquiri.assignment2.database.DatabaseAccess;
import ca.sheridancollege.faquiri.assignment2.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//7.00 of 2/5/21
@Controller
public class TeamController {

    @Autowired
    DatabaseAccess da; //global variable for DatabaseAccess so we can use it in different methods of controller

    ModelAndView mv;

    public String continents[]={"N. America", "S. America", "Europe", "Asia", "Africa", "Oceania"};

    //home page
    @GetMapping("/")
    public String home(){

        return "Home";
    }

    //CREATE
    //1. from home, goes to add team where it passes in a empty object and returns "AddTeam.html:
    //@RequestParam String teamName, @RequestParam String continent, @RequestParam int gamesPlayed, @RequestParam int wins, @RequestParam int draws, @RequestParam int losses
    //add a team
    @GetMapping("/addNewTeam")
    public ModelAndView addNewTeam(){
//        model.addAttribute("team", new Team());
        //placeholder for team id

        //pass in empty team be filled out in form
        Team newTeam = new Team();
        //da.addTeam(newTeam);
        mv = new ModelAndView("AddTeam", "team", newTeam);
        mv.addObject("continents", continents);
        return mv;
    }
    //2. in AddTeam.html, it calls processTeam in the action
    @PostMapping("/processTeam")
    public String processTeam(@ModelAttribute Team team){
        da.addTeam(team);

        return "redirect:/addNewTeam";
    }

    //READ
    //display all teams
    @GetMapping("/displayTeams")
    public ModelAndView display(@RequestParam(defaultValue = "1") int sortOption){
        //grab result from user, need a request param

        //pass in a parameter to the get teams that will do it by name, continent or points

        //go to the all teams page and pass in the list of all teams
//        List<Team>t = da.getTeams();
        //

        return new ModelAndView("DisplayResults", "teams", da.getTeams(sortOption));
    }

    @PostMapping("/displayTeamsSorted")
    public ModelAndView displaySorted(@RequestParam int sortOption){
        //grab result from user, need a request param

        //pass in a parameter to the get teams that will do it by name, continent or points

        //go to the all teams page and pass in the list of all teams
       List<Team>t = da.getTeams(sortOption);
        //

        return new ModelAndView("DisplayResults", "teams", da.getTeams(sortOption));
    }


    //UPDATE
    //edit a team
    //1. load page so user sees all teams to edit
    @GetMapping("/edit")
    public ModelAndView edit(){
        //pass in the teams to the edit page to see all of them
        return new ModelAndView("EditTeam", "teams", da.getTeams(1));
    }

    //2. grab the team to edit and put it to a add team type page
    @GetMapping("editTeamById/{id}")
    public ModelAndView editTeam(@PathVariable Long id){
        //construct the team before its edited to pass into the process edit page
        Team team = da.getTeamById(id).get(0); //since a list is returned, we only want the first element

        //pass in the team to be modified to the editTeamDetails page
        mv = new ModelAndView("EditTeamDetails", "editedTeam", team);
        mv.addObject("continents", continents);
        return mv;
    }


    //3.carry the updated team object into the database
    @PostMapping("/processEdit")
    public String processEdit(@ModelAttribute Team editedTeam){
        //grab the updated details of the team and apply it in the database
        da.updateTeamById(editedTeam);

        return "redirect:/edit";
    }

    //DELETE
    //delete a team
    //2 parts, load the page with all teams like displayTeams
    @GetMapping("/delete")
    public ModelAndView delete(){
        //load the delete page with all of the teams

        return new ModelAndView("DeleteTeam", "teams", da.getTeams(1));
    }

    //mapping for the actual record thats to be deleted
    @GetMapping("/deleteTeamById/{id}")
    public ModelAndView deleteTeam(@PathVariable Long id){

        //pass in id to method in DatabaseAcess
        da.deleteTeamById(id);

        //data is changed so need to refresh database
        mv = new ModelAndView("redirect:/delete", "teams", da.getTeams(1));

        //no empty student needed since we make one in the home page
        return mv;
    }
}
