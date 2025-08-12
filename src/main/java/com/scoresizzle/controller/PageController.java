package com.scoresizzle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/get-started")
    public String getStarted() {
        return "get-started";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/myteams")
    public String myTeams() {
        return "myteams";
    }

    @GetMapping("/mymatches")
    public String myMatches() {
        return "mymatches";
    }

    @GetMapping("/new-match")
    public String newMatch() {
        return "new-match";
    }

    @GetMapping("/new-team")
    public String newTeam() {
        return "new-team";
    }
}