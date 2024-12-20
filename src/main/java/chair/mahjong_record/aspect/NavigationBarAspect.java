package chair.mahjong_record.aspect;

import chair.mahjong_record.model.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class NavigationBarAspect {

    @Pointcut("execution(* chair.mahjong_record.controller.*.*(..))" +
            " && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void pageControllerMethods(){
        //定義切入點,針對所有的GetMapping
    }

    @Before("pageControllerMethods()")
    public void addMemberInfoModel(JoinPoint joinPoint){
        //JoinPoint：提供關於當前攔截方法的資訊，比如方法參數、名稱等。
        Object[] args = joinPoint.getArgs();
        for(Object arg : args){
            if(arg instanceof Model){
                Model model = (Model)arg;

                //從session中獲取會員資訊
                HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
                //預設沒有
                HttpSession session = request.getSession(false);
                if(session != null){
                    Member member = (Member)session.getAttribute("memberInfo");
                    if (member != null) {
                        model.addAttribute("memberInfo", member);
                    }
                }

            }
        }
    }

}
