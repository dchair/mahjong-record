package chair.mahjong_record.service.impi;

import chair.mahjong_record.dto.RecordInfo;
import chair.mahjong_record.service.RecordService;
import org.springframework.stereotype.Component;

@Component
public class RecordServiceImpl  implements RecordService {

    @Override
    public Integer createNDRecord(Integer settingId, RecordInfo recordInfo) {
        return "0";
    }
}
