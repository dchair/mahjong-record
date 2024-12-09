package chair.mahjong_record.service;


import chair.mahjong_record.dto.PlayerQueryParams;
import chair.mahjong_record.dto.PlayerRequest;
import chair.mahjong_record.model.Player;

import java.util.List;


public interface PlayerService {
    Integer addPlayer(PlayerRequest playerRequest);
    Player getPlayerById(Integer playerId);
    List<Player>getPlayers(PlayerQueryParams playerQueryParams);
    List<Player>getPlayerSelect();
    void deletePlayerById(Integer playerId);
    Boolean isPlayerExists(String playerName);
    Player getPlayerByName(String playerName);
    Integer  getTotalPlayerCount();
}
