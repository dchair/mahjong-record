package chair.mahjong_record.controller;

import chair.mahjong_record.dto.PlayerRequest;
import chair.mahjong_record.model.Player;
import chair.mahjong_record.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/add_player")
    public String add_player(Model model){
        //這裡開一個名字為 playerRequest  的模型(PlayerModel)
        PlayerRequest playerRequest =new PlayerRequest();
        model.addAttribute("playerRequest",playerRequest);
        return "add_player";
    }
    @GetMapping("player_data")
    public String getPlayers(Model model){
        List<Player> players = playerService.getPlayers();  // 获取所有玩家
        model.addAttribute("players", players);  // 将玩家数据传递给视图
        return "player_data";
    }

    @PostMapping("/add_player")
    public String add_player(@ModelAttribute("playerRequest") PlayerRequest playerRequest){

        int playerId = playerService.addPlayer(playerRequest);
        Player player =playerService.getPlayerById(playerId);
        if(player!=null){

            return "create_success";
        }else{
            return "create_fail";
        }
    }


}
