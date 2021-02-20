package ca.sheridancollege.faquiri.assignment2.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

public class Team {
    private Long teamID;
    @NonNull

    private String teamName;
    private String continent;
    private int numPlayedGames;
    private int numWonGames;
    private int numDrawnGames;
    private int numLostGames;

    private int points = (3 * numWonGames) + numDrawnGames;


}
