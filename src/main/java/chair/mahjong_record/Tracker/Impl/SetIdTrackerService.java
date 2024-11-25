package chair.mahjong_record.Tracker.Impl;

import chair.mahjong_record.Tracker.SetIdTracker;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SetIdTrackerService implements SetIdTracker {

    private final ConcurrentHashMap<Integer, AtomicInteger> setIdTracker = new ConcurrentHashMap<>();
    @Override
    public int getAndIncrementSetId(Integer key) {
        setIdTracker.putIfAbsent(key, new AtomicInteger(1));
        return setIdTracker.get(key).getAndIncrement();
    }

    @Override
    public void resetSetId(Integer key) {
        setIdTracker.put(key, new AtomicInteger(1));
    }

    @Override
    public int getCurrentSetId(Integer key) {
        return setIdTracker.getOrDefault(key, new AtomicInteger(1)).get();
    }
}
