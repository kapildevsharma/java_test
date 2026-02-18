package com.Test;

import java.util.Arrays;

/**
 * @author kapilsharma05
 *
 */
public class MergeArr {

	public static void main(String[] args) {
		int [] nums1= new int [] {1,2,3,0,0,0};
		int [] nums2 = new int[] {2,5,6};
		int m = 3,  n = 3;	
		
		MergeArr test = new MergeArr();
		test.merge(nums1,m,nums2,n);
		int[] rArr  = new int[] {0,1,2,2,3,0,4,2};
		int val = 2;
	//	int []rArr  =  new int[] {3,2,2,3};
	//	int val = 3;
		System.out.println(test.removeElement(rArr, val));
	}
	
	public int removeElement(int[] nums, int val) {
        int count = 0;
        int [] result = new int [nums.length];
        for (int i=0, j = 0; i<nums.length;i++){
            if(nums[i]==val) {
            	count++;
            }
            else {
            	result[j] = nums[i]; j++;
            }
        }
        int [] test = new int[result.length - count];
        for(int i = 0 ;i <result.length - count ; i++) {
        	test[i] = result[i];
        }
        System.out.println("Count of val "+ val + " is :: "+ count);
        System.out.print(Arrays.toString(nums)  + " :: "+ Arrays.toString(result));

        nums = test;
        System.out.println("After Removal ");
        System.out.print(Arrays.toString(nums));
        System.out.println();
        return nums.length;
        
    }
    public  void merge(int[] nums1, int m, int[] nums2, int n) {
    	
        int numsArr1 []  = new int[m];
        int numsArr2 []  = new int[n];
        for( int i = 0; i < m; i++) {
        	numsArr1[i] = nums1[i];
        }
        for( int i = 0; i < n; i++) {
        	numsArr2[i] = nums2[i];
        }
               
        int resultArr[] = new int [m + n];
        System.out.println("Before Copy ");
        System.out.println(Arrays.toString(nums1)  + " "+ Arrays.toString(nums2));
        System.out.println("After Copy to new Arr ");
        System.out.println(Arrays.toString(numsArr1) + " "+ Arrays.toString(numsArr2));
        int i = 0;
        for (int x : numsArr1) { resultArr[i] = x; i ++; }
        for (int x : numsArr2) { resultArr[i] = x; i ++; }
        System.out.println("Before Sort ");
        System.out.println(Arrays.toString(resultArr) );
        Arrays.sort(resultArr);
        System.out.println("After Sort ");
        System.out.println(Arrays.toString(resultArr) );

    }


}
