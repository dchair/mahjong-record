package chair.mahjong_record.dao;

import chair.mahjong_record.model.GameRecord;

public interface RecordDao {
    Integer createNDRecord(GameRecord gameRecord);
}
