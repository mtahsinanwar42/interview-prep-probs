package org.example.otherdatastructure;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Thread-safe TTL cache with optional expiration for each key.
 * <p>
 * Features:
 * - Thread-safe using ConcurrentHashMap.
 * - Global TTL applied to all keys (milliseconds).
 * - Lazy expiration on get/put.
 * - Optional per-entry TTL can be added later if needed.
 */
public class Cache<K, V> {

    private final ConcurrentHashMap<K, V> cache;
    private final ConcurrentHashMap<K, Long> expiryMap;

    public Cache() {
        this.cache = new ConcurrentHashMap<>();
        this.expiryMap = new ConcurrentHashMap<>();
    }

    /**
     * Puts a key-value pair into the cache and sets its expiration.
     *
     * @param key   non-null key
     * @param value value associated with key
     */
    public void put(K key, V value, long ttlMillis) {
        Objects.requireNonNull(key, "key must not be null");
        cache.put(key, value);

        if (ttlMillis > 0L) {
            expiryMap.put(key, System.currentTimeMillis() + ttlMillis);
        } else {
            expiryMap.remove(key);
        }
    }

    public V get(K key) {
        Objects.requireNonNull(key, "key must not be null");

        if (isExpired(key)) {
            cache.remove(key);
            expiryMap.remove(key);
            return null;
        }

        return cache.get(key);
    }

    public void remove(K key) {
        cache.remove(key);
        expiryMap.remove(key);
    }

    private boolean isExpired(K key) {
        Long ttlMillis = expiryMap.get(key);

        return ttlMillis != null && System.currentTimeMillis() > ttlMillis;
    }

    public int size() {
        return cache.size();
    }

    public void clear() {
        cache.clear();
        expiryMap.clear();
    }

    // needed to avoid memory leak
    public void startCleanup(long intervalMillis) {
        ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();
        cleaner.scheduleAtFixedRate(() -> {
            long now = System.currentTimeMillis();
            for (K key : expiryMap.keySet()) {
                Long expiry = expiryMap.get(key);
                if (expiry != null && now > expiry) {
                    cache.remove(key);
                    expiryMap.remove(key);
                }
            }
        }, intervalMillis, intervalMillis, TimeUnit.MILLISECONDS);
    }
}
