package javaTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache<K, V> {

    // Scheduler for cleanup
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    private class Node {
        K key;
        V value;
        long expiryTime;

        Node prev;
        Node next;

        Node(K key, V value, long ttlMillis) {
            this.key = key;
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    private final int capacity;
    private final Map<K, Node> map;
    private final ReentrantLock lock = new ReentrantLock();

    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new ConcurrentHashMap<>();

        head = new Node(null, null, Long.MAX_VALUE);
        tail = new Node(null, null, Long.MAX_VALUE);

        head.next = tail;
        tail.prev = head;

        scheduler.scheduleAtFixedRate(this::cleanUp, 1, 1, TimeUnit.MINUTES);
    }

    public V get(K key) {
        lock.lock();
        try {
            Node node = map.get(key);
            if (node == null || node.isExpired()) {
                if (node != null) {
                    removeNode(node);
                    map.remove(key);
                }
                return null;
            }
            moveToHead(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value, long ttlMillis) {
        lock.lock();
        try {
            Node node = map.get(key);
            if (node != null) {
                node.value = value;
                node.expiryTime = System.currentTimeMillis() + ttlMillis;
                moveToHead(node);
                return;
            }

            Node newNode = new Node(key, value, ttlMillis);
            map.put(key, newNode);
            addToHead(newNode);

            if (map.size() > capacity) {
                Node lru = removeTail();
                map.remove(lru.key);
            }

        } finally {
            lock.unlock();
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }

        // Cleanup expired entries
    public void cleanUp() {
        System.out.println("Running cleanup...");
        lock.lock();
        try {
            Node current = head.next;
            while (current != tail) {
                if (current.isExpired()) {
                    Node toRemove = current;
                    current = current.next;
                    removeNode(toRemove);
                    map.remove(toRemove.key);
                } else {
                    current = current.next;
                }
            }
        } finally {
            lock.unlock();
        }
    }
}