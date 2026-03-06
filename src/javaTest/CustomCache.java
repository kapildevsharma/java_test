package javaTest;

import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class CustomCache {

    // Inner Entity class
    private record Entity(String value, long expireAt) {
        private Entity(String value, long expireAt) {
            this.value = value;
            this.expireAt = System.currentTimeMillis() + expireAt;
        }
        boolean isExpired() {
                return System.currentTimeMillis() > expireAt;
            }
    }

    // Thread-safe map
    private final ConcurrentHashMap<String, Entity> cacheMap = new ConcurrentHashMap<>();

    // Scheduler for cleanup
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    // Constructor
    public CustomCache() {
        // runs every 1 minute
        scheduler.scheduleAtFixedRate(this::cleanUp, 1, 1, TimeUnit.MINUTES);
    }
    private final ReentrantLock lock = new ReentrantLock();

    // Put value with TTL (milliseconds)
    public void put(String key, String value, long ttlMillis) {
        lock.lock();
        try {
            long expireAt = System.currentTimeMillis() + ttlMillis;
            cacheMap.put(key, new Entity(value, expireAt));
        }
        finally {
            lock.unlock();
        }
    }

    // Get value
    public String get(String key) {
        lock.lock();
        try{
            Entity entity = cacheMap.get(key);
            if (entity == null) {
                return null;
            }

            if (entity.isExpired()) {
                cacheMap.remove(key);
                return null;
            }

            return entity.value;
        }
        finally {
            lock.unlock();
        }
    }

    // Remove key
    public void remove(String key) {
        lock.lock();
        try{
            cacheMap.remove(key);
        }
        finally {
            lock.unlock();
        }
    }

    // Cache size
    public int size() {
        return cacheMap.size();
    }

    // Cleanup expired entries
    private void cleanUp() {
        cacheMap.entrySet().removeIf(entry -> entry.getValue().isExpired());
        System.out.println("Cleanup executed at " + new Date());
    }

    // Shutdown scheduler (important for graceful shutdown)
    public void shutdown() {
        scheduler.shutdown();
    }

    // ==========================
    // Main method for testing
    // ==========================
    public static void main(String[] args) throws InterruptedException {
        CustomCache cache = new CustomCache();

        cache.put("key1", "value1", 3000); // 3 seconds
        cache.put("key2", "value2", 5000); // 5 seconds

        System.out.println(cache.get("key1")); // value1
        Thread.sleep(4000);

        System.out.println(cache.get("key1")); // null (expired)
        System.out.println(cache.get("key2")); // value2

        cache.shutdown();
    }
}
