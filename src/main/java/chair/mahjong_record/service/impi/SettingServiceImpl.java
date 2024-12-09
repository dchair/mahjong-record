package chair.mahjong_record.service.impi;

import chair.mahjong_record.dao.SettingDao;
import chair.mahjong_record.dto.GameSettingQueryParams;
import chair.mahjong_record.dto.GameSettingRequest;
import chair.mahjong_record.model.GameSetting;
import chair.mahjong_record.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingDao settingDao;

    @Override
    public Integer createSetting(GameSettingRequest gameSettingRequest) {
        return settingDao.createSetting(gameSettingRequest);
    }

    @Override
    public GameSetting getSettingById(Integer settingId) {
        return settingDao.getSettingById(settingId);
    }

    @Override
    public List<GameSetting> getSettings(GameSettingQueryParams gameSettingQueryParams) {
        return settingDao.getSettings(gameSettingQueryParams);
    }

    @Override
    public Boolean isSettingExists(Integer settingId) {
        return settingDao.isSettingExists(settingId) !=null;
    }

    @Override
    public Integer getTotalSettingCount() {
        return settingDao.getTotalSettingCount();
    }

    @Override
    public void deleteSettingById(Integer settingId) {
        settingDao.deleteSettingById(settingId);
    }

    @Override
    public List<GameSetting> getSettingsSelect() {
        return settingDao.getSettingsSelect();
    }
}
