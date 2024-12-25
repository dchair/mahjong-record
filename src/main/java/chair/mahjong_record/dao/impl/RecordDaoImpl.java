package chair.mahjong_record.dao.impl;

import chair.mahjong_record.dao.RecordDao;
import chair.mahjong_record.dto.RecordSettingDTO;
import chair.mahjong_record.dto.RecordSettingDTOQueryParams;
import chair.mahjong_record.model.GameRecord;
import chair.mahjong_record.rowMapper.RecordSettingRowMapper;
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
                ":winner_name,:win_money,:loser_name,:lose_money,:set_id,NOW(),NOW())";

        Map<String, Object> map = new HashMap<>();
        map.put("setting_id", gameRecord.getSettingId());
        map.put("dealer_name", gameRecord.getDealerName());
        map.put("calculate_fan", gameRecord.getCalculateFan());
        map.put("winner_name", gameRecord.getWinnerName());
        map.put("win_money", gameRecord.getWinMoney());
        map.put("loser_name",gameRecord.getLoserName());
        map.put("lose_money", gameRecord.getLoseMoney());
        map.put("set_id", gameRecord.getSetId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);
    }

    @Override
    public void createDRecord(List<GameRecord> gameRecordList) {
        String sql= "INSERT INTO game_record(setting_id,dealer_name,calculate_fan," +
                "winner_name,win_money,loser_name,lose_money,set_id,created_date," +
                "last_modified_date) VALUES(:setting_id,:dealer_name,:calculate_fan," +
                ":winner_name,:win_money,:loser_name,:lose_money,:set_id,NOW(),NOW())";

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


        }
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    //未完成
    @Override
    public List<RecordSettingDTO> getRecordSettingDTOList(RecordSettingDTOQueryParams rSDTOQueryParams) {

        String sql ="SELECT r.record_id,r.setting_id,r.dealer_name,r.calculate_fan," +
                "r.winner_name,r.win_money,r.loser_name,r.lose_money,r.set_id," +
                "r.created_date,r.last_modified_date,s.base_fan_price,s.per_fan_price"+
                " FROM game_record r "+
                "JOIN game_settings s ON r.setting_id = s.setting_id WHERE 1=1";

        sql=sql+" ORDER BY "+ rSDTOQueryParams.getOrderBy() +" "+rSDTOQueryParams.getSort();

        Map<String, Object> map = new HashMap<>();
        map.put("limit",rSDTOQueryParams.getLimit());
        map.put("offset",rSDTOQueryParams.getOffset());
        sql=sql+" LIMIT :limit OFFSET :offset";

        List<RecordSettingDTO> recordSettingDTOList =namedParameterJdbcTemplate.query(sql,map,new RecordSettingRowMapper());
        return recordSettingDTOList;
    }

    @Override
    public Integer getTotalRecordCount() {
        String sql = "SELECT COUNT(*) FROM game_record";
        return namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
    }

    @Override
    public List<RecordSettingDTO> getRecordPageRecently(RecordSettingDTOQueryParams recordSettingDTOQueryParams, List<String> playerNames) {
        String sql = "SELECT r.record_id,r.setting_id,r.dealer_name,r.calculate_fan," +
                "r.winner_name,r.win_money,r.loser_name,r.lose_money,r.set_id," +
                "r.created_date,r.last_modified_date,s.base_fan_price,s.per_fan_price"+
                " FROM game_record r "+
                "JOIN game_settings s ON r.setting_id = s.setting_id WHERE 1=1";

        sql = sql + " AND WINNER_NAME IN (:playerNames) OR LOSER_NAME IN (:playerNames)";
        sql = sql + " ORDER BY " + recordSettingDTOQueryParams.getOrderBy() + " " + recordSettingDTOQueryParams.getSort();
        sql = sql + " LIMIT :limit OFFSET :offset";
        Map<String, Object> map = new HashMap<>();
        map.put("playerNames", playerNames);
        map.put("limit", recordSettingDTOQueryParams.getLimit());
        map.put("offset", recordSettingDTOQueryParams.getOffset());

        List<RecordSettingDTO> recordSettingDTOList = namedParameterJdbcTemplate.query(sql, map, new RecordSettingRowMapper());
        return recordSettingDTOList;
    }

}
