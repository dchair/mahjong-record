package chair.mahjong_record.dao;

import chair.mahjong_record.dto.MemberLoginRequest;
import chair.mahjong_record.dto.MemberRegisterRequest;
import chair.mahjong_record.model.Member;

import java.util.Optional;

public interface MemberDao {
    void register(MemberRegisterRequest memberRegisterRequest);
    Optional<Member> isMemberRegisteredByMail(String mail);
}
