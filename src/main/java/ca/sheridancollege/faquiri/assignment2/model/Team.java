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

}
