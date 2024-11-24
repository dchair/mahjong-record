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
import java.util.List;
import java.util.Map;

@Component
public class RecordDaoImpl implements RecordDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createNDRecord(GameRecord gameRecord) {
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
    }

    @Override
    public void createDRecord(List<GameRecord> gameRecordList) {
        String sql= "INSERT INTO game_record(setting_id,dealer_name,calculate_fan," +
                "winner_name,win_money,loser_name,lose_money,set_id,created_date," +
                "last_modified_date) VALUES(:setting_id,:dealer_name,:calculate_fan," +
                ":winner_name,:win_money,:loser_name,:lose_money,:set_id,:created_date,:last_modified_date)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[gameRecordList.size()];

        Date now = new Date();
        for(int i=0; i<gameRecordList.size();i++){
            GameRecord gameRecord = gameRecordList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("setting_id", gameRecord.getSettingId());
            parameterSources[i].addValue("dealer_name", gameRecord.getDealerName());
            parameterSources[i].addValue("calculate_fan", gameRecord.getCalculateFan());
            parameterSources[i].addValue("winner_name", gameRecord.getWinnerName());
            parameterSources[i].addValue("win_money", gameRecord.getWinMoney());
            parameterSources[i].addValue("loser_name", gameRecord.getLoserName());
            parameterSources[i].addValue("lose_money", gameRecord.getLoseMoney());
            parameterSources[i].addValue("set_id", gameRecord.getSetId());
            parameterSources[i].addValue("created_date", now);
            parameterSources[i].addValue("last_modified_date", now);

        }
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

}
