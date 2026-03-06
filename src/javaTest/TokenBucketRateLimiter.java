package javaTest;

import java.util.concurrent.atomic.AtomicLong;

public class TokenBucketRateLimiter {
    private final long capacity;
    private final long refillRate; // tokens per second
    private AtomicLong tokens;
    private long lastRefillTimestamp;

    public TokenBucketRateLimiter(long capacity, long refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = new AtomicLong(capacity);
        this.lastRefillTimestamp = System.currentTimeMillis();
    }
    public synchronized boolean allowRequest() {
        refill();
        if (tokens.get() > 0) {
            tokens.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRefillTimestamp;
        System.out.println("elapsedTime : "+ elapsedTime);
        long tokensToAdd = (elapsedTime * refillRate) / 1000; // Convert ms to seconds
        System.out.println("tokensToAdd : "+ tokensToAdd + " newly token : "+ (Math.min(capacity, tokens.get() + tokensToAdd)));
        if (tokensToAdd > 0) {
            System.out.println("tokensToAdd : "+ tokensToAdd + " current token : "+ tokens.get());

            tokens.getAndUpdate(current -> Math.min(capacity, current + tokensToAdd));
            lastRefillTimestamp = now;
        }
    }
}
