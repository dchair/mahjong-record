package chair.mahjong_record.rowMapper;

import chair.mahjong_record.model.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

        Member member = new Member();
        member.setMemberId(rs.getInt("member_id"));
        member.setMemberName(rs.getString("member_name"));
        member.setMemberMail(rs.getString("member_mail"));
        member.setMemberPassword(rs.getString("member_password"));
        member.setCreatedDate(rs.getTimestamp("created_date"));
        member.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return member;
    }
}
