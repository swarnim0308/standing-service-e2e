package com.standings.service;

import com.standings.config.ApiFootballSourceConfig;
import com.standings.dto.StandingDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Optional;

@Service
public class StandingsService {

    private final WebClient webClient;
    private final ApiFootballSourceConfig config;

    public StandingsService(WebClient.Builder webClientBuilder, ApiFootballSourceConfig config) {
        this.config = config;
        this.webClient = webClientBuilder.baseUrl(config.getBaseUrl()).build();
    }

    public Optional<String> getTeamStanding(String country, String leagueId, String teamName) {
        StandingDto[] response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("action", "get_standings")
                        .queryParam("league_id", leagueId)
                        .queryParam("APIkey", config.getApiKey())
                        .build())
                .retrieve()
                .bodyToMono(StandingDto[].class)
                .block();

        if (response != null) {
            return Arrays.stream(response)
                    .filter(dto -> dto.getTeamName().equalsIgnoreCase(teamName)
                            && dto.getCountryName().equalsIgnoreCase(country))
                    .map(StandingDto::getOverallLeaguePosition)
                    .findFirst();
        }
        return Optional.empty();
    }
}
