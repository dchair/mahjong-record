package chair.count.DAO;

import chair.count.Model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

        //新增會員
    public int saveplayer(PlayerModel player){
        String sq1="INSERT INTO PLAYERDATA(playerName,chips) values(?,?)";
        return jdbcTemplate.update(sq1,player.getPlayerName(),player.getChips());
    }
        //

    public long isPlayerExists(String playerName){
        String sql="SELECT COUNT (*) FROM PLAYERDATE WHERE PlayerName=?";
        return jdbcTemplate.queryForObject(sql,Long.class,playerName);
        }
    }







        //查詢資料是否存在

