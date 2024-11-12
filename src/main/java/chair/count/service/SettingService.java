package chair.count.service;

import chair.count.dto.GameSettingsRequest;
import chair.count.model.GameSettings;

import java.util.List;

public interface SettingService {
    Integer createSetting(GameSettingsRequest gameSettingsRequest);
    GameSettings getSettingById(Integer settingId);
}
