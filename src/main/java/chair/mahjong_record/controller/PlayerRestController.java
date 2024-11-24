package chair.mahjong_record.controller;

import chair.mahjong_record.service.PlayerService;
import chair.mahjong_record.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PlayerRestController {

    @Autowired
    PlayerService playerService;
    @Autowired
    SettingService settingService;

    @PostMapping("players/validate")
    public ResponseEntity<Map<String, Boolean> >validatePlayer(@RequestBody List<String> playerNames ) {
        Map<String, Boolean> validationResults = new HashMap<>();
        for (String playerName : playerNames) {
            boolean exits =playerService.isPlayerExists(playerName);
            validationResults.put(playerName, exits);
        }
        return ResponseEntity.ok(validationResults);
    }
    //新增api端點，檢查settingId是否存在
//    @GetMapping("/setting/validate/{settingId}")
//    public ResponseEntity<Map<String, Boolean>> validateSetting(@PathVariable("settingId") Integer settingId){
//        boolean exists = settingService.isSettingExists(settingId);
//        if(!exists){
//           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"遊戲設定不存在");
//        }
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("exists", true);
//        return ResponseEntity.ok(response);
//    }

}
