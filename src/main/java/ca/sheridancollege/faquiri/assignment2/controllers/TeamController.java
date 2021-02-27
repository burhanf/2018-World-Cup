package ca.sheridancollege.faquiri.assignment2.controllers;

import ca.sheridancollege.faquiri.assignment2.database.DatabaseAccess;
import ca.sheridancollege.faquiri.assignment2.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TeamController {

    @Autowired
    DatabaseAccess da; //global variable for DatabaseAccess so we can use it in different methods of controller

    ModelAndView mv;

    //used for dropdown list
    public String continents[]={"N. America", "S. America", "Europe", "Asia", "Africa", "Oceania"};

    //home page
    @GetMapping("/")
    public String home(){
        return "Home";
    }

    //CREATE
    @GetMapping("/addNewTeam")
    public ModelAndView addNewTeam(){
        //pass in empty team be filled out in form

        //go to AddTeam.html
        mv = new ModelAndView("AddTeam", "team", new Team());
        mv.addObject("continents", continents);
        return mv;
    }
    //in AddTeam.html, it calls processTeam in the action
    @PostMapping("/processTeam")
    public String processTeam(@ModelAttribute Team team){
        da.addTeam(team); //add to database
        return "redirect:/addNewTeam";
    }

    //READ
    @GetMapping("/displayTeams")
    public ModelAndView display(@RequestParam(defaultValue = "1") int sortOption){
        //pass in a parameter to the get teams, will do it by name, continent or points
        return new ModelAndView("DisplayResults", "teams", da.getTeams(sortOption));
    }

    @PostMapping("/displayTeamsSorted")
    public ModelAndView displaySorted(@RequestParam int sortOption){
        //grab result from user, need a request param
        //todo may not need
        return new ModelAndView("DisplayResults", "teams", da.getTeams(sortOption));
    }


    //UPDATE
    @GetMapping("/edit")
    public ModelAndView edit(){
        //pass in the teams to the edit page to see all of them
        return new ModelAndView("EditTeam", "teams", da.getTeams(1));
    }

    //grab the team to edit and put it to a add team type page
    @GetMapping("editTeamById/{id}")
    public ModelAndView editTeam(@PathVariable Long id){
        //construct the team before its edited to pass into the process edit page
        Team team = da.getTeamById(id).get(0); //since a list is returned, we only want the first element

        //pass in the team to be modified to the editTeamDetails page
        mv = new ModelAndView("EditTeamDetails", "editedTeam", team);
        mv.addObject("continents", continents);
        return mv;
    }

    //carry the updated team object into the database
    @PostMapping("/processEdit")
    public String processEdit(@ModelAttribute Team editedTeam){
        //grab the updated details of the team and apply it in the database
        da.updateTeamById(editedTeam);

        return "redirect:/edit";
    }

    //DELETE
    //load the page with all teams like displayTeams
    @GetMapping("/delete")
    public ModelAndView delete(){
        return new ModelAndView("DeleteTeam", "teams", da.getTeams(1));
    }

    //mapping for the actual record thats to be deleted
    @GetMapping("/deleteTeamById/{id}")
    public ModelAndView deleteTeam(@PathVariable Long id){
        //pass in id to method in DatabaseAcess
        da.deleteTeamById(id);

        //data is changed so need to refresh database
        mv = new ModelAndView("redirect:/delete", "teams", da.getTeams(1));
        return mv;
    }
}
