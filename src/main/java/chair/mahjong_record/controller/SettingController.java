package chair.mahjong_record.controller;

import chair.mahjong_record.dto.GameSettingsRequest;
import chair.mahjong_record.model.GameSettings;
import chair.mahjong_record.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping("/add_game_setting")
    public String game_setting(Model model){
        GameSettingsRequest gameSettingsRequest = new GameSettingsRequest();
        model.addAttribute("gameSettingsRequest",gameSettingsRequest);
        return "game_settings";
    }
    @PostMapping("/add_game_setting")
    public String game_setting(@ModelAttribute("gameSettingsRequest") GameSettingsRequest gameSettingsRequest){
        int settingId =settingService.createSetting(gameSettingsRequest);
        GameSettings gameSettings = settingService.getSettingById(settingId);
        if (gameSettings!=null){
            return "create_success";
        }else {
        return "create_fail";
        }
    }
    @GetMapping("/game_setting/{settingId}")
        public String read_game_setting(@PathVariable Integer settingId,
                                        Model model){
        GameSettings gameSettings =settingService.getSettingById(settingId);
        if(gameSettings!=null){
            model.addAttribute("gameSettings",gameSettings);
            return "read_game_setting";
        }else{
            return "error";
        }
    }
    @GetMapping("/game_settings")
    public String settings(Model model) {
        List<GameSettings> gameSettingsList = settingService.getSettings();
        model.addAttribute("gameSettingsList",gameSettingsList);
        return "read_game_settings";
    }
}