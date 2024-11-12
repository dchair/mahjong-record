package chair.count.controller;

import chair.count.dto.GameSettingsRequest;
import chair.count.dto.PlayerRequest;
import chair.count.model.GameSettings;
import chair.count.model.Player;
import chair.count.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping("/game_setting")
    public String game_setting(Model model){
        GameSettingsRequest gameSettingsRequest = new GameSettingsRequest();
        model.addAttribute("gameSettingsRequest",gameSettingsRequest);
        return "game_settings";
    }
    @PostMapping("/game_setting")
    public String game_setting(@ModelAttribute("gameSettingsRequest") GameSettingsRequest gameSettingsRequest){
        int settingId =settingService.createSetting(gameSettingsRequest);
        GameSettings gameSettings = settingService.getSettingById(settingId);
        if (gameSettings!=null){
            return "create_success";
        }else {
        return "create_fail";
        }
    }
}
