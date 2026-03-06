package javaTest;

public class LRUCacheTokenBucketRateAlgoTest {
    public static void main(String[] args) {

        // Testing LRU Cache with TTL
        LRUCache<String, String> cache = new LRUCache<>(2);

        cache.put("A", "Apple", 2000); // TTL 2s
        cache.put("B", "Banana", 5000);

        System.out.println(cache.get("A")); // Apple
      //  Thread.sleep(2500);
        System.out.println(cache.get("A")); // null (expired)

        cache.put("C", "Cat", 5000); // Evicts B if over capacity
        System.out.println(cache.get("B")); // Banana or null depending on eviction
        System.out.println(cache.get("C")); // Cat


        // Testing Token Bucket Rate Limiter
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, 2);

        for (int i = 1; i <= 20; i++) {
            if (limiter.allowRequest()) {
                System.out.println("Request " + i + " allowed");
            } else {
                System.out.println("Request " + i + " rejected");
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
