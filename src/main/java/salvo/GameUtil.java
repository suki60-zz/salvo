package salvo;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameUtil {

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
}
