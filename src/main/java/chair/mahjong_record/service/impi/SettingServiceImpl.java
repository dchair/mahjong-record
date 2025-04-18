package chair.mahjong_record.service.impi;

import chair.mahjong_record.dao.SettingDao;
import chair.mahjong_record.dto.GameSettingQueryParams;
import chair.mahjong_record.dto.GameSettingRequest;
import chair.mahjong_record.mapper.SettingMapper;
import chair.mahjong_record.model.GameSetting;
import chair.mahjong_record.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SettingServiceImpl implements SettingService {

    private final SettingMapper settingMapper;
    private  final SettingDao settingDao;

    @Autowired
    public SettingServiceImpl(SettingMapper settingMapper,SettingDao settingDao) {
        this.settingMapper = settingMapper;
        this.settingDao = settingDao;
    }

    //改用mapper
    @Override
    public Integer createSetting(GameSettingRequest gameSettingRequest) {
        settingMapper.createSetting(gameSettingRequest);
        return gameSettingRequest.getSettingId();
    }

//    @Override
//    public Integer createSetting(GameSettingRequest gameSettingRequest) {
//        return settingDao.createSetting(gameSettingRequest);
//    }
    //改用mapper
    @Override
    public GameSetting getSettingById(Integer settingId) {
        return settingMapper.getSettingById(settingId);
    }

//    @Override
//    public GameSetting getSettingById(Integer settingId) {
//        return settingDao.getSettingById(settingId);
//
//    }

    @Override
    public List<GameSetting> getSettings(GameSettingQueryParams gameSettingQueryParams) {
        //安全驗證
        List<String> validOrderBys = List.of("setting_id","base_fan_price","per_fan_price"
                ,"created_date","last_modified_date");
        String orderBy =gameSettingQueryParams.getOrderBy();
        if(orderBy==null || !validOrderBys.contains(orderBy)){
            throw new IllegalArgumentException("Invalid orderBy");
        }
        String sort = gameSettingQueryParams.getSort().toLowerCase();
        Map<String,Object> map = new HashMap<>();
        map.put("orderBy",gameSettingQueryParams.getOrderBy());
        map.put("sort",sort);
        map.put("limit",gameSettingQueryParams.getLimit());
        map.put("offset",gameSettingQueryParams.getOffset());

        return settingMapper.getSettings(map);
    }
//    @Override
//    public List<GameSetting> getSettings(GameSettingQueryParams gameSettingQueryParams) {
//        return settingDao.getSettings(gameSettingQueryParams);
//    }

    @Override
    public Boolean isSettingExists(Integer settingId) {
        return settingDao.isSettingExists(settingId) !=null;
    }

//    @Override
//    public Integer getTotalSettingCount() {
//        return settingDao.getTotalSettingCount();
//    }
    //改用mapper去抓參數total
    @Override
    public Integer getTotalSettingCount() {
        return settingMapper.countSettings();
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
