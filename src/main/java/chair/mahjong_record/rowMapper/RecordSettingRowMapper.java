package chair.mahjong_record.rowMapper;

import chair.mahjong_record.dto.RecordSettingDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordSettingRowMapper implements RowMapper<RecordSettingDTO> {
    @Override
    public RecordSettingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecordSettingDTO recordSettingDTO =new RecordSettingDTO();

        recordSettingDTO.setRecordId(rs.getInt("record_id"));
        recordSettingDTO.setSettingId(rs.getInt("setting_id"));
        recordSettingDTO.setDealerName(rs.getString("dealer_name"));
        recordSettingDTO.setCalculateFan(rs.getInt("calculate_fan"));
        recordSettingDTO.setWinnerName(rs.getString("winner_name"));
        recordSettingDTO.setWinMoney(rs.getInt("win_money"));
        recordSettingDTO.setLoserName(rs.getString("loser_name"));
        recordSettingDTO.setLoseMoney(rs.getInt("lose_money"));
        recordSettingDTO.setSetId(rs.getInt("set_id"));
        recordSettingDTO.setCreatedDate(rs.getTimestamp("created_date"));
        recordSettingDTO.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
        recordSettingDTO.setBaseFanPrice(rs.getInt("base_fan_price"));
        recordSettingDTO.setPerFanPrice(rs.getInt("per_fan_price"));

        return recordSettingDTO;
    }
}
