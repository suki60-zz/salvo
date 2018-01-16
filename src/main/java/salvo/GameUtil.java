package salvo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public static List<Map<String, Object>> mapSalvo1(GamePlayer gamePlayer) {

        return gamePlayer.getSalvos()
                .stream()
                .map(GameUtil::mapSalvo2)
                .collect(Collectors.toList());
    }

    public static Map<String, Object> mapSalvo2(Salvo salvo) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("turn", salvo.getTurn());
        map.put("player", salvo.getGamePlayer().getPlayer().getId());
        map.put("gamePlayer", salvo.getGamePlayer().getId());
        map.put("location",  salvo.getLocations());

        return map;
    }
}
