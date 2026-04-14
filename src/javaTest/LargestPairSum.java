package javaTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LargestPairSum {

    // Find the maximum sum of a contiguous subarray.
    public static int findMaxSumSubarray(int[] arr) {
        int maxSoFar = arr[0]; // keeps track of the maximum sum found across all subarrays.
        int maxEndingHere = arr[0];  //keeps track of the best subarray sum ending at index i.

        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    // Find the maximum sum of a contiguous subarray.
	public static int findMaxSum(List<Integer> list) {
        System.out.println("Finding max sum in a contiguous subarray in list "+ list);

		int j = 0;
		int max = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			int sum = list.get(j) + list.get(i);
			if (sum > max) {
				max = sum;
				if (list.get(j) < list.get(i)) {
					j = i;
				}
			}
		}
		return max;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // Find the maximum sum of a contiguous subarray. Kadane’s Algorithm
    public static void findMaxSumWithStartEndIndex (int[] arr) {
        System.out.println("Finding max sum with start and end index in arr[] "+ Arrays.toString(arr));
        int maxSum = arr[0], currentSum = arr[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < arr.length; i++) {
            if (currentSum < 0) {
                currentSum = arr[i];
                tempStart = i;
            } else {
                currentSum += arr[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }
        System.out.println("Maximum contiguous sum is " + maxSum + " in arr[] "+ Arrays.toString(arr));
        System.out.println("Starting index " + start);
        System.out.println("Ending index " + end);
    }
    public static void findSubarrays(int[] arr, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int prefixSum = 0;

        // Important: To handle subarray starting from index 0
        map.put(0, new ArrayList<>(Arrays.asList(-1)));

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            // Check if there is a prefixSum - target
            if (map.containsKey(prefixSum - target)) {
                List<Integer> startIndexes = map.get(prefixSum - target);
                for (int start : startIndexes) {
                    System.out.println("Subarray found from index "
                            + (start + 1) + " to " + i);
                    System.out.println("Subarray with sum " + target + " found: " +
                            Arrays.toString(Arrays.copyOfRange(arr, start+1, i+1)));
                }
            }

            // Store current prefixSum
            map.putIfAbsent(prefixSum, new ArrayList<>());
            map.get(prefixSum).add(i);
        }
    }

    // Finds ONLY FIRST subarray with sum = target
    public static void findFirstSubarray(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int prefixSum = 0;

        map.put(0, -1); // handle subarray from index 0

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            if (map.containsKey(prefixSum - target)) {
                int start = map.get(prefixSum - target) + 1;

                System.out.println("Subarray found from "
                        + start + " to " + i);

                System.out.println(Arrays.toString(
                        Arrays.copyOfRange(arr, start, i + 1)
                ));
                return;
            }

            // store only first occurrence
            map.putIfAbsent(prefixSum, i);
        }

        System.out.println("No subarray found");
    }


    // O(n) time, O(n) space.
    public static void findSubarrayWithSum(int[] arr, int sum) {
        System.out.println("Finding subarray with sum " + sum + " in arr[] "+ Arrays.toString(arr));
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int currSum  = 0;
        prefixSumMap.put(0, -1); // Base case: sum=0 before array starts

        for (int index = 0; index < arr.length; index++) {
            currSum  += arr[index];

            if (prefixSumMap.containsKey(currSum - sum)) {
                int start = prefixSumMap.get(currSum - sum) + 1;
                System.out.println("findSubarrayWithSum, Subarray " + ( prefixSumMap.get(currSum - sum) + 1 ) + " to " + index);
                System.out.println("findSubarrayWithSum, Subarray with sum " + sum + " found: " +
                        Arrays.toString(Arrays.copyOfRange(arr, start, index + 1)));
                return;
            }
            // Store the first occurrence of this sum
            prefixSumMap.putIfAbsent(currSum , index);
        }

        System.out.println("findSubarrayWithSum, No subarray with sum " + sum + " found.");
    }

    public static void findSubarraySlidingWindow(int[] arr, int target) {
        int start = 0, currSum = 0;
        boolean found = false;

        for (int end = 0; end < arr.length; end++) {
            currSum += arr[end];

            while (currSum > target && start < end) {
                currSum -= arr[start++];
            }

            if (currSum == target) {
                System.out.println("findSubarraySlidingWindow, Subarray " + start + " to " + end);
                found = true;
                System.out.println("findSubarraySlidingWindow, Subarray with sum " + target + " found: " +
                        Arrays.toString(Arrays.copyOfRange(arr, start, end + 1)));
                return;
            }
        }

        if (!found) {
            System.out.println("No subarray found");
        }
    }
	public static void main(String[] args) {

		int[] arr = new int[] {5, 9, 7, 11};
		Integer[] arr1 = Arrays.stream(arr).boxed().toArray(Integer[]:: new);
		System.out.println(Arrays.toString(arr1));
	 	Integer[] newArray = new Integer[arr.length];
		int i = 0;
		for (int value : arr) {
		    newArray[i++] = Integer.valueOf(value);
		}
        System.out.println(Arrays.toString(newArray));

		List<Integer> list = Arrays.asList(arr1);
		list = Arrays.stream(arr).boxed().collect(Collectors.toList());
		list = Arrays.stream(arr1).collect(Collectors.toList());
		list = Arrays.asList(5, 9, 7, 11);
		
        System.out.println(findMaxSum(list));

        System.out.println(findMaxSumSubarray(arr));

        System.out.println("----Finding max sum with start and end index----");
        findMaxSumWithStartEndIndex(new int[] {-2, -3, 4, -1, -2, 1, 5, -3});

        arr = new int[]{3, -7, 4, 1, 3, 3, 1, -4};
        int sum = 7;
        findSubarrayWithSum(arr, sum);

        arr = new int[] {1, -4, 20, 3, 10, 5};
        int target = 33;
        findSubarrayWithSum(arr, target);

        arr = new int[] {1, 4, 20, 3, 10, 5};
        target = 13;
        findSubarraySlidingWindow(arr, target);
        findSubarrays(arr, target);
        testZonedDateTime();

        getHashMapMethod();

	}
	
	public static void testZonedDateTime() {
	      // Get the current date and time
	      ZonedDateTime date1 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
	      System.out.println("date1: " + date1);
			
	      ZoneId id = ZoneId.of("Europe/Paris");
	      System.out.println("ZoneId: " + id);
			
	      ZoneId currentZone = ZoneId.systemDefault();
	      System.out.println("CurrentZone: " + currentZone);
	   }
	
	public static void getHashMapMethod() {
		 Map<String, String> map = new HashMap<>();
	        map.put("Name", "Kapil");
	        map.put("Address", "Kolkata");
	  
	        // Print the map
	        System.out.println("Map: " + map);
	       // remap the values using compute() method
	        map.compute("Name", (key, val)
	                                -> val.concat(" Sharma"));
	        map.compute("Address", (key, val)
	                                   -> val.concat(" West-Bengal"));
	        // print new mapping
	        System.out.println("New Map: " + map);
	}
	
}