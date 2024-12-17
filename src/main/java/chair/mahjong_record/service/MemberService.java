package chair.mahjong_record.service;

import chair.mahjong_record.dto.MemberLoginRequest;
import chair.mahjong_record.dto.MemberRegisterRequest;
import chair.mahjong_record.model.Member;

import java.util.Optional;

public interface MemberService {
    void register(MemberRegisterRequest memberRegisterRequest);
    Optional<Member> isMemberRegisteredByMail(String mail);
    Optional<Member> memberLogin(MemberLoginRequest memberLoginRequest);
}
