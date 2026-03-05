package javaTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

	public static void main(String[] args) {
		String st = "kapil dev sharma";

		Map<String, Long> noOfCharWithTotalCount = Arrays.stream(st.split(""))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("Each Character with count: " + noOfCharWithTotalCount);

		int[] arr = { 5, 2, 8, 1, 3 };
		// Arrays.sort(arr);
		// sorting with bubble sort only for small dataSets
		int[] bubbleSortedArr = sortingArrayWithBubbleSort(arr);
		System.out.println("Sorting array with bubble sort : " +Arrays.toString(bubbleSortedArr));
		
		// sorting with selection sort only for small dataSets
		arr = new int[] { 5, 2, 8, 1, 3 };
		int[] selectionSortedArr = sortingArrayWithSelectionSort(arr);
		System.out.println("Sorting array with Selection sort : " +Arrays.toString(selectionSortedArr));
		
		// sorting with insertion sort only for small dataSets
		arr = new int[] { 5, 2, 8, 1, 3 };
		int[] insertionSortedArr = sortingArrayWithInsertionSort(arr);
		System.out.println("Sorting array with Insertion sort : " +Arrays.toString(insertionSortedArr));
		
		
		
		System.out.println(arr[arr.length - 2]);

		Optional<Integer> num = Arrays.stream(arr).boxed().sorted().skip(arr.length - 2).findFirst();
		System.out.println("oder and skip reverse:" + (num.isPresent() ? num.get() : "No element"));
		num = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst();
		System.out.println("reverse oder and skip :" + (num.isPresent() ? num.get() : "No element"));

		arr = new int[] { 1, 1, 2 };
		arr = Arrays.stream(arr).boxed().distinct().mapToInt(Integer::intValue).toArray();
		arr = Arrays.stream(arr).distinct().toArray();

		System.out.println("unique element :" + Arrays.toString(arr));
		int[] array = { 1, 2, 3 }; // Array to compare against

		findMIssingNumbers(array);

		arr = new int[] { 1, 1, 2 };
		removeElement(arr, 0); // remove duplicate element
		System.out.println("remove duplicate element :" + Arrays.toString(arr));

		arr = new int[] { 0, 1, 2, 2, 3, 0, 4, 2 };
		removeElement(arr, 2); // remove val from arr
		System.out.println("remove duplicate element :" + Arrays.toString(arr));

	}

	/**
	 * Bubble sort is a simple comparison-based sorting algorithm. It repeatedly steps through the list, 
	 * compares adjacent elements, and swaps them if they are in the wrong order.
	 *  O(n²) time complexity 
	 *  Explanation:
		 	Outer loop (i): Iterates through the array multiple times.
		 	Inner loop (j): Compares adjacent elements and swaps them if necessary.
		 	Swapping: If arr[j] > arr[j + 1], the elements are swapped.
	 *	
	 * Example :         
	 * 	int[] arr = {5, 2, 8, 1, 3};

	 */
	private static int[] sortingArrayWithBubbleSort(int[] arr) {
		//bubble Sort 
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}
	
	/**
	 * Selection Sort works by repeatedly selecting the minimum element from the unsorted portion
	 *  of the array and moving it to the sorted portion.
	 * 
	 * Explanation:
		Outer loop (i): Iterates through the array and assumes the element at i is the minimum.
		Inner loop (j): Finds the minimum element from the unsorted part of the array.
		Swapping: The minimum element found is swapped with the element at the current index i.
	 * 
	 */
	private static int[] sortingArrayWithSelectionSort(int[] arr) {
		//Selection Sort Algorithm
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			// getting index of minimum element
			for (int j = i+1; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
            // Swap the found minimum element with the element at index i
			int temp = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i]= temp;
		}
		return arr;
	}

	/**
	 * Insertion sort builds the sorted array one element at a time by taking elements from 
	 * the unsorted portion and inserting them into their correct position in the sorted portion.
	 * 
	 * Explanation:
		Key element: The element at index i is the key, which will be inserted into the sorted portion.
		Inner loop (while): Finds the correct position for the key by shifting elements greater than the key to the right.
		Insertion: The key is placed in its correct position.
	 * 
	 * Example :         
	 * 		int[] arr = {5, 2, 8, 1, 3};
	 */
	private static int[] sortingArrayWithInsertionSort(int[] arr) {
		//Insertion Sort Algorithm
		for (int i = 1; i < arr.length ; i++) {
			int key = arr[i];
			int j = i-1;
			
            // Move elements of arr[0..i-1] that are greater than key to one position ahead
			while(j>=0 && arr[j]>key) {
				arr[j+1] = arr[j];
				j = j-1;
			}
            // Place the key in its correct position
			arr[j+1] = key;
		}
		return arr;
	}


	public static int removeElement(int[] nums, int val) {
		int k = 0; // This will track the position of the next valid element

		if (val > 0) {
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] != val) { // If current element is not val
					nums[k] = nums[i]; // Move it to the 'k' position
					k++; // Increment the position for the next valid element
				}
			}
			System.out.println("new length of the array with 'val' removed :" + (k));

			return k; // 'k' is the new length of the array with 'val' removed

		} else {
			for (int i = 1; i < nums.length; i++) {
				if (nums[i] != nums[k]) { // If current element is not val
					k++; // Increment the position for the next valid element
					nums[k] = nums[i]; // Move it to the 'k' position

				}
			}
			System.out.println("total unique element :" + (k + 1));

			return k + 1; // number of unique elements is i + 1

		}

	}

	// Helper method to check if a value is in the array
	private static boolean contains(int[] array, int value) {
		return Arrays.stream(array).anyMatch(num -> num == value);
	}

	public static void findMIssingNumbers(int[] arr) {
		// approach 1
		IntStream intStream = IntStream.rangeClosed(1, 10);
		intStream.filter(num -> !contains(arr, num)).forEach(System.out::print);

		System.out.println();

		// approach 2
		int j = 0;
		for (int i = 1; i <= 6; i++) {
			if (j < arr.length && arr[j] == i) {
				j++;
			} else {
				System.out.println(i);
			}
		}
	}

}
