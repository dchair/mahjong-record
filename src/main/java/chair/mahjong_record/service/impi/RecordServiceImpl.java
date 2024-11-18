package chair.mahjong_record.service.impi;

import chair.mahjong_record.dao.RecordDao;
import chair.mahjong_record.dao.SettingDao;
import chair.mahjong_record.dto.RecordInfo;
import chair.mahjong_record.model.GameRecord;
import chair.mahjong_record.model.GameSettings;
import chair.mahjong_record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecordServiceImpl  implements RecordService {
    @Autowired
    SettingDao settingDao;
    @Autowired
    RecordDao recordDao;

    @Override
    public Integer createNDRecord(Integer settingId, RecordInfo recordInfo,Integer nonSaveId) {

        GameRecord gameRecord =new GameRecord();
        //算台數;台數=calculateFan台數+莊家(有無)+(連莊台數*2)
        int calculateFan=recordInfo.getCalculateFan();
        String dealer=recordInfo.getDealerName();
        String winner=recordInfo.getWinnerName();
        String loser= recordInfo.getLoserName();
        //處理莊家台數
        if(dealer.equals(winner) || dealer.equals(loser)){
            int extraFan=recordInfo.getDealerStreak()*2+1;
            calculateFan = calculateFan+extraFan;
        }
        //處理金額
        GameSettings settings =settingDao.getSettingById(settingId);
        int winMoney = settings.getBaseFanPrice()+settings.getPerFanPrice()*calculateFan;
        int loseMoney =-winMoney;

        gameRecord.setSettingId(settingId);
        gameRecord.setDealerName(dealer);
        gameRecord.setCalculateFan(calculateFan);
        gameRecord.setWinnerName(winner);
        gameRecord.setWinMoney(winMoney);
        gameRecord.setLoserName(loser);
        gameRecord.setLoseMoney(loseMoney);
        gameRecord.setSetId(nonSaveId);

        return recordDao.createNDRecord(gameRecord);
    }

}
