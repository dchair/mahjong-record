package chair.mahjong_record.dao;

import chair.mahjong_record.dto.GameSettingQueryParams;
import chair.mahjong_record.dto.GameSettingRequest;
import chair.mahjong_record.model.GameSetting;

import java.util.List;

public interface SettingDao {
    Integer createSetting(GameSettingRequest gameSettingRequest);
    GameSetting getSettingById(Integer settingId);
    List<GameSetting> getSettings(GameSettingQueryParams gameSettingQueryParams);
    List<GameSetting>getSettingsSelect();
    GameSetting isSettingExists(Integer settingId);
    Integer  getTotalSettingCount();
    void deleteSettingById(Integer settingId);
}
