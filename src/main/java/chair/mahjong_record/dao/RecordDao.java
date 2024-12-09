package chair.mahjong_record.dao;

import chair.mahjong_record.dto.RecordSettingDTO;
import chair.mahjong_record.dto.RecordSettingDTOQueryParams;
import chair.mahjong_record.model.GameRecord;

import java.util.List;

public interface RecordDao {
    void createNDRecord(GameRecord gameRecord);
    void createDRecord(List<GameRecord> gameRecordList);
    List<RecordSettingDTO> getRecordSettingDTOList(RecordSettingDTOQueryParams rSDTOQueryParams);
    Integer getTotalRecordCount();
    List<RecordSettingDTO> getRecordPageRecently(RecordSettingDTOQueryParams recordSettingDTOQueryParams,List<String> playerNames);
}
