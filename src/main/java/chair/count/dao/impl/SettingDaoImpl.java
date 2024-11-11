package chair.count.dao.impl;

import chair.count.dao.SettingDao;
import chair.count.dto.GameSettingsRequest;
import org.springframework.stereotype.Component;

@Component
public class SettingDaoImpl implements SettingDao {
    @Override
    public Integer createSetting(GameSettingsRequest gameSettingsRequest) {
        return 0;
    }
}
