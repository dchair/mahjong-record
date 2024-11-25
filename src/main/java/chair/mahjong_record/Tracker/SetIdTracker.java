package chair.mahjong_record.Tracker;

public interface SetIdTracker {
    int getAndIncrementSetId(Integer key);
    void resetSetId(Integer key);
    int getCurrentSetId(Integer key);
}
