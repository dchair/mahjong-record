package chair.count.dao;

import chair.count.dto.GameSettingsRequest;

public interface SettingDao {
    public Integer createSetting(GameSettingsRequest gameSettingsRequest);
}
