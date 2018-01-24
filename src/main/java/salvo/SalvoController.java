package salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("/games")
    public List<Map<String, Object>> getGames() {

        List<Map<String, Object>> list = new LinkedList<>();

        list = gameRepository
                .findAll()
                .stream()
                .map(GameUtil::mapGame)
                .collect(Collectors.toList());

        return list;
    }

    @RequestMapping("/players")
    public List<Map<String, Object>> listPlayers() {

        List<Map<String, Object>> list = new LinkedList<>();

        list = playerRepository
                .findAll()
                .stream()
                .map(GameUtil::mapListPlayers)
                .collect(Collectors.toList());

        return list;
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
        mapGamePlayer.put("salvos", gamePlayer.getGame().getGamePlayers()
                .stream()
                .map(gamePlayer1 -> gamePlayer1.getSalvos()
                        .stream()
                        .map(GameUtil::mapSalvo)
                        .collect(Collectors.toList()))
                .flatMap(lista -> lista.stream())
                .collect(Collectors.toList())
        );

        return mapGamePlayer;
    }
}