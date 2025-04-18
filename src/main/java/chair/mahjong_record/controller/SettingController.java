package chair.mahjong_record.controller;

import chair.mahjong_record.dto.GameSettingQueryParams;
import chair.mahjong_record.dto.GameSettingRequest;
import chair.mahjong_record.model.GameSetting;
import chair.mahjong_record.service.SettingService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SettingController {

    @Autowired
    private SettingService settingService;


//    @GetMapping("/gameSetting")
//    public String game_setting(Model model,
//                         //排序Sorting
//                         @RequestParam(defaultValue = "created_date")String orderBy,
//                         @RequestParam(defaultValue = "asc")String sort,
//                         //分頁Pagination
//                         @RequestParam(defaultValue = "5")@Max(1000) @Min(0)Integer limit,
//                         @RequestParam(defaultValue = "0") @Min(0)Integer offset) {
//
//        GameSettingQueryParams gameSettingQueryParams = new GameSettingQueryParams();
//        gameSettingQueryParams.setOrderBy(orderBy);
//        gameSettingQueryParams.setSort(sort);
//        gameSettingQueryParams.setLimit(limit);
//        gameSettingQueryParams.setOffset(offset);
//        model.addAttribute("gameSettingQueryParams", gameSettingQueryParams);
//
//        List<GameSetting> gameSettingList =settingService.getSettings(gameSettingQueryParams);
//        model.addAttribute("gameSettingList", gameSettingList);
//        //使前端獲得數據得以呈現
//
//        //獲取玩家總數
//        int totalSettings = settingService.getTotalSettingCount();
//        int totalPages = (int) Math.ceil((double) totalSettings / limit);
//        int currentPage = offset / limit;
//
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("currentPage", currentPage);
//        //使前端獲得數據得以呈現
//
//        GameSettingRequest gameSettingRequest = new GameSettingRequest();
//        model.addAttribute("gameSettingsRequest", gameSettingRequest);
//
//        return "gameSetting";
//    }

    @GetMapping("/gameSetting")
    public String game_setting(@ModelAttribute GameSettingQueryParams gameSettingQueryParams, Model model){
        try {
            List<GameSetting> gameSettingList = settingService.getSettings(gameSettingQueryParams);
            int total = settingService.getTotalSettingCount();
            int totalPages = (int) Math.ceil((double) total / gameSettingQueryParams.getLimit());
            int currentPage = gameSettingQueryParams.getOffset() / gameSettingQueryParams.getLimit();

            model.addAttribute("gameSettingList", gameSettingList);
            model.addAttribute("gameSettingQueryParams", gameSettingQueryParams);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("gameSettingsRequest", new GameSettingRequest());
            return "gameSetting";
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/delete_setting/{settingId}")
    public String delete_setting(@PathVariable Integer settingId) {
        settingService.deleteSettingById(settingId);
        return "redirect:/gameSetting";
    }


    @PostMapping("/add_game_setting")
    public String game_setting(@ModelAttribute("gameSettingsRequest") GameSettingRequest gameSettingRequest,
                               Model model){
        int settingId =settingService.createSetting(gameSettingRequest);
        GameSetting gameSetting = settingService.getSettingById(settingId);
        if(gameSetting !=null){
            model.addAttribute("message", "Create Success!");
            model.addAttribute("redirectUrl", "/gameSetting"); // 設定跳轉的目標 URL
        }else{
            model.addAttribute("message", "Create Failed!");
            model.addAttribute("redirectUrl", "/gameSetting");
        }
        return "result";
    }

//
//    @GetMapping("/setting/validate/{settingId}")
//    public String checkSetting(@PathVariable("settingId") Integer settingId, Model model) {
//        // 檢查settingId是否存在
//        boolean exists = settingService.isSettingExists(settingId);
//
//        if (!exists) {
//            // 如果不存在，將錯誤訊息添加到模型並重定向到錯誤頁面
//            model.addAttribute("errormessage", "設定ID " + settingId + " 不存在");
//            return "error"; // error.html頁面
//        }
//
//        // 如果存在，繼續處理邏輯
//        // ...
//        return "game_settings"; // 如果設定存在，返回遊戲設定頁面
//    }
}
