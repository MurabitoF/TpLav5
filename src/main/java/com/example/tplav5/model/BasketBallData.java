package com.example.tplav5.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketBallData {
    @JsonProperty("League")
    private String league;
    @JsonProperty("Away Team")
    private String awayTeam;
    @JsonProperty("Away Score")
    private Integer awayScore;
    @JsonProperty("Home Team")
    private String homeTeam;
    @JsonProperty("Home Score")
    private Integer homeScore;
    @JsonProperty("Status")
    private String status;
}
