package chair.count.dao.impl;

import chair.count.dao.PlayerDao;
import chair.count.dto.PlayerRequest;
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
public class PlayerDaoImpl implements PlayerDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer addPlayer(PlayerRequest playerRequest) {
        String sql ="INSERT INTO player(player_name,chips)"+
                "VALUES(:playerName,:chips)";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("playerName", playerRequest.getPlayerName());
        map.put("chips" , playerRequest.getChips());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int playerId = keyHolder.getKey().intValue();

        return playerId;
    }
}
