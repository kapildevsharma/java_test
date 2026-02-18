package javaTest;

import java.util.Arrays;

// find out the pythagorean triplets in the given array
// A Pythagorean triplet is a set of three positive integers a, b, and c, such that a^2 + b^2 = c^2
// For example, (3, 4, 5) is a Pythagorean triplet because 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
public class PythagorasTripletsProblem {
    public static  void main(String [] args){
        int arr[]  = {5, 9, 6, 4, 1, 8, 70, 3,10};
        findPythagoreanTriplets(arr);
    }

    // 1,16,25,36,49,64,81,100
    // triplet: 3,4,5
    // 3^2 + 4^2 = 9 + 16 =

  //triplete solution: sort the array and then use two pointer approach to find the triplet

    private static void findPythagoreanTriplets(int[] arr) {
        // Sort the array
        Arrays.sort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));

        int n = arr.length;
        boolean found = false;

        // Check for Pythagorean triplets
        for (int i = n - 1; i >= 2; i--) {
            int c = arr[i];
            int left = 0;
            int right = i - 1;
            // Two pointer approach
            while (left < right) {
                int a = arr[left];
                int b = arr[right];
                int lhs = a * a + b * b;
                int rhs = c * c;
                if (lhs == rhs) {
                    found = true;
                    System.out.println("Pythagorean triplet found: (" + a + ", " + b + ", " + c + ")");
                    left++;
                    right--;
                }
                else if (lhs < rhs) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        if (!found)
            System.out.println("No Pythagorean triplet found");
    }
}
/*
Time Complexity (with explanation)
    1. Sorting the array: Takes O(n log n)
    2. Two-pointer search:
        Outer loop runs O(n) times -- (once for each possible c).
        Inner while loop runs O(n) in total per iteration
    Overall: O(n²)
Space Complexity
    O(n) for the squared array
*/
