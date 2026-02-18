package javaTest;

import java.util.Arrays;

public class ArraysProblem {

	public static void main(String[] args) {
		int nums[] = { 2, 7, 11, 15 };
		int[] res = twoSum(nums, 18);
		System.out.println("toSums index: [" + res[0] + ", " + res[1] + "]");

		nums = new int[] { 10, 10, 20 };
		int result = removeDuplicates(nums);
		System.out.println("Duplicate element: " + result + " is remove");

		nums = new int[] { 1, 5, 2, 5, 3, 5, 5, 4, 5 };
		result = removeDuplicates(nums);
		System.out.println("Duplicate element: " + result + " is remove");

		nums = new int[] { 2, 0, 2, 1, 1, 0 }; // Output: [0,0,1,1,2,2]
		nums = sortingArray0s1st2ndSort(nums);
		System.out.println("sortingArray0s1st2ndSort arryas: " + Arrays.toString(nums));

	}

	private static int[] sortingArray0s1st2ndSort(int[] arr) {
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

	public static int removeDuplicates(int[] nums) {
		int res = 1;
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] != nums[i]) {
				nums[res++] = nums[i];
			}
		}

		for (int i = 0; i < res; i++) {
			System.out.println(i+ " " + nums[i] + " ");
		}

		return res;
	}

	// find out sum of value which is equals to target, return index if that 2
	// values
	public static int[] twoSum(int[] nums, int target) {
		int res[] = new int[2];
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					res[0] = i;
					res[1] = j;
					return res;
				}
			}
		}
		return new int[2];
	}

}
