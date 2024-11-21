package chair.mahjong_record.service.impi;

import chair.mahjong_record.dao.PlayerDao;
import chair.mahjong_record.dto.PlayerRequest;
import chair.mahjong_record.model.Player;
import chair.mahjong_record.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class PlayerServiceImpl implements PlayerService {

    private final static Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired
    PlayerDao playerDao;


    @Override
    public Integer addPlayer(PlayerRequest playerRequest) {
        //檢查帳號是否存在
        Player player =playerDao.getPlayerByName(playerRequest.getPlayerName());
        if(player != null){
            log.warn("該遊戲名稱{}已經被註冊了",playerRequest.getPlayerName());
            //拋出一個exception
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"該遊戲名稱已經被註冊了");
        }else{
            return playerDao.addPlayer(playerRequest);}

    }

    @Override
    public void deletePlayerById(Integer playerId) {
        playerDao.deletePlayerById(playerId);
    }

    @Override
    public Player getPlayerById(Integer playerId) {
        return playerDao.getPlayerById(playerId);
    }

    @Override
    public List<Player> getPlayers() {
        return playerDao.getPlayers();
    }
}
