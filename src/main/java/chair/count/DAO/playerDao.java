package chair.count.DAO;

import chair.count.Model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class playerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

        //新增會員
    public int saveplayer(PlayerModel player){
        String sq1="INSERT INTO PLAYERDATA(playerName,chips) values(?,?)";
        return jdbcTemplate.update(sq1,player.getPlayerName(),player.getChips());
    }
        //
    }





        //查詢資料是否存在

