package chair.count.DAO.mapper;

import chair.count.Model.PlayerModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper implements RowMapper<PlayerModel> {
    @Override
    public PlayerModel mapRow(ResultSet rs,int rowNum ) throws SQLException{
        PlayerModel player = new PlayerModel();
        player.setPlayerName(rs.getString("PlayerName"));
        player.setChips(rs.getInt("chips"));
        return player;
    }
}
