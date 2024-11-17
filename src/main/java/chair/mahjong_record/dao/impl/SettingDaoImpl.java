package chair.mahjong_record.dao.impl;

import chair.mahjong_record.dao.SettingDao;
import chair.mahjong_record.dto.GameSettingsRequest;
import chair.mahjong_record.model.GameSettings;
import chair.mahjong_record.rowMapper.SettingRowMapper;
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
public class SettingDaoImpl implements SettingDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public GameSettings getSettingById(Integer settingId) {
        String sql = "SELECT setting_id, base_fan_price ,per_fan_price ," +
                " created_date , last_modified_date FROM game_settings " +
                " WHERE setting_id = :settingId";
        Map<String , Object> map = new HashMap<>();
        map.put("settingId", settingId);

        List<GameSettings> gameSettingsList = namedParameterJdbcTemplate.query(sql, map, new SettingRowMapper());

        if(!gameSettingsList.isEmpty()){
            return gameSettingsList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createSetting(GameSettingsRequest gameSettingsRequest) {
        String sql ="INSERT INTO game_settings (base_fan_price ,per_fan_price ," +
                " created_date , last_modified_date ) VALUES (:baseFanPrice," +
                ":perFanPrice,:createdDate,:lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("baseFanPrice", gameSettingsRequest.getBaseFanPrice());
        map.put("perFanPrice", gameSettingsRequest.getPerFanPrice());

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
    public List<GameSettings> getSettings() {
        String sql="SELECT setting_id,base_fan_price,per_fan_price," +
                "created_date,last_modified_date FROM game_settings";
        return namedParameterJdbcTemplate.query(sql, new SettingRowMapper());
    }
}
