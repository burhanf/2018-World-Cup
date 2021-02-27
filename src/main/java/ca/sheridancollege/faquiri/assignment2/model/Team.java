package ca.sheridancollege.faquiri.assignment2.model;


import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor

//Java bean = NoArgConstructor, Serializable and getters/setters (which is done through @Data)
public class Team implements Serializable {
    private Long teamID;
    @NonNull

    private String teamName;
    private String continent;
    private int played;
    private int won;
    private int drawn;
    private int lost;


    //points calculation will be a method
    public int calculatePoints(){
        return ( 3 * won) + drawn;
    }

}
