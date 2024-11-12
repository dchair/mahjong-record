package chair.count.rowMapper;

import chair.count.model.GameSettings;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingRowMapper implements RowMapper<GameSettings> {
    @Override
    public GameSettings mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        GameSettings gameSettings = new GameSettings();
        gameSettings.setSettingId(resultSet.getInt("setting_id"));
        gameSettings.setBaseFanPrice(resultSet.getInt("base_fan_price"));
        gameSettings.setPerFanPrice(resultSet.getInt("per_fan_price"));
        gameSettings.setCreatedDate(resultSet.getTimestamp("created_date"));
        gameSettings.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return gameSettings;

    }
}
