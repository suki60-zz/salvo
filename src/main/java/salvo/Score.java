package salvo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;


    private Double score;

    private Date date;

    public Score() {}

    public Score(Game game, Player player, Double score) {

        this.game = game;
        this.player = player;
        this.score = score;
        this.date = new Date();
    }

    // getters
    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Double getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }



}
