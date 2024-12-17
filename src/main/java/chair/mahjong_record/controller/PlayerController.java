package chair.mahjong_record.controller;

import chair.mahjong_record.dto.PlayerQueryParams;
import chair.mahjong_record.dto.PlayerRequest;
import chair.mahjong_record.model.Player;
import chair.mahjong_record.service.PlayerService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;


    @GetMapping("/player")
    public String player(Model model,
                     //排序Sorting
                     @RequestParam(defaultValue = "created_date")String orderBy,
                     @RequestParam(defaultValue = "asc")String sort,
                     //分頁Pagination
                     @RequestParam(defaultValue = "5")@Max(1000) @Min(0)Integer limit,
                     @RequestParam(defaultValue = "0") @Min(0)Integer offset){

        PlayerQueryParams playerQueryParams = new PlayerQueryParams();
        playerQueryParams.setOrderBy(orderBy);
        playerQueryParams.setSort(sort);
        playerQueryParams.setLimit(limit);
        playerQueryParams.setOffset(offset);
        model.addAttribute("playerQueryParams",playerQueryParams);

        List<Player> players = playerService.getPlayers(playerQueryParams);  // 獲取所有玩家
        model.addAttribute("players", players);  // 将玩家傳遞至前端

        //獲取玩家總數
        int totalPlayers = playerService.getTotalPlayerCount();
        int totalPages = (int) Math.ceil((double) totalPlayers / limit);
        int currentPage = offset / limit;

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);


        PlayerRequest playerRequest =new PlayerRequest();
        model.addAttribute("playerRequest",playerRequest);
        return "player";
    }

    @PostMapping("/player/add_player")
    public String add_player(@ModelAttribute("playerRequest") PlayerRequest playerRequest,
                             Model model){

        int playerId = playerService.addPlayer(playerRequest);
        Player player =playerService.getPlayerById(playerId);
        if(player!=null){

            model.addAttribute("message", "Create Success!");
            model.addAttribute("redirectUrl", "/player"); // 設定跳轉的目標 URL
        }else{
            model.addAttribute("message", "Create Failed!");
            model.addAttribute("redirectUrl", "/player");
        }
        return "result";
    }
    @PostMapping("/delete_player/{playerId}")
    public String delete_player(@PathVariable Integer playerId){
        playerService.deletePlayerById(playerId);
        //返回玩家修改介面
        return "redirect:/player";
    }

}
