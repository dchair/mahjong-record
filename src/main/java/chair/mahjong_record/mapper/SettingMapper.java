package chair.mahjong_record.mapper;
import chair.mahjong_record.model.GameSetting;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SettingMapper {
    GameSetting getSettingById(int settingId);
}
