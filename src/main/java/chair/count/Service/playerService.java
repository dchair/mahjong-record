package chair.count.Service;

import chair.count.DAO.UserDao;
import chair.count.Model.playerModel;
import org.springframework.beans.factory.annotation.Autowired;

public class playerService {
    @Autowired
    UserDao userDao;
    public int reportnum(playerModel playerModel){
        if (saveUser(user.getPlayerName())){
            return 2;
        }
        int cnt userDao.saveUser(user);
    }
    public boolean isUserExists(String playername){
        return userDao.isUserExists(playername) > 0;
    }
}
