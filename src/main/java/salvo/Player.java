package salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String userName;

    @OneToMany(mappedBy = "player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<Score> scores = new HashSet<>();

    public Player() { }

    public Player(String user_name) {

        this.userName = user_name;
    }

    //getters
    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    @JsonIgnore
    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public Score getScore(Game game) {

        return scores.stream()
                .filter(score -> score.getGame().getId() == game.getId())
                .findFirst()
                .orElse(null);
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        //gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }

    public String toString() {
        return userName;
    }
}