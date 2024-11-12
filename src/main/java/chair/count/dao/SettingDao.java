package chair.count.dao;

import chair.count.dto.GameSettingsRequest;
import chair.count.model.GameSettings;

import java.util.List;

public interface SettingDao {
    Integer createSetting(GameSettingsRequest gameSettingsRequest);
    GameSettings getSettingById(Integer settingId);
}
