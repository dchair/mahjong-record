package chair.count.DAO.mapper;

import chair.count.Model.playerModel;

import javax.swing.tree.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<playerModel> {
    @Override
    public playerModel mapRow(ResultSet rs,int rowNum) throws SQLException{
        playerModel user = new playerModel();
        user.setPlayerName(rs.getString("playerName"));
        user.setChips(rs.getInt("chips"));
        return user;
    }
}




