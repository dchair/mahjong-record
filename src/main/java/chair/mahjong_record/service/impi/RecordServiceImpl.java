package chair.mahjong_record.service.impi;

import chair.mahjong_record.dao.PlayerDao;
import chair.mahjong_record.dao.RecordDao;
import chair.mahjong_record.dao.SettingDao;
import chair.mahjong_record.dto.CreateRecordRequest;
import chair.mahjong_record.dto.RecordInfo;
import chair.mahjong_record.dto.RecordSettingDTO;
import chair.mahjong_record.dto.RecordSettingDTOQueryParams;
import chair.mahjong_record.model.GameRecord;
import chair.mahjong_record.model.GameSetting;
import chair.mahjong_record.model.Player;
import chair.mahjong_record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecordServiceImpl  implements RecordService {
    @Autowired
    SettingDao settingDao;
    @Autowired
    RecordDao recordDao;
    @Autowired
    PlayerDao playerDao;

    @Transactional
    @Override
    public void createNDRecord(Integer settingId, RecordInfo recordInfo,Integer nonSaveId) {

        GameRecord gameRecord =new GameRecord();
        //算台數;台數=calculateFan台數+莊家(有無)+(連莊台數*2)
        int calculateFan=recordInfo.getCalculateFan();
        String dealer=recordInfo.getDealerName();
        String winner=recordInfo.getWinnerName();
        String loser= recordInfo.getLoserName();
        //萬一使用者誤填贏家和輸家同一個人
        if(winner.equals(loser)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"贏家和輸家不能是同一個人!");
        }

        //處理莊家台數
        if(dealer.equals(winner) || dealer.equals(loser)){
            int extraFan=recordInfo.getDealerStreak()*2+1;
            calculateFan = calculateFan+extraFan;
        }
        //處理金額
        GameSetting settings =settingDao.getSettingById(settingId);
        int winMoney = settings.getBaseFanPrice()+settings.getPerFanPrice()*calculateFan;
        int loseMoney =-winMoney;

        //將玩家輸贏的資料存進去資料庫
        Player winnerPlayer = playerDao.getPlayerByName(winner);
        Player loserPlayer = playerDao.getPlayerByName(loser);
        playerDao.updatePlayerChips(winnerPlayer.getPlayerId(),winnerPlayer.getChips()+winMoney);
        playerDao.updatePlayerChips(loserPlayer.getPlayerId(),loserPlayer.getChips()+loseMoney);

        gameRecord.setSettingId(settingId);
        gameRecord.setDealerName(dealer);
        gameRecord.setCalculateFan(calculateFan);
        gameRecord.setWinnerName(winner);
        gameRecord.setWinMoney(winMoney);
        gameRecord.setLoserName(loser);
        gameRecord.setLoseMoney(loseMoney);
        gameRecord.setSetId(nonSaveId);

        recordDao.createNDRecord(gameRecord);
    }

    @Override
    public void createDRecord(Integer settingId, CreateRecordRequest createRecordRequest, Integer saveId) {

        //開一個List去存放之後要存的資料表
        List<GameRecord> gameRecordList =new ArrayList<>();
        //從createRecordRequest裡面提取LIST
        for(RecordInfo recordInfo:createRecordRequest.getRecordInfoList()){
            GameRecord gameRecord =new GameRecord();
            //算台數
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
            GameSetting settings =settingDao.getSettingById(settingId);
            int winMoney = settings.getBaseFanPrice()+settings.getPerFanPrice()*calculateFan;
            int loseMoney =-winMoney;

            //將玩家輸贏的資料存進去資料庫
            Player winnerPlayer = playerDao.getPlayerByName(winner);
            Player loserPlayer = playerDao.getPlayerByName(loser);
            playerDao.updatePlayerChips(winnerPlayer.getPlayerId(),winnerPlayer.getChips()+winMoney);
            playerDao.updatePlayerChips(loserPlayer.getPlayerId(),loserPlayer.getChips()+loseMoney);

            gameRecord.setSettingId(settingId);
            gameRecord.setDealerName(recordInfo.getDealerName());
            gameRecord.setCalculateFan(calculateFan);
            gameRecord.setWinnerName(winner);
            gameRecord.setWinMoney(winMoney);
            gameRecord.setLoserName(loser);
            gameRecord.setLoseMoney(loseMoney);
            gameRecord.setSetId(saveId);

                //存放數據
            gameRecordList.add(gameRecord);
        }
        recordDao.createDRecord(gameRecordList);
    }

    @Override
    public List<RecordSettingDTO> getRecordSettingDTOList(RecordSettingDTOQueryParams rSDTOQueryParams) {
        return recordDao.getRecordSettingDTOList(rSDTOQueryParams);
    }

    @Override
    public Integer getTotalRecordCount() {
        return recordDao.getTotalRecordCount();
    }

    @Override
    public List<RecordSettingDTO> getRecordPageRecently(RecordSettingDTOQueryParams recordSettingDTOQueryParams, List<String> playerNames) {
        return recordDao.getRecordPageRecently(recordSettingDTOQueryParams,playerNames);
    }
}
