package chair.count.dao;

import chair.count.dto.PlayerRequest;

public interface PlayerDao {
    Integer addPlayer(PlayerRequest playerRequest);
}
