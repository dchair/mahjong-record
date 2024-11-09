package chair.count.service.impi;

import chair.count.dao.PlayerDao;
import chair.count.dto.PlayerRequest;
import chair.count.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class playerServiceImpl implements PlayerService {

    @Autowired
    PlayerDao playerDao;

    @Override
    public Integer addPlayer(PlayerRequest playerRequest) {
        return playerDao.addPlayer(playerRequest);
    }
}
