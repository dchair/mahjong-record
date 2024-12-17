package chair.mahjong_record.service.impi;

import chair.mahjong_record.dao.MemberDao;
import chair.mahjong_record.dto.MemberLoginRequest;
import chair.mahjong_record.dto.MemberRegisterRequest;
import chair.mahjong_record.model.Member;
import chair.mahjong_record.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberServiceImpl implements MemberService {

    private final static Logger log = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberDao memberDao;

    @Override
    public void register(MemberRegisterRequest memberRegisterRequest) {
        memberDao.register(memberRegisterRequest);
    }

    @Override
    public Optional<Member> isMemberRegisteredByMail(String mail) {
        return memberDao.isMemberRegisteredByMail(mail);
    }

    @Override
    public Optional<Member> memberLogin(MemberLoginRequest memberLoginRequest) {
        Optional <Member> existingMember =memberDao.isMemberRegisteredByMail(memberLoginRequest.getMemberMail());
//假設帳號存在
        if(existingMember.isPresent()){
            //密碼正確，回傳member
            if(memberLoginRequest.getMemberPassword().equals(existingMember.get().getMemberPassword())){
                return existingMember;
            }else{
                log.warn("該email{}的密碼不正確", memberLoginRequest.getMemberMail());
                return Optional.empty();
            }
            //密碼不存在
        }else
            log.warn("該email{}尚未註冊", memberLoginRequest.getMemberMail());

        return Optional.empty();
    }
}
