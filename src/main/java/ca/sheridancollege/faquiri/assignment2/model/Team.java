package ca.sheridancollege.faquiri.assignment2.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Team {
    @NonNull
    private Long teamID;

    private String teamName;
    private String continent;
    private int numPlayedGames;
    private int numWonGames;
    private int numDrawnGames;
    private int numLostGames;

    private int points = (3 * numWonGames) + numDrawnGames;


}
