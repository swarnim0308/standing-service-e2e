package com.standings.controller;

import com.standings.service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
@RestController
public class StandingsController {

    private final StandingsService standingsService;

    @Autowired
    public StandingsController(StandingsService standingsService) {
        this.standingsService = standingsService;
    }

    @GetMapping("/hello")
    ResponseEntity<String> getHelloMessage() {
        return new ResponseEntity<>("Hello from my service", HttpStatus.OK);
    }

    @GetMapping("/team-position")
    public ResponseEntity<String> getTeamStanding(
            @RequestParam String country,
            @RequestParam String leagueId,
            @RequestParam String teamName) {

        return standingsService.getTeamStanding(country, leagueId, teamName)
                .map(pos -> ResponseEntity.ok(String.format("Team '%s' is in position %s", teamName, pos)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("Team '%s' not found in league %s", teamName, leagueId)));
    }
// 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978
}
