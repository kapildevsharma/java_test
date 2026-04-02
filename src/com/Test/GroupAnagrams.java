package com.Test;

import java.util.*;

public class GroupAnagrams {
    
    public static void main(String[] args) {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(groupAnagrams(input));
    }

    private static List<List<String>> groupAnagrams(String[] input) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : input) {
            // soring the string to get the key for anagram groups
           /* char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);*/
            // Counting Characters
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            String key = Arrays.toString(count);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        System.out.println(map);
        System.out.println(map.values().stream().max(Comparator.comparing(List::size)));

        return new ArrayList<>(map.values());
    }

}
