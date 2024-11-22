package chair.mahjong_record.controller;

import chair.mahjong_record.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

    @Autowired
    PlayerService playerService;

    @PostMapping("validate")
    public ResponseEntity<Map<String, Boolean> >validatePlayer(@RequestBody List<String> playerNames ) {
        Map<String, Boolean> validationResults = new HashMap<>();
        for (String playerName : playerNames) {
            boolean exits =playerService.isPlayerExists(playerName);
            validationResults.put(playerName, exits);
        }
        return ResponseEntity.ok(validationResults);
    }

}
