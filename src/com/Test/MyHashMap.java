package com.Test;

public class MyHashMap <K,V>{
    static class Node<K,V>{
        K key;
        V value;
        Node<K,V> next;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private int capacity =16;
    private Node<K,V>[] buckets ;

    public MyHashMap() {
        buckets = new Node[capacity];
    }

    public int getIndex(K key){
        int hashCode = key.hashCode();
        return hashCode & (capacity-1);
    }

    public void put(K key, V value){
        int index = getIndex(key);

        Node<K, V> head = buckets[index];
        while (head != null) {
            if(head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }
        Node<K,V> newNode = new Node<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
    }

    public V get(K key){
        int index = getIndex(key);
        Node<K, V> head = buckets[index];
        while (head != null) {
            if(head.key.equals(key)){
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

     public void remove(K key){
        int index = getIndex(key);
        Node<K, V> head = buckets[index];
        Node<K, V> prev = null;
        while (head != null) {
            if(head.key.equals(key)){
                if(prev == null){
                    buckets[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                return;
            }
            prev = head;
            head = head.next;
        }
    }

     public boolean containsKey(K key){
        int index = getIndex(key);
        Node<K, V> head = buckets[index];
        while (head != null) {
            if(head.key.equals(key)){
                return true;
            }
            head = head.next;
        }
        return false;
    }

     public int size(){
         int size = 0;
         for(Node<K,V> bucket : buckets){
             Node<K,V> head = bucket;
             while (head != null) {
                 size++;
                 head = head.next;
             }
         }
         return size;
     }

     public static void main(String[] args) {
         MyHashMap<String, Integer> map = new MyHashMap<>();
         map.put("one", 1);
         map.put("two", 2);
         map.put("three", 3);

         System.out.println(map.get("two")); // Output: 2
         System.out.println(map.containsKey("three")); // Output: true
         System.out.println(map.size()); // Output: 3

         map.remove("two");
         System.out.println(map.get("two")); // Output: null
         System.out.println(map.size()); // Output: 2
     }

}
