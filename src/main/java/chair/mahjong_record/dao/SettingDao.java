package chair.mahjong_record.dao;

import chair.mahjong_record.dto.GameSettingsRequest;
import chair.mahjong_record.model.GameSettings;

import java.util.List;

public interface SettingDao {
    Integer createSetting(GameSettingsRequest gameSettingsRequest);
    GameSettings getSettingById(Integer settingId);
    List<GameSettings> getSettings();
}
