package chair.mahjong_record.mapper;

import chair.mahjong_record.dto.GameSettingRequest;
import chair.mahjong_record.model.GameSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SettingMapper {
    GameSetting getGameSetting(int settingId);
}
