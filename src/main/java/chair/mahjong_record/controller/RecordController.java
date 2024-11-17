package chair.mahjong_record.controller;

import chair.mahjong_record.dto.CreateRecordRequest;
import chair.mahjong_record.dto.GameRecordRequest;
import chair.mahjong_record.dto.RecordInfo;
import chair.mahjong_record.model.GameSettings;
import chair.mahjong_record.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RecordController {
    @Autowired
    SettingService settingService;


    @GetMapping("/game_setting/{settingId}/record")
    public String game_record(@PathVariable Integer settingId,
                              @RequestParam String playerName1,
                              @RequestParam String playerName2,
                              @RequestParam String playerName3,
                              @RequestParam String playerName4,

                              Model model){
        System.out.println(settingId);
        GameSettings gameSettings = settingService.getSettingById(settingId);
//假設這個setting不存在
        if(gameSettings == null){
            return "game_settings";
        }

        List<String> playerNames = new ArrayList<>(Arrays.asList(playerName1,playerName2,playerName3,playerName4));
        model.addAttribute("playerNames", playerNames);

        return "game_record";

    }
    @PostMapping("/save_self_drawn")
    public ResponseEntity<?> saveSelfDrawn( @RequestParam Integer settingId,
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
        return ResponseEntity.ok(createRecordRequest);
    }

    @PostMapping("/save_non_self_drawn")
        public ResponseEntity<RecordInfo> saveNonSelfDrawn(@RequestParam Integer settingId,
                                                           @ModelAttribute("recordInfo") RecordInfo recordInfo){
        System.out.println(recordInfo);
        System.out.println(settingId);

        //使用service來計算邏輯
        recordService.createNDRecord(settingId,recordInfo);
        return ResponseEntity.ok(recordInfo) ;
    }

}
