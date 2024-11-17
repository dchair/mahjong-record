package chair.mahjong_record.dto;

public class GameSettingsRequest {
    private Integer baseFanPrice;
    private Integer perFanPrice;

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


}
