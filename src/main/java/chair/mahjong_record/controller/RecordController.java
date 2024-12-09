package chair.mahjong_record.controller;

import chair.mahjong_record.Tracker.SetIdTracker;
import chair.mahjong_record.dto.*;
import chair.mahjong_record.model.GameSetting;
import chair.mahjong_record.model.Player;
import chair.mahjong_record.service.PlayerService;
import chair.mahjong_record.service.RecordService;
import chair.mahjong_record.service.SettingService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecordController {
    @Autowired
    SettingService settingService;
    @Autowired
    RecordService recordService;
    @Autowired
    SetIdTracker setIdTracker;
    @Autowired
    PlayerService playerService;

    //A
    @GetMapping("/record")
    public String record(Model model,
                         GameSettingRequest gameSettingRequest,
                         PlayerRequest playerRequest) {
        //將資料庫的setting找出，並用下拉式選單讓玩家做選擇
        List<GameSetting> gameSettingList = settingService.getSettingsSelect();
        //將資料庫的player找出，並用下拉式選單選玩家
        List<Player> players = playerService.getPlayerSelect();
        //備資料給前端準備
        model.addAttribute("gameSettingsList", gameSettingList);
        model.addAttribute("players",players);

        return "record_settings";
    }

    //B
    @PostMapping("/record")
    public String record_settings(HttpSession session,
                                  @RequestParam("settingId") Integer settingId,
                                  @RequestParam("playerName1") String playerName1,
                                  @RequestParam("playerName2") String playerName2,
                                  @RequestParam("playerName3") String playerName3,
                                  @RequestParam("playerName4") String playerName4){
        //先清空session內的值
        session.removeAttribute("playerNames");
        session.removeAttribute("settingId");

        if (playerName1.isBlank() || playerName2.isBlank() || playerName3.isBlank() || playerName4.isBlank()) {
            throw new IllegalArgumentException("所有玩家名稱都必須填寫");
        }//放入遊戲設定
        session.setAttribute("settingId", settingId);
        session.setAttribute("playerNames",List.of(playerName1,playerName2,playerName3,playerName4));
        //轉向紀錄
        return "redirect:/record/game_record";

    }
//C
//紀錄遊戲輸贏
    @GetMapping("record/game_record")
    public String record_game_record(HttpSession session,
                                    Model model,
                                     @RequestParam(defaultValue = "created_date")String orderBy,
                                     @RequestParam(defaultValue = "desc")String sort,
                                     //分頁Pagination
                                     @RequestParam(defaultValue = "10")@Max(1000) @Min(0)Integer limit,
                                     @RequestParam(defaultValue = "0") @Min(0)Integer offset
                                     ) {
        //假設沒有抓到他session內的玩家就導向他回record去設定玩家
       if(session.getAttribute("settingId") == null||session.getAttribute("playerNames") == null) {
           return "redirect:/record";
       }
        Integer settingId =(Integer)session.getAttribute("settingId");
        List<String> playerNames = (List<String>)session.getAttribute("playerNames");
        if (settingId == null || playerNames == null) {
            throw new IllegalStateException("Session 資料遺失，請重新選擇設定");
        }
        // 確保資料加入 Model
        model.addAttribute("settingId", settingId);
        model.addAttribute("playerNames", playerNames);
        //增加遊戲中的玩家資料
        List<Player> playerInfo=new ArrayList<>();
        for(String playerName : playerNames) {
            Player player = playerService.getPlayerByName(playerName);
           if(player != null) {
               playerInfo.add(player);
           }
        }
        // 將玩家資訊加入到 Model
        model.addAttribute("playerInfo", playerInfo);

        RecordSettingDTOQueryParams rSDTOQueryParams=new RecordSettingDTOQueryParams();
        rSDTOQueryParams.setOrderBy(orderBy);
        rSDTOQueryParams.setSort(sort);
        rSDTOQueryParams.setLimit(limit);
        rSDTOQueryParams.setOffset(offset);



        List<RecordSettingDTO> recordSettingDTOList =recordService.getRecordPageRecently(rSDTOQueryParams,playerNames);
        model.addAttribute("recordSettingDTOList", recordSettingDTOList);
        return "game_records";
    }
    //D
    //執行紀錄

    @PostMapping("/save_self_drawn")
    public String saveSelfDrawn( @RequestParam Integer settingId,
                                             @RequestParam String dealerName,
                                             @RequestParam Integer dealerStreak,
                                             @RequestParam Integer calculateFan,
                                             @RequestParam String winnerName,
                                             @RequestParam List<String> playerNames

                                             ){
        CreateRecordRequest createRecordRequest = new CreateRecordRequest();
        List<RecordInfo> recordInfoList =new ArrayList<>();
        //處理自摸的狀況，贏家以外的人都是輸家

        List<String> losers = new ArrayList<>(playerNames);
        losers.removeIf(player -> player.equals(winnerName));


//處理loser方面的資料
        for(String loserName : losers){
            RecordInfo recordInfo = new RecordInfo();
            recordInfo.setSettingId(settingId);
            recordInfo.setDealerName(dealerName);
            recordInfo.setDealerStreak(dealerStreak);
            recordInfo.setCalculateFan(calculateFan);
            recordInfo.setWinnerName(winnerName);
            recordInfo.setLoserName(loserName);
            recordInfoList.add(recordInfo);

        }
        createRecordRequest.setRecordInfoList(recordInfoList);

        //檢查輸出
        System.out.println(createRecordRequest);
        int saveId = setIdTracker.getAndIncrementSetId(1);// 每次請求自增
        System.out.println(saveId);
        recordService.createDRecord(settingId,createRecordRequest,saveId);
        return "redirect:/record/game_record";
    }

    @PostMapping("/save_non_self_drawn")
        public String saveNonSelfDrawn(@RequestParam Integer settingId,
                                                           @ModelAttribute("recordInfo") RecordInfo recordInfo){
        //使用service來計算邏輯
        int nonSaveId = setIdTracker.getAndIncrementSetId(1);// 每次請求自增
        recordService.createNDRecord(settingId,recordInfo,nonSaveId);
        return "redirect:/record/game_record";

    }
    @GetMapping("/record/record_data")
    public String record_record_data(Model model,
                                     @RequestParam(defaultValue = "created_date")String orderBy,
                                     @RequestParam(defaultValue = "desc")String sort,
                                     //分頁Pagination
                                     @RequestParam(defaultValue = "10")@Max(1000) @Min(0)Integer limit,
                                     @RequestParam(defaultValue = "0") @Min(0)Integer offset){
        RecordSettingDTOQueryParams rSDTOQueryParams=new RecordSettingDTOQueryParams();
        rSDTOQueryParams.setOrderBy(orderBy);
        rSDTOQueryParams.setSort(sort);
        rSDTOQueryParams.setLimit(limit);
        rSDTOQueryParams.setOffset(offset);
        model.addAttribute("rSDTOQueryParams", rSDTOQueryParams);

        List<RecordSettingDTO> recordSettingDTOList =recordService.getRecordSettingDTOList(rSDTOQueryParams);
        model.addAttribute("recordSettingDTOList", recordSettingDTOList);

        //獲取玩家總數
        int totalRecord = recordService.getTotalRecordCount();
        int totalPages = (int) Math.ceil((double) totalRecord / limit);
        int currentPage = offset / limit;

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);


        return "record_record_data";

    }

}
