package chair.count.Controller;

import chair.count.Model.playerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {


    @GetMapping("/index")
    public String index(Model model){

        return "index";
    }

    @GetMapping("/player")
    public String playerinfo(Model model){
        playerModel playermodel =new playerModel();
        model.addAttribute("playermodel",playermodel);
        return"player";
    }
    @PostMapping("/complete")
    public String completeinfo(@ModelAttribute playerModel playerModel,Model model){
        String msq =null;
        int result = userRegService.Registration(playerModel);
        switch (result){
            case 0:
                msq="新增失敗";
                break;
            case 1:
                msq="遊玩會員新增成功";
                break;
            case 2:
                msq="您的id已經被使用了";
                break;
            default:
                msq="其他原因 請聯絡本站管理員";
                break;
        }
        model.addAttribute("mesq",msq);
        return "index";

    }


}
