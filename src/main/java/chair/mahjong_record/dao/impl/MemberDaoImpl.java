package chair.mahjong_record.dao.impl;

import chair.mahjong_record.dao.MemberDao;
import chair.mahjong_record.dto.MemberRegisterRequest;
import chair.mahjong_record.model.Member;
import chair.mahjong_record.rowMapper.MemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void register(MemberRegisterRequest memberRegisterRequest) {
        String sql="INSERT INTO public.member (member_name,member_mail," +
                "member_password,created_date,last_modified_date)" +
                " VALUES (:memberName,:memberMail,:memberPassword," +
                "NOW(),NOW())" +" RETURNING member_id ";

        Map<String, Object> map = new HashMap<>();
        map.put("memberName",memberRegisterRequest.getMemberName());
        map.put("memberMail", memberRegisterRequest.getMemberMail());
        map.put("memberPassword", memberRegisterRequest.getMemberPassword());



        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);

        // 如果需要獲取自增主鍵
        int memberId = keyHolder.getKey().intValue();
        System.out.println("Generated Member ID: " +memberId);

    }

    @Override
    public Optional<Member> isMemberRegisteredByMail(String mail) {
        String sql ="SELECT * FROM public.member WHERE member_mail=:memberMail";
        Map<String, Object> map = new HashMap<>();
        map.put("memberMail", mail);

        try{
            Member member = namedParameterJdbcTemplate.queryForObject(sql, map, new MemberRowMapper());
            return Optional.of(member);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
