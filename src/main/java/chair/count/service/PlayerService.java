package chair.count.service;


import chair.count.dto.PlayerRequest;
import chair.count.model.Player;

import java.util.List;


public interface PlayerService {
    Integer addPlayer(PlayerRequest playerRequest);
    Player getPlayerById(Integer playerId);
    List<Player>getPlayers();
}
