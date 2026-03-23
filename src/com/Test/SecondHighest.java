package com.Test;

import java.util.Arrays;
import java.util.Comparator;

// find second-largest element without sorting
public class SecondHighest {
	public static void main(String arg[]) {
		int[] arr = { 12, 35, 1, 10, 34, 1 };
		int secondNUm = findSecondHighest(arr);
		System.out.println("findSecondHighest : " + secondNUm);

		arr = new int[] { 12, 35, 1, 10, 34, 1 };
		secondNUm = findSecondHighestWithSort(arr);
		System.out.println("findSecondHighestWithSort : " + secondNUm);

		secondNUm = findSecondHighestWithStream(arr);
		System.out.println("findSecondHighestWithStream : " + secondNUm);

        int nthNUm = findNthHighestWithStream(arr, 2);
        System.out.println("nthNUm : " + nthNUm);
	}

	public static int findSecondHighest(int[] arr) {
		int length = arr.length;
		int largest = Integer.MIN_VALUE;
		int secondLargest = Integer.MIN_VALUE;
		for (int i = 0; i < length; i++) {
			if (arr[i] > largest) {
				secondLargest = largest;
				largest = arr[i];
			} else {
				if (arr[i] > secondLargest && arr[i] != largest) {
					secondLargest = arr[i];
				}
			}
		}
		return secondLargest;
	}

	public static int findSecondHighestWithSort(int[] arr) {
		Arrays.sort(arr);
		return arr[arr.length - 2];
	}

	public static int findSecondHighestWithStream(int[] arr) {
		//Integer result = Arrays.stream(arr).boxed().distinct().sorted((a, b) -> b - a).skip(1).findFirst().get();
        Integer result = Arrays.stream(arr).boxed().distinct().sorted(Comparator.reverseOrder())
                .skip(1).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No second largest element"));

        return result;
	}

    public static int findNthHighestWithStream(int[] arr, int n) {
        //Integer result = Arrays.stream(arr).boxed().distinct().sorted((a, b) -> b - a).skip(1).findFirst().get();
        Integer result = Arrays.stream(arr).boxed().distinct().sorted(Comparator.reverseOrder())
                .skip(n-1).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No Nth largest element"));

        return result;
    }
}
