package chair.count.Controller;

import chair.count.Model.PlayerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayerController {

    @GetMapping("/addplayer")
    public String playerinfo(Model model){
        //這裡開一個名字為addplayermodel  的模型(PlayerModel)
        PlayerModel addplayermodel =new PlayerModel();
        model.addAttribute("addplayermodel",addplayermodel);
        return "addplayer";
    }
    @PostMapping("/addplayer")
    public String completeinfo(@ModelAttribute("playerModel") PlayerModel playerModel, Model model){
        String msq =null;
        return "index";

    }
}
