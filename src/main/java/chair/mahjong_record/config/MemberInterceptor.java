package chair.mahjong_record.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MemberInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("memberInfo")==null){
            //若未登入，定位至登入
            response.sendRedirect("/member/login");
            return false;
        }
        //若登入，放行
        return true;
    }
}
