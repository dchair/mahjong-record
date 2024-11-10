package chair.count.rowMapper;

import chair.count.model.Player;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerRowMapper implements RowMapper<Player> {

    @Override
    public Player mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Player player = new Player();
        player.setPlayerId(resultSet.getInt("player_id"));
        player.setPlayerName(resultSet.getString("player_name"));
        player.setChips(resultSet.getInt("chips"));
        player.setCreatedDate(resultSet.getTimestamp("created_date"));
        player.setLastModified(resultSet.getTimestamp("last_modified_date"));

        return player;

    }
}
