package chair.mahjong_record.service;

import chair.mahjong_record.dto.GameSettingsRequest;
import chair.mahjong_record.model.GameSettings;

import java.util.List;

public interface SettingService {
    Integer createSetting(GameSettingsRequest gameSettingsRequest);
    GameSettings getSettingById(Integer settingId);
    List<GameSettings> getSettings();
    Boolean isSettingExists(Integer settingId);
}
