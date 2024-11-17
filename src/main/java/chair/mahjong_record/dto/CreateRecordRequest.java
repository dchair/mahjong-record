package chair.mahjong_record.dto;

import java.util.List;

public class CreateRecordRequest {

    private List<RecordInfo> recordInfoList;

    public List<RecordInfo> getRecordInfoList() {
        return recordInfoList;
    }

    public void setRecordInfoList(List<RecordInfo> recordInfoList) {
        this.recordInfoList = recordInfoList;
    }
}
