package chair.mahjong_record.dao;

import chair.mahjong_record.dto.PlayerQueryParams;
import chair.mahjong_record.dto.PlayerRequest;
import chair.mahjong_record.model.Player;

import java.util.List;

public interface PlayerDao {
    Integer addPlayer(PlayerRequest playerRequest);
    Player getPlayerById(Integer playerId);
    Player getPlayerByName(String playerName);
    List<Player> getPlayers(PlayerQueryParams playerQueryParams);
    List<Player> getPlayerSelect();
    void updatePlayerChips(Integer playerId, Integer chips);
    void deletePlayerById(Integer playerId);
    Player checkPlayerByName(String playerName);
    Integer  getTotalPlayerCount();


}
