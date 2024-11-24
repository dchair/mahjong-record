package chair.mahjong_record.service;

import chair.mahjong_record.dto.CreateRecordRequest;
import chair.mahjong_record.dto.RecordInfo;

import java.util.List;

public interface RecordService {
    void createNDRecord(Integer settingId, RecordInfo recordInfo,Integer nonSaveId);
    void createDRecord(Integer settingId, CreateRecordRequest createRecordRequest, Integer saveId);
}
