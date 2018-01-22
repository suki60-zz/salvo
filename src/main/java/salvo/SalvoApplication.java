package salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,
									  GameRepository gameRepository,
									  GamePlayerRepository gamePlayerRepository,
									  ShipRepository shipRepository,
									  SalvoRepository salvoRepository,
									  ScoreRepository scoreRepository) {
		return (args) -> {

			//Players
			Player p1 = new Player("player1@gmail.com");
			Player p2 = new Player("player2@gmail.com");
			playerRepository.save(p1);
			playerRepository.save(p2);



			//Games
			Game g1 = new Game();
			Game g2 = new Game();
			Game g3 = new Game();

			g2.setDate(3600);
			g3.setDate(7200);

			gameRepository.save(g1);
			gameRepository.save(g2);
			gameRepository.save(g3);



			//GamePlayers
			GamePlayer gp1 = new GamePlayer(p1, g1);
			GamePlayer gp2 = new GamePlayer(p2, g1);

			GamePlayer gp3 = new GamePlayer(p1, g2);
			GamePlayer gp4 = new GamePlayer(p2, g2);

			GamePlayer gp5 = new GamePlayer(p1, g3);
			GamePlayer gp6 = new GamePlayer(p2, g3);

			gamePlayerRepository.save(gp1);
			gamePlayerRepository.save(gp2);
			gamePlayerRepository.save(gp3);
			gamePlayerRepository.save(gp4);
			gamePlayerRepository.save(gp5);
			gamePlayerRepository.save(gp6);



			//Ships
			List<String> l1 = Arrays.asList("A1", "A2", "A3");
			Ship s1 = new Ship("destroyer", l1, gp1);
			gp1.addShip(s1);

			List<String> l2 = Arrays.asList("B1", "B2", "B3");
			Ship s2 = new Ship("cruise", l2, gp2);
			gp2.addShip(s2);

			shipRepository.save(s1);
			shipRepository.save(s2);



			//Salvo
			List<String> sl1 = Arrays.asList("B1", "A1");
			Salvo salvo1 = new Salvo(1, sl1, gp1);

			List<String> sl2 = Arrays.asList("A1", "B1");
			Salvo salvo2 = new Salvo(1, sl2, gp2);

			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);



			//Score
			Score score_1 = new Score(g1, p1, 1.0);
			Score score_2 = new Score(g1, p2, 0.0);

			scoreRepository.save(score_1);
			scoreRepository.save(score_2);



			//Others
			for (GamePlayer gamePlayer : g1.getGamePlayers()) {

				System.out.println(gamePlayer);
			}
			System.out.println(g1);

		};
	}
}