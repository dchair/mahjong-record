package chair.mahjong_record.dao;

import chair.mahjong_record.model.GameRecord;

import java.util.List;

public interface RecordDao {
    void createNDRecord(GameRecord gameRecord);
    void createDRecord(List<GameRecord> gameRecordList);
}
