package chair.mahjong_record.dao.impl;

import chair.mahjong_record.dao.SettingDao;
import chair.mahjong_record.dto.GameSettingQueryParams;
import chair.mahjong_record.dto.GameSettingRequest;
import chair.mahjong_record.model.GameSetting;
import chair.mahjong_record.rowMapper.SettingRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
public class SettingDaoImpl implements SettingDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public GameSetting getSettingById(Integer settingId) {
        String sql = "SELECT setting_id, base_fan_price ,per_fan_price ," +
                " created_date , last_modified_date FROM game_settings " +
                " WHERE setting_id = :settingId";
        Map<String , Object> map = new HashMap<>();
        map.put("settingId", settingId);

        List<GameSetting> gameSettingList = namedParameterJdbcTemplate.query(sql, map, new SettingRowMapper());

        if(!gameSettingList.isEmpty()){
            return gameSettingList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createSetting(GameSettingRequest gameSettingRequest) {
        String sql ="INSERT INTO game_settings (base_fan_price ,per_fan_price ," +
                " created_date , last_modified_date ) VALUES (:baseFanPrice," +
                ":perFanPrice,:createdDate,:lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("baseFanPrice", gameSettingRequest.getBaseFanPrice());
        map.put("perFanPrice", gameSettingRequest.getPerFanPrice());

        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);

        KeyHolder keyHolder =new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int settingId= keyHolder.getKey().intValue();
        System.out.println(settingId);
        return settingId;
    }

    @Override
    public List<GameSetting> getSettings(GameSettingQueryParams gameSettingQueryParams) {
        String sql="SELECT setting_id,base_fan_price,per_fan_price," +
                "created_date,last_modified_date FROM game_settings WHERE 1=1";
        sql=sql+" ORDER BY "+gameSettingQueryParams.getOrderBy() +" "+gameSettingQueryParams.getSort();

        Map<String ,Object> map = new HashMap<>();
        map.put("limit",gameSettingQueryParams.getLimit());
        map.put("offset",gameSettingQueryParams.getOffset());
        sql=sql+" LIMIT :limit OFFSET :offset";

        List<GameSetting> gameSettingList =namedParameterJdbcTemplate.query(sql,map, new SettingRowMapper());
        return gameSettingList;

    }

    @Override
    public List<GameSetting> getSettingsSelect() {
        String sql="SELECT setting_id,base_fan_price,per_fan_price," +
                "created_date,last_modified_date FROM game_settings WHERE 1=1";
        List<GameSetting> gameSettingsList =namedParameterJdbcTemplate.query(sql, new SettingRowMapper());
        return gameSettingsList;
    }

    @Override
    public GameSetting isSettingExists(Integer settingId) {
        String sql ="SELECT setting_id,base_fan_price,per_fan_price," +
                "created_date,last_modified_date FROM game_settings WHERE " +
                " setting_id =:settingId";
        Map<String, Object> map = new HashMap<>();
        map.put("settingId", settingId);

        try{
            return namedParameterJdbcTemplate.queryForObject(
                    sql,map,new BeanPropertyRowMapper<GameSetting>(GameSetting.class)
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Integer getTotalSettingCount() {
       String sql ="SELECT COUNT(*) FROM game_settings";
       return namedParameterJdbcTemplate.queryForObject(sql,new HashMap<>(),Integer.class);
    }

    @Override
    public void deleteSettingById(Integer settingId) {
        String sql ="DELETE FROM game_settings WHERE setting_id = :settingId";

        Map<String, Object> map = new HashMap<>();
        map.put("settingId", settingId);

        namedParameterJdbcTemplate.update(sql,map);
    }
}
