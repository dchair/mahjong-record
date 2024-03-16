package chair.count.Service;

import chair.count.DAO.PlayerDao;
import chair.count.Model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AddPlayerService {
    @Autowired
    PlayerDao playerDao;
    public int Registration(PlayerModel playerName){
    if(isPlayerExists(playerName.getPlayerName())){
        return 2;
    }
        int cnt = PlayerDao.savePlayer(playerName);
}


}
