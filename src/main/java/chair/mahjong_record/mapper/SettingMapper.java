package chair.mahjong_record.mapper;
import chair.mahjong_record.dto.GameSettingQueryParams;
import chair.mahjong_record.dto.GameSettingRequest;
import chair.mahjong_record.model.GameSetting;

import java.util.List;
import java.util.Map;


public interface SettingMapper {
    GameSetting getSettingById(int settingId);
    int createSetting(GameSettingRequest gameSettingRequest);
    List<GameSetting> getSettings(Map<String, Object> map);
    int countSettings();
}
