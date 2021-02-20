package ca.sheridancollege.faquiri.assignment2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//7.00 of 2/5/21
@Controller
public class TeamController {



    @GetMapping("/")
    public String home(){

        return "home";
    }
}
