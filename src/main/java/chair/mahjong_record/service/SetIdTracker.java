package chair.mahjong_record.service;

public interface SetIdTracker {
    int getAndIncrementSetId(Integer key);
    void resetSetId(Integer key);
}
