package javaTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FindDuplicate {

	public static void main(String[] args) {

		String s1 = "aabbcc"; 
		String s2 = "abc";
		Set<Character> set1 = s1.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
		Set<Character> set2 = s2.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
		System.out.println(set1.equals(set2));
		
		System.out.println("printFirstNonRepeatingChar : "+ printFirstNonRepeatingChar("abca"));

		int arr[] = { 1, 2, 3, 1, 3, 6, 6 };
		arr = new int[] { 12, 11, 40, 12, 5, 6, 5, 12, 11 };

		findDuplicates(arr);
		printRepeating(arr);
	}

	private static Character printFirstNonRepeatingChar(String string) {
		char[] chars = string.toCharArray();

		List<Character> discardedChars = new ArrayList<>();

		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];

			if (discardedChars.contains(c))
				continue;

			for (int j = i + 1; j < chars.length; j++) {
				if (c == chars[j]) { // match found
					discardedChars.add(c);
					break;
				} else if (j == chars.length - 1) { // no match found till end
					return c;
				}
			}
		}
		return null;
	}

	static void findDuplicates(int arr[]) {

		Set<Integer> duplicates = new HashSet<Integer>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] == arr[j] && i != j) {
					duplicates.add(arr[i]);
					break;
				}
			}
		}

		System.out.println("Duplicates Element in array is : " + duplicates);

		Set<Integer> seen = new HashSet<Integer>();
		for (int num : arr) {
			if (!seen.add(num)) {
                duplicates.add(num);
				System.out.println("Duplicate element: " + num);
			}
		}
        System.out.println("Duplicates element in array is : " + duplicates);


    }

	//arr = new int[] { 12, 11, 40, 12, 5, 6, 5, 12, 11 };
	static void printRepeating(int[] arr) {
        int size = arr.length;

        int i;
		System.out.println("The repeating elements are count");
		ConcurrentHashMap<Integer, Integer> repeatElementCount = new ConcurrentHashMap<>();
		for (i = 0; i < size; i++) {
			/*if(repeatElementCount.containsKey(arr[i])) {
				System.out.println("Update Value of Elements : " + arr[i]+" "+i);
				repeatElementCount.replace(arr[i], repeatElementCount.get(arr[i])+1);
			}else {
				System.out.println("Add Value of Elements : " + arr[i]+" "+i);
				repeatElementCount.put(arr[i], 1);
			}*/

            repeatElementCount.put(arr[i], repeatElementCount.getOrDefault(arr[i], 0) + 1);

        }
		for(Map.Entry<Integer, Integer> entry : repeatElementCount.entrySet()) {
			if(entry.getValue()>1) {
				System.out.println(entry.getKey() +" :: "+ entry.getValue());
			}else {
				repeatElementCount.remove(entry.getKey());
			}
		}
		
		System.out.println(repeatElementCount.toString());
	}

}
