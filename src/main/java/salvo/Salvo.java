package salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_player_id")
    private GamePlayer gamePlayer;

    private int turn;

    @ElementCollection
    @Column(name = "locations")
    private List<String> locations;

    public Salvo() {}

    public Salvo(int turn, List<String> locations, GamePlayer gamePlayer) {

        this.turn = turn;
        this.locations = locations;
        this.gamePlayer = gamePlayer;
    }

    //getters
    public long getId() {
        return id;
    }

    @JsonIgnore
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public int getTurn() {
        return turn;
    }

    public List<String> getLocations() {
        return locations;
    }

}
