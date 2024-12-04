package chair.mahjong_record.rowMapper;

import chair.mahjong_record.model.GameSetting;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingRowMapper implements RowMapper<GameSetting> {
    @Override
    public GameSetting mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        GameSetting gameSetting = new GameSetting();
        gameSetting.setSettingId(resultSet.getInt("setting_id"));
        gameSetting.setBaseFanPrice(resultSet.getInt("base_fan_price"));
        gameSetting.setPerFanPrice(resultSet.getInt("per_fan_price"));
        gameSetting.setCreatedDate(resultSet.getTimestamp("created_date"));
        gameSetting.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return gameSetting;

    }
}
