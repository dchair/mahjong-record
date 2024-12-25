package chair.mahjong_record.dao.impl;

import chair.mahjong_record.dao.PlayerDao;
import chair.mahjong_record.dto.PlayerQueryParams;
import chair.mahjong_record.dto.PlayerRequest;
import chair.mahjong_record.model.Player;
import chair.mahjong_record.rowMapper.PlayerRowMapper;
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
public class PlayerDaoImpl implements PlayerDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer addPlayer(PlayerRequest playerRequest) {
        String sql ="INSERT INTO player(player_name,chips,created_date,last_modified_date)"+
                "VALUES(:playerName,:chips,NOW(),NOW()) RETURNING player_id";

        Map<String, Object> map = new HashMap<>();
        map.put("playerName", playerRequest.getPlayerName());
        map.put("chips" , playerRequest.getChips());


        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int playerId = keyHolder.getKey().intValue();

        return playerId;
    }

    @Override
    public Player getPlayerById(Integer playerId) {
        String sql ="SELECT player_id,player_name,chips,created_date,last_modified_date " +
                "FROM player where player_id = :playerId";
        Map<String, Object> map = new HashMap<>();
        map.put("playerId",playerId);
        List<Player> playerList = namedParameterJdbcTemplate.query(sql, map ,new PlayerRowMapper());

        if(playerList.size()>0){
            return playerList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Player getPlayerByName(String playerName) {
        String sql = "SELECT player_id,player_name,chips,created_date,last_modified_date " +
                "FROM player WHERE player_name = :playerName";
        Map<String ,Object> map = new HashMap<>();
        map.put("playerName",playerName);

        List<Player> playerList = namedParameterJdbcTemplate.query(sql, map ,new PlayerRowMapper());

        if(playerList.size()>0){
            return playerList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Player> getPlayers(PlayerQueryParams playerQueryParams) {
        String sql="SELECT player_id,player_name,chips,created_date,last_modified_date " +
                "FROM player WHERE 1=1";
        sql=sql+" ORDER BY "+ playerQueryParams.getOrderBy() +" "+playerQueryParams.getSort();

        Map<String ,Object> map = new HashMap<>();
        map.put("limit",playerQueryParams.getLimit());
        map.put("offset",playerQueryParams.getOffset());
        sql=sql+" LIMIT :limit OFFSET :offset";

        List<Player> players =namedParameterJdbcTemplate.query(sql,map, new PlayerRowMapper());
        return players;
    }

    @Override
    public List<Player> getPlayerSelect() {
        String sql="SELECT player_id,player_name,chips,created_date,last_modified_date " +
                "FROM player WHERE 1=1";
        List<Player> players =namedParameterJdbcTemplate.query(sql, new PlayerRowMapper());
        return players;

    }

    @Override
    public void deletePlayerById(Integer playerId) {
        String sql = "DELETE FROM player WHERE player_id = :playerId";

        Map<String, Object> map = new HashMap<>();
        map.put("playerId",playerId);

        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void updatePlayerChips(Integer playerId, Integer chips) {
        String sql ="UPDATE player SET chips = :chips ,last_modified_date = :lastModifiedDate"+
                " WHERE player_id = :playerId";
        Map<String, Object> map = new HashMap<>();
        map.put("playerId",playerId);
        map.put("chips",chips);
        map.put("lastModifiedDate",new Date());

        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public Integer getTotalPlayerCount() {
        String sql = "SELECT COUNT(*) FROM player";
        return namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
    }

    @Override
    public Player checkPlayerByName(String playerName) {
        String sql ="SELECT player_id,player_name,chips,created_date,last_modified_date FROM player" +
                " WHERE player_name = :playerName";
        Map<String, Object> map = new HashMap<>();
        map.put("playerName",playerName);

        try{
            return namedParameterJdbcTemplate.queryForObject(
                    sql,map,new BeanPropertyRowMapper<Player>(Player.class)
            );
        }catch(Exception e){
            return null;
        }


    }
}
