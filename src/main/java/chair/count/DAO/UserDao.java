package chair.count.DAO;

import chair.count.Model.playerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
        //新增會員
    public int saveUser(playerModel user){
        String sq1="INSERT INTO USER(playerName,chips) values(?,?)";
        return jdbcTemplate.update(sq1,user.getPlayerName(),user.getChips());
    }
        //
    public playerModel finfByPlayername(String playerName){
        String sql3="SELECT * FROM USER where playerName=? LIMIT 1";
        try {
            return  jdbcTemplate.queryForObject(sql3,new Object[]{playerName},new )
        }

    }


    public playerModel findByUsername(String username){
        String sql3 = "SELECT * FROM USER where username=? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql3, new Object[]{username}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // 如果找不到匹配的數據，返回 null
            return null;
        }
    }


        //查詢資料是否存在

