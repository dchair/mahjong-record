package chair.mahjong_record.dto;

import java.util.Date;

public class RecordSettingDTO {
    private Integer recordId;
    private Integer settingId;
    private String dealerName;
    private Integer calculateFan;
    private String winnerName;
    private Integer winMoney;
    private String loserName;
    private Integer loseMoney;
    private Integer setId;
    private Date createdDate;
    private Date lastModifiedDate;
    private Integer baseFanPrice;
    private Integer perFanPrice;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

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

    public Integer getWinMoney() {
        return winMoney;
    }

    public void setWinMoney(Integer winMoney) {
        this.winMoney = winMoney;
    }

    public String getLoserName() {
        return loserName;
    }

    public void setLoserName(String loserName) {
        this.loserName = loserName;
    }

    public Integer getLoseMoney() {
        return loseMoney;
    }

    public void setLoseMoney(Integer loseMoney) {
        this.loseMoney = loseMoney;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public Integer getBaseFanPrice() {
        return baseFanPrice;
    }

    public void setBaseFanPrice(Integer baseFanPrice) {
        this.baseFanPrice = baseFanPrice;
    }

    public Integer getPerFanPrice() {
        return perFanPrice;
    }

    public void setPerFanPrice(Integer perFanPrice) {
        this.perFanPrice = perFanPrice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
