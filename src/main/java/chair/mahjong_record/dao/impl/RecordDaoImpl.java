package chair.mahjong_record.dao.impl;

import chair.mahjong_record.dao.RecordDao;
import chair.mahjong_record.model.GameRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class RecordDaoImpl implements RecordDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createNDRecord(GameRecord gameRecord) {
        String sql ="INSERT INTO game_record (setting_id,dealer_name,calculate_fan," +
                "winner_name,win_money,loser_name,lose_money,set_id,created_date," +
                "last_modified_date) VALUES(:setting_id,:dealer_name,:calculate_fan," +
                ":winner_name,:win_money,:loser_name,:lose_money,:set_id,:created_date,:last_modified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("setting_id", gameRecord.getSettingId());
        map.put("dealer_name", gameRecord.getDealerName());
        map.put("calculate_fan", gameRecord.getCalculateFan());
        map.put("winner_name", gameRecord.getWinnerName());
        map.put("win_money", gameRecord.getWinMoney());
        map.put("loser_name",gameRecord.getLoserName());
        map.put("lose_money", gameRecord.getLoseMoney());
        map.put("set_id", gameRecord.getSetId());
        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);
        int recordId = keyHolder.getKey().intValue();
        return recordId;
    }
}
