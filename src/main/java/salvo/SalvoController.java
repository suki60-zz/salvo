package salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @RequestMapping("/games")
    List<Map<String, Object>> getGames() {

        return gameRepository
                .findAll()
                .stream()
                .map(SalvoController::mapGame)
                .collect(Collectors.toList());
    }

    //GAME VIEWS
    @RequestMapping("/game_view/{nn}")
    public Map<String, Object> mapGameView(@PathVariable Long nn) {

        GamePlayer gamePlayer = gamePlayerRepository.findOne(nn);

        Map<String, Object> mapGamePlayer = new LinkedHashMap<>();

        mapGamePlayer.put("id", gamePlayer.getGame().getId());
        mapGamePlayer.put("date", gamePlayer.getGame().getDate());
        mapGamePlayer.put("gamePlayers", gamePlayer.getGame().getGamePlayers()
                .stream()
                .map(GameUtil::mapGamePlayer)
                .collect(Collectors.toList()));
        mapGamePlayer.put("ships", gamePlayer.getShips()
                .stream()
                .map(GameUtil::mapShips)
                .collect(Collectors.toList()));


        return mapGamePlayer;
    }


    //map functions
    private static Map<String, Object> mapGame(Game game) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", game.getId());
        map.put("create", game.getDate());
        map.put("gamePlayers", game.getGamePlayers()
                .stream()
                .map(GameUtil::mapGamePlayer)
                .collect(Collectors.toList()));

        return map;
    }
}