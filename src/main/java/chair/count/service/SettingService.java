package chair.count.service;

import chair.count.dto.GameSettingsRequest;

public interface SettingService {
    Integer createSetting(GameSettingsRequest gameSettingsRequest);
}
