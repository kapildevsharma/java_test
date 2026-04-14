package javaTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InterviewList {

	public static void main(String[] args) {
		InterviewList test = new InterviewList();

		// Move zeros to beginning [0,5,1,0,7,0,2,0]
        int[] arr = new int[]{0,5,1,0,7,0,2,0};
        moveZeroToBeginning(arr);
        // Longest consecutive sequence [100,4,200,1,3,2]
        longestConsecutive();
        // Q:- String "aabbccdeff" First non repeating character , Character frequency, Second highest frequency
        firstNonRepeatingFrequencyWithSecondhighest();

	}

    public static void firstNonRepeatingFrequencyWithSecondhighest(){
        String st = "aabbccdeff";
        Map<Character, Integer> freq = new LinkedHashMap<>();

        // Step 1: Frequency count
        for (char c : st.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Step 2: First non-repeating
        Character firstNonRepeating = null;
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            if (e.getValue() == 1) {
                firstNonRepeating = e.getKey();
                break;
            }
        }

        // Step 3: Second highest frequency
        int first = 0, second = 0;
        char secondChar = 0;

        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            int val = e.getValue();

            if (val > first) {
                second = first;
                first = val;
            } else if (val > second && val < first) {
                second = val;
                secondChar = e.getKey();
            }
        }

        // Output
        System.out.println("Frequency: " + freq);
        System.out.println("First Non-Repeating: " + firstNonRepeating);
        System.out.println("Second Highest Freq Char: " + secondChar);

        // java 8
        char result = st.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(c->c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .sorted((a,b) -> Long.compare(b.getValue(), a.getValue()))
        //      .sorted(Comparator.comparingInt((Map.Entry<Character, Integer> e) -> e.getValue()).reversed())
                .skip(1) .map(Map.Entry::getKey) .findFirst().get();

        System.out.println("java 8 stream, Second highest frequency character :" + secondChar);

        result = st.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(c->c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue()==1)
                .sorted(Comparator.comparingInt(e -> e.getValue().intValue()))
                .map(Map.Entry::getKey).findFirst().get();

        System.out.println("java 8 stream, First Non-Repeating character :" + secondChar);




    }

    public static void longestConsecutive(){

        System.out.println("Longest consecutive sequence [100,4,200,1,3,2] ");

        int[] num = new int[]{100,4,200,1,3,2};

        // Sorting Approach --> Time: O(n log n)
        Arrays.sort(num);
        int longest  = 1 , current = 1;
        for(int i=1; i< num.length; i++){
            if(num[i] == num[i-1]) continue; // skip duplicate

            if(num[i] == num[i-1] + 1)
                current++;
            else {
                longest = Math.max(current, longest);
                current = 0;
            }
        }
        System.out.println(" Sorting Approach : "+Math.max(current, longest));

        // HashSet (Optimal) --> Time Complexity: O(n)
        num = new int[]{100,4,200,1,3,2};
        Set<Integer> set = new HashSet<Integer>();
        for(int n : num) set.add(n);
        longest = 0;
        for(int n: set){
            // start of sequence
            if(!set.contains(n-1)){
                int currentNum = n;
                int count =1;
                while(set.contains(currentNum+1)){
                    currentNum++;
                    count++;
                }
                longest = Math.max(count, longest);
            }
        }
        System.out.println(" HashSet (Optimal) : "+longest);

    }

	public static void moveZeroToBeginning(int[] arr) {
        int n = arr.length;
        int insertPos = n - 1;
        int [] result = new int[n];
        // Two-Pointer --> Time Complexity: O(n)
        // Move non-zero elements to the end
        for(int i = n-1 ; i>=0 ; i--){
            if(arr[i] != 0){
                result[insertPos] = arr[i];
                insertPos--;
            }
        }
        // Fill remaining with zeros
        for(int i = 0 ; i< insertPos ; i++){
            result[i] = 0;
        }

        System.out.println("moveZeroToBeginning: " + Arrays.toString(result));

        // java 8 Time Complexity: O(n log n)
        result = Arrays.stream(arr).boxed()
              //  .sorted((a, b) -> ( a==0? -1: b==0?1: 0))
                .sorted(Comparator.comparingInt( a -> a==0?0:1))
                .mapToInt(Integer::intValue).toArray();
        System.out.println("java 8, moveZeroToBeginning: " + Arrays.toString(result));

        // java 8, IntStream Time Complexity: O(n)
        result = IntStream.concat(Arrays.stream(arr).filter(i -> i==0),
                Arrays.stream(arr).filter(i -> i!=0)).toArray();
        System.out.println("java 8,IntStream contact moveZeroToBeginning: " + Arrays.toString(result));

	}

}
