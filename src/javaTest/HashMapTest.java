package javaTest;

public class HashMapTest {
    public static void main(String[] args) {
        java.util.HashMap<String, String> map = new java.util.HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        System.out.println("hashCode: " + map.hashCode());
// index = hashCode(key)  & (n - 1);
        // implement hash function
         hashFunction("key1", 16);
        for (String key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }
    }

    private static void hashFunction(String key1, int i) {
        int hash = key1.hashCode();
        System.out.println("Index for key '" + key1 + "': hashCode : " + hash);
        int index = hash & (i - 1);
        System.out.println("Index for key '" + key1 + "': " + index);
    }
}
