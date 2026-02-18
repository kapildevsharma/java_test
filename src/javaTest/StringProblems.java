package javaTest;

import java.util.Arrays;
import java.util.HashMap;

public class StringProblems {

	public static void main(String[] args) {
		String s = "anagram", t = "nagaram";
		boolean flag = checkAnagram(s, t, false);
		String s1 = "rat", t1 = "car";
		System.out.println("String are Anagram with sorting algo without sorting api : " + flag);
		boolean isanagramFlagWithArraysSorting = checkAnagram(s1, t1, true);
		System.out.println("String are Anagram with using arrays sorting api : " + isanagramFlagWithArraysSorting);
		
		String  str1 = "cddgk", str2 = "gcd";
		int minNumChar = minSteps(str1, str2);
		System.out.println("Minimum numbers of characters to be removed to make two strings anagram " + minNumChar);

		String str = "engineers rock";
        String pattern = "gsr";
        System.out.println("Check Word Pattern : "+checkWordPattern(str, pattern));
        
	}

	// function to check two strings anagram
	public static boolean checkAnagram(String s, String t, boolean isUsedSortingAPI) {
		char[] arr1 = s.toCharArray();
		char[] arr2 = t.toCharArray();
		
		// with using sorting api
		if(isUsedSortingAPI) {
			Arrays.sort(arr1);
			Arrays.sort(arr2);
		}else {
			//bubble Sort 
			for (int i = 0; i < arr1.length - 1; i++) {
				for (int j = 0; j < arr1.length - 1 - i; j++) {
					if (arr1[j] > arr1[j + 1]) {
						char temp = arr1[j];
						arr1[j] = arr1[j + 1];
						arr1[j + 1] = temp;
					}
				}
			}
			
			//Insertion Sort Algorithm
			for (int i = 1; i < arr2.length ; i++) {
				char key = arr2[i];
				int j = i-1;
				while(j>=0 && arr2[j]>key) {
					arr2[j+1] = arr2[j];
					j = j-1;
				}
				arr2[j+1] = key;
			}
			
		}
		return Arrays.equals(arr1, arr2);
		
	}

 // Minimum Number of Steps to Make Two Strings Anagram
	public static int minSteps(String s, String t) {
        int count = 0;
        int char_count[] = new int[26];
		// iterate though the first String and update count
        for (int i = 0; i < s.length(); i++) 
            char_count[s.charAt(i) - 'a']++;        
        // iterate through the second string update char_count.
        // if character is not found in char_count then increase count
        for (int i = 0; i < t.length(); i++) {
            char_count[t.charAt(i) - 'a']--;
        }
		// traverse count arrays to find number of characters to be removed
        for(int i = 0; i < 26; ++i) {
          if(char_count[i] != 0) {
            count+= Math.abs(char_count[i]);
          }
        }        
        return count / 2;
    }
	
	
	public static boolean checkWordPattern(String s, String pattern) {
		String [] words = s.split(" ");
        int len = pattern.length();
		if (words.length != len) {
			return false;	
		}
		HashMap <Character, String> patternToWord = new HashMap<Character, String>();
		HashMap <String, Character> wordToPattern = new HashMap<String, Character>();
		
		 for (int i = 0; i < pattern.length(); i++) {
            char currentPatternChar = pattern.charAt(i);
            String currentWord = words[i];
            // Check the pattern-to-word mapping
            if (patternToWord.containsKey(currentPatternChar)) {
                // If there is a mapping, check if it matches the current word
                if (!patternToWord.get(currentPatternChar).equals(currentWord)) {
                    return false;
                }
            } else {
                // If no mapping exists, create the mapping
                patternToWord.put(currentPatternChar, currentWord);
            }
            
            // Check the word-to-pattern mapping
            if (wordToPattern.containsKey(currentWord)) {
                // If there is a mapping, check if it matches the current pattern character
                if (wordToPattern.get(currentWord) != currentPatternChar) {
                    return false;
                }
            } else {
                // If no mapping exists, create the mapping
                wordToPattern.put(currentWord, currentPatternChar);
            }
        }

		 // If we traverse the entire pattern and s without finding inconsistencies, return true
	        return true;
	}    
	        
	        
					
			
}
