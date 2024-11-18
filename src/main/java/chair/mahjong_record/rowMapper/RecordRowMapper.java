package chair.mahjong_record.rowMapper;

import chair.mahjong_record.model.GameRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordRowMapper implements RowMapper<GameRecord> {
    @Override
    public GameRecord mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        GameRecord gameRecord = new GameRecord();
        gameRecord.setRecordId(resultSet.getInt("record_id"));
        gameRecord.setSettingId(resultSet.getInt("setting_id"));
        gameRecord.setDealerName(resultSet.getString("dealer_name"));
        gameRecord.setCalculateFan(resultSet.getInt("calculate_fan"));
        gameRecord.setWinnerName(resultSet.getString("winner_name"));
        gameRecord.setWinMoney(resultSet.getInt("win_money"));
        gameRecord.setLoserName(resultSet.getString("lose_name"));
        gameRecord.setLoseMoney(resultSet.getInt("lose_money"));
        gameRecord.setSetId(resultSet.getInt("set_id"));
        gameRecord.setCreatedDate(resultSet.getTimestamp("created_date"));
        gameRecord.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return gameRecord;
    }
}
