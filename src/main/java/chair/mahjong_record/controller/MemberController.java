package chair.mahjong_record.controller;

import chair.mahjong_record.dto.MemberLoginRequest;
import chair.mahjong_record.dto.MemberRegisterRequest;
import chair.mahjong_record.model.Member;
import chair.mahjong_record.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("member")
//@SessionAttributes("member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("memberRegisterRequest", new MemberRegisterRequest());
        return "memberRegisterPage";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("memberRegisterRequest")MemberRegisterRequest memberRegisterRequest
            , Model model) {

        Optional<Member> existingMember = memberService.isMemberRegisteredByMail(memberRegisterRequest.getMemberMail());
        //如果會員已經存在，返回註冊頁面並顯示錯誤
        if(existingMember.isPresent()) {
            model.addAttribute("error", "這個信箱已經被註冊過了");
            return "memberRegisterPage";
        }
        //如果會員不存在，進行註冊
        try {
            memberService.register(memberRegisterRequest);
            model.addAttribute("success","註冊成功，請登入");
            return "memberRegisterPage";
        }catch (Exception e) {
            model.addAttribute("error","註冊過程出現錯誤，請稍後再試");
            return "memberRegisterPage";
        }

    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("memberLoginRequest", new MemberLoginRequest());
        return "memberLogin";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("memberLoginRequest")MemberLoginRequest memberLoginRequest
            ,Model model) {
        Optional<Member> existingMember = memberService.memberLogin(memberLoginRequest);
        //如果登入成功
        if (existingMember.isPresent()) {
            model.addAttribute("memberInfo", existingMember.get());
            return "redirect:/index";
        } else {
            model.addAttribute("error", "帳號或密碼錯誤，請重新輸入。");
            return "memberLogin";
        }
    }
}
