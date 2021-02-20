package ca.sheridancollege.faquiri.assignment2.model;


import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor

public class Team implements Serializable {
    private Long teamID;
    @NonNull

    private String teamName;
    private String continent;
    private int played;
    private int won;
    private int drawn;
    private int lost;


    public int calculatePoints(){
        return ( 3 * won) + drawn;
    }


//    public Team(long teamID, String teamName, String continent, int played, int won, int drawn, int lost) {
//        this.teamID = teamID;
//        this.teamName = teamName;
//        this.continent = continent;
//        this.numPlayedGames = played;
//        this.numWonGames = won;
//        this.numDrawnGames = drawn;
//        this.numLostGames = lost;
//
//        //With the new system, the points allocation is simpler: three points for a win, one point for a draw, and zero points for a loss
//        this.points =  (3 * numWonGames) + numDrawnGames;
//    }
}
