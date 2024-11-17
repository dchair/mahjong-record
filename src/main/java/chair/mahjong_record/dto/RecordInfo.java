package chair.mahjong_record.dto;

public class RecordInfo {

    private Integer settingId;
    private String dealerName;
    private Integer dealerStreak;
    private Integer calculateFan;
    private String winnerName;
    private String loserName;

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public Integer getDealerStreak() {
        return dealerStreak;
    }

    public void setDealerStreak(Integer dealerStreak) {
        this.dealerStreak = dealerStreak;
    }

    public Integer getCalculateFan() {
        return calculateFan;
    }

    public void setCalculateFan(Integer calculateFan) {
        this.calculateFan = calculateFan;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }


    public String getLoserName() {
        return loserName;
    }

    public void setLoserName(String loserName) {
        this.loserName = loserName;
    }


}
