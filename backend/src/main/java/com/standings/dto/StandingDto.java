package com.standings.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandingDto {

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("league_id")
    private String leagueId;

    @JsonProperty("league_name")
    private String leagueName;

    @JsonProperty("team_name")
    private String teamName;

    @JsonProperty("overall_league_position")
    private String overallLeaguePosition;

    public String getCountryName() {
        return countryName;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getOverallLeaguePosition() {
        return overallLeaguePosition;
    }
}

