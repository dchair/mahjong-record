package chair.count.controller;

import chair.count.dto.PlayerRequest;
import chair.count.model.Player;
import chair.count.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/addplayer")
    public String playerinfo(Model model){
        //這裡開一個名字為 playerRequest  的模型(PlayerModel)
        PlayerRequest playerRequest =new PlayerRequest();
        model.addAttribute("playerRequest",playerRequest);
        return "addplayer";
    }

    @PostMapping("/addplayer")
    public String addplayer(@ModelAttribute("playerRequest") PlayerRequest playerRequest){
        int playerId = playerService.addPlayer(playerRequest);
        Player player =playerService.getPlayerById(playerId);

        return "index";


    }


}
