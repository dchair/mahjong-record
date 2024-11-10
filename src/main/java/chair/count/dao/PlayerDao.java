package chair.count.dao;

import chair.count.dto.PlayerRequest;
import chair.count.model.Player;

import java.util.List;

public interface PlayerDao {
    Integer addPlayer(PlayerRequest playerRequest);
    Player getPlayerById(Integer playerId);
    Player getPlayerByName(String playerName);
    List<Player> getPlayers();

}
