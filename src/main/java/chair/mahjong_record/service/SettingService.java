package chair.mahjong_record.service;

import chair.mahjong_record.dto.GameSettingQueryParams;
import chair.mahjong_record.dto.GameSettingRequest;
import chair.mahjong_record.model.GameSetting;

import java.util.List;

public interface SettingService {
    Integer createSetting(GameSettingRequest gameSettingRequest);
    GameSetting getSettingById(Integer settingId);
    List<GameSetting> getSettings(GameSettingQueryParams gameSettingQueryParams);
    Boolean isSettingExists(Integer settingId);
    Integer  getTotalSettingCount();
    void deleteSettingById(Integer settingId);
}
