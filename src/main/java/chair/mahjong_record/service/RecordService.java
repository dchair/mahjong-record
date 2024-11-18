package chair.mahjong_record.service;

import chair.mahjong_record.dto.RecordInfo;

public interface RecordService {
    Integer createNDRecord(Integer settingId, RecordInfo recordInfo,Integer nonSaveId);
}
