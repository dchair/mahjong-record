package chair.mahjong_record.controller;

import chair.mahjong_record.dto.PlayerRequest;
import chair.mahjong_record.model.Player;
import chair.mahjong_record.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/player/add_player")
    public String add_player(Model model){
        //這裡開一個名字為 playerRequest  的模型(PlayerModel)
        PlayerRequest playerRequest =new PlayerRequest();
        model.addAttribute("playerRequest",playerRequest);
        return "add_player";
    }
    @GetMapping("player/player_data")
    public String getPlayers(Model model){
        List<Player> players = playerService.getPlayers();  // 获取所有玩家
        model.addAttribute("players", players);  // 将玩家数据传递给视图
        return "player_data";
    }

    @PostMapping("/player/add_player")
    public String add_player(@ModelAttribute("playerRequest") PlayerRequest playerRequest,
                             Model model){

        int playerId = playerService.addPlayer(playerRequest);
        Player player =playerService.getPlayerById(playerId);
        if(player!=null){

            model.addAttribute("message", "Create Success!");
            model.addAttribute("redirectUrl", "/index"); // 設定跳轉的目標 URL
        }else{
            model.addAttribute("message", "Create Failed!");
            model.addAttribute("redirectUrl", "/add_player");
        }
        return "result";
    }
    @PostMapping("/delete_player/{playerId}")
    public String delete_player(@PathVariable Integer playerId){
        playerService.deletePlayerById(playerId);
        //返回玩家修改介面
        return "redirect:/player/delete_player";
    }

    @GetMapping("/player/delete_player")
    public String delete_player(Model model){
        //顯示資料庫目前的玩家狀況
        List<Player> players = playerService.getPlayers();
        model.addAttribute("players", players);
        return "delete_player";
    }



}
