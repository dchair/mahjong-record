package chair.mahjong_record.service.impi;

import chair.mahjong_record.dao.SettingDao;
import chair.mahjong_record.dto.GameSettingsRequest;
import chair.mahjong_record.model.GameSettings;
import chair.mahjong_record.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingDao settingDao;

    @Override
    public Integer createSetting(GameSettingsRequest gameSettingsRequest) {
        return settingDao.createSetting(gameSettingsRequest);
    }

    @Override
    public GameSettings getSettingById(Integer settingId) {
        return settingDao.getSettingById(settingId);
    }

    @Override
    public List<GameSettings> getSettings() {
        return settingDao.getSettings();
    }

    @Override
    public Boolean isSettingExists(Integer settingId) {
        return settingDao.isSettingExists(settingId) !=null;
    }
}
