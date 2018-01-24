package salvo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GameUtil {

    public static Map<String, Object> mapGame(Game game) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", game.getId());
        map.put("create", game.getDate());
        map.put("gamePlayers", game.getGamePlayers()
                .stream()
                .map(GameUtil::mapGamePlayer)
                .collect(Collectors.toList())
        );

        return map;
    }

    public static Map<String, Object> mapGamePlayer(GamePlayer gamePlayer) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", gamePlayer.getId());

        if (gamePlayer.getScore() != null) {
            map.put("score", gamePlayer.getScore().getScore());
        }

        map.put("players", mapPlayer(gamePlayer.getPlayer()));

        return map;
    }

    public static Map<String, Object> mapPlayer(Player player) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", player.getId());
        map.put("email", player.getUserName());

        return map;
    }

    public static Map<String, Object> mapShips(Ship ship) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("type", ship.getType());
        map.put("location", ship.getLocation());

        return map;
    }

    public static Map<String, Object> mapSalvo(Salvo salvo) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("turn", salvo.getTurn());
        map.put("player", salvo.getGamePlayer().getPlayer().getId());
        map.put("gamePlayer", salvo.getGamePlayer().getId());
        map.put("location",  salvo.getLocations());

        return map;
    }

    public static Map<String, Object> mapListPlayers(Player player) {

        Map<String, Object> map = new LinkedHashMap<>();
        Set<Score> scores = player.getScores();

        map.put("player", player.getUserName());
        map.put("wins", findWins(scores));
        map.put("ties", findTies(scores));
        map.put("loses", findLoses(scores));
        map.put("total", totalScore(scores));

        return map;
    }

    public static Integer findWins(Set<Score> scores) {

        Integer num = 0;

        for (Score score : scores) {

            if (score.getScore() == 1.0) {
                num++;
            }
        }

        return num;
    }

    public static Integer findTies(Set<Score> scores) {

        Integer num = 0;

        for (Score score : scores) {

            if (score.getScore() == 0.5) {
                num++;
            }
        }

        return num;
    }

    public static Integer findLoses(Set<Score> scores) {

        Integer num = 0;

        for (Score score : scores) {

            if (score.getScore() == 0.0) {
                num++;
            }
        }

        return num;
    }

    public static Double totalScore(Set<Score> scores) {

        Double num;

        num = findWins(scores) + findTies(scores) * 0.5;

        return num;
    }
}
