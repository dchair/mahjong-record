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
    public String game_setting(@ModelAttribute("gameSettingsRequest") GameSettingsRequest gameSettingsRequest,
                               Model model){
        int settingId =settingService.createSetting(gameSettingsRequest);
        GameSettings gameSettings = settingService.getSettingById(settingId);
        if(gameSettings!=null){
            model.addAttribute("message", "Create Success!");
            model.addAttribute("redirectUrl", "/index"); // 設定跳轉的目標 URL
        }else{
            model.addAttribute("message", "Create Failed!");
            model.addAttribute("redirectUrl", "/add_player");
        }
        return "result";
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

    @GetMapping("/setting/validate/{settingId}")
    public String checkSetting(@PathVariable("settingId") Integer settingId, Model model) {
        // 檢查settingId是否存在
        boolean exists = settingService.isSettingExists(settingId);

        if (!exists) {
            // 如果不存在，將錯誤訊息添加到模型並重定向到錯誤頁面
            model.addAttribute("errormessage", "設定ID " + settingId + " 不存在");
            return "error"; // error.html頁面
        }

        // 如果存在，繼續處理邏輯
        // ...
        return "game_settings"; // 如果設定存在，返回遊戲設定頁面
    }
}
