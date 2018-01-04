package salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("/games")
    List<Map<String, Object>> getGames() {

        return gameRepository
                .findAll()
                .stream()
                .map(game -> mapGame(game))
                .collect(Collectors.toList());
    }

    private Map<String, Object> mapGame(Game game) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", game.getId());
        map.put("create", game.getDate());
        map.put("gamePlayers", game.getGamePlayers()
                .stream()
                .map(gamePlayer -> mapGamePlayer(gamePlayer))
                .collect(Collectors.toList()));

        return map;
    }

    private Map<String, Object> mapGamePlayer(GamePlayer gamePlayer) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", gamePlayer.getId());
        map.put("players", mapPlayer(gamePlayer.getPlayer()));

        return map;
    }

    private Map mapPlayer(Player player) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", player.getId());
        map.put("email", player.getUserName());

        return map;
    }
}