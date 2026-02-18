package javaTest;

import java.util.HashMap;

// Youtube URL : https://www.youtube.com/watch?v=P-vl_BrdxJA&list=PLQ7ZAf76c0ZPVdhV1bAjFv0bQc1xHURzE&index=20
public class BinarySearch {
	public static void main(String[] args) {
		int arr[] = {2,5,7,11,45,87,90};
        int arr2[] = {90,87,54,36,22,1};
        binarySearch(arr, 7);
        binarySearchDesc(arr2,54);
        binarySearchOrderAgnostic(arr2,54); // find sorted arrays type
        binarySearchOrderAgnostic(arr,87);
     //   finds out the first occurrence 
        int arr1[] = {2, 2, 3, 4, 4, 4, 4, 6, 8, 9};
        int target = 4;
        int firstOcc = binarySearchOccurrence(arr1, target, true); // first occurrence
        int lastOcc = binarySearchOccurrence(arr1, target, false); // // last occurrence
        int res[] = new int[2];
        res[0] = firstOcc;
        res[1] = lastOcc;
        if(firstOcc ==-1) {
            System.out.println("Total Occurrence target "+ target +" is: " + 0);
        }else {
        	System.out.println("Total Occurrence target "+ target +" is: " + (lastOcc - firstOcc+1));
        }
                
        int arrfloor[] = {4,4,8,8,8,15,16,23,23,42};
        binarySearchFloor(arrfloor, 17);
        binarySearchFloor(arrfloor, 3);
        
        binarySearchCeeling(arrfloor, 17);
        binarySearchCeeling(arrfloor, 3);
        
        char[] charArr = {'c','i','f'};
        binarySearchCeelingProblem(charArr, 'a');

        arr = new int[] {1,5,2,5,3,5,5,4,5};
        int majorityElement = getMajorityElement(arr);
    	System.out.println("majorityElement:"+ majorityElement);

    	arr = new int[] {2,2,2,4,5,7,9,25,56,88,94};
    	findMinimumAbosulateDiffernece(arr, 20); //ceiling and floor
		findMinimumAbosulateDiffernece(arr, 100); //floor
		findMinimumAbosulateDiffernece(arr, 0); //ceiling
		
		arr = new int[] {2,2,2,4,5,7,9,25,56,88,94}; // infinite array
		findElementInInfiniteSortedArray(arr, 25) ; 
		findElementInInfiniteSortedArray(arr, 110) ;

	}
	
	public static void findElementInInfiniteSortedArray(int[] arr, int target) {
		int range[] = findRangeInfiniteArray(arr, target);
        int ans = binarySearchInRange(arr, target, range[0], range[1]);
        if (ans == -1){
            System.out.println("binarySearchInRange : target "+target+" not found");
        }
        else {
            System.out.println("binarySearchInRange : Found target "+ target+ " at index : "+ ans);
        }		
	}
	
	public static int binarySearchInRange(int[] arr, int target, int start, int end) {
		int ans = -1;
		while(start<=end) {
			int mid = start + (end-start)/2;
			if(arr[mid]==target) {
				ans = mid;
				break;
			}else if(arr[mid]< target){
				start = mid +1;
			}else {
				end = mid-1;
			}
		}
		return ans;
	}
	public static int[] findRangeInfiniteArray(int[] arr, int target) {
		int[] range = new int[2];
		int start =0;
		int end = 1;
		while(arr[end]< target) {
			if((2*end)<= arr.length-1) {
				start = end;
				end = 2*end;
			}else {
				break;
			}
		}
		range[0] = start;
		range[1] = end;
		return range;
	}
	
	public static void findMinimumAbosulateDiffernece(int arr[], int target) {
		int ans = -1;
		int start = 0;
		int end = arr.length-1;
		while(start<=end) {
			int mid = start + (end-start)/2;
			if(arr[mid]==target) {
				ans = mid;
				break;
			}else if(arr[mid]< target){
				start = mid +1;
			}else {
				end = mid-1;
			}
		}
		
		int res= 0;
		if(ans == -1) {
			if(end == -1) {
				res = Math.abs(target-arr[start]);
			}else if(start == arr.length) {
				res = Math.abs(target-arr[end]);
			}else {
				int ans1 = Math.abs(target-arr[start]);
				int ans2 = Math.abs(target-arr[start]);
				res = ans1< ans2? ans1: ans2;
			}
		}
		
		System.out.println("Minimum Abosulate Differnece is:  "+ res);

	}
	
	public static int getMajorityElement(int [] arr) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int num : arr) {
			map.put(num, (map.getOrDefault(num,0)+1));
			if(map.get(num)>arr.length/2) {
				return num;
			}
		}
		return -1;
	}

	public static char binarySearchCeelingProblem(char[] arr, char target) {
		char ans = '$';
		int start = 0;
		int end = arr.length-1;
		while(start<=end) {
			int mid = start + (end-start)/2;
			if(arr[mid]<= target){
				start = mid +1;
			}else {
				end = mid-1;
				ans = arr[mid];
			}
		}
		if (ans == '$'){
			ans = arr[0];
        }
        	
        System.out.println("binarySearchCeelingProblem : Found element "+ target+ " at floor element : "+ ans);

		return ans == '$'?arr[0]:ans;
	}
	
	
	public static int binarySearchCeeling(int[] arr, int target) {
		int ans = -1;
		int start = 0;
		int end = arr.length-1;
		while(start<=end) {
			int mid = start + (end-start)/2;
			if(arr[mid]==target) {
				ans = arr[mid];
				break;
			}else if(arr[mid]< target){
				start = mid +1;
			}else {
				end = mid-1;
				ans = arr[mid];
			}
		}
		if (ans == -1){
            System.out.println("binarySearchCeeling : Element not found");
        }
        else {
            System.out.println("binarySearchCeeling : Found element "+ target+ " at floor element : "+ ans);
        }		
		return ans;
	}
	public static int binarySearchFloor(int[] arr, int target) {
		int ans = -1;
		int start = 0;
		int end = arr.length-1;
		while(start<=end) {
			int mid = start + (end-start)/2;
			if(arr[mid]==target) {
				ans = arr[mid];
				break;
			}else if(arr[mid]< target){
				start = mid +1;
				ans = arr[mid];
			}else {
				end = mid-1;
			}
		}
		if (ans == -1){
            System.out.println("binarySearchFloor : Element not found");
        }
        else {
            System.out.println("binarySearchFloor : Found element '"+ target+ "' at floor element : "+ ans);
        }		
		return ans;
	}
	
	public static int binarySearchOccurrence(int[] arr, int target, boolean isFirst) {
		int ans = -1;
		int start = 0;
		int end = arr.length-1;
		while(start<=end) {
			int mid = start + (end-start)/2;
			if(arr[mid]==target) {
				ans = mid;
				if(isFirst) {
					end = mid -1;
				}else {
					start = mid +1;
				}
			}else if(arr[mid]< target){
				start = mid +1;
			}else {
				end = mid-1;
			}
		}
		if (ans == -1){
            System.out.println("Binary Search Occurrence : Target "+target+" not found");
        }
        else {
            System.out.println("Binary "+(isFirst==true?" first": "last")+ " Search Occurrence : Found target "+ target+ " at index : "+ ans);
        }		
		return ans;
	}

	// Agnostic Binary Search is an algorithm where we do not know whether the given sorted array is 
	// ascending or descending order
	public static int binarySearchOrderAgnostic(int[] arr, int target) {
		int ans = -1;
		int start = 0;
		int end = arr.length-1;
		if(arr[start]<= arr[end]) {
			ans = binarySearch(arr, target) ;
		}else {
			ans = binarySearchDesc(arr, target) ;
		}
		if (ans == -1){
            System.out.println("binarySearchOrderAgnostic : Element not found");
        }
        else {
            System.out.println("binarySearchOrderAgnostic : Found element "+ target+ " at index : "+ ans);
        }
		return ans;
		
	}

	public static int binarySearch(int[] arr, int target) {
		int ans = -1;
		int start = 0;
		int end = arr.length-1;
		while(start<=end) {
			int mid = start + (end-start)/2;
			if(arr[mid]==target) {
				ans = mid;
				break;
			}else if(arr[mid]< target){
				start = mid +1;
			}else {
				end = mid-1;
			}
		}
		if (ans == -1){
            System.out.println("binarySearch : Element not found");
        }
        else {
            System.out.println("binarySearch : Found element "+ target+ " at index : "+ ans);
        }		
		return ans;
	}
	
	public static int binarySearchDesc(int[] arr, int target) {
		int ans = -1;
		int start = 0;
		int end = arr.length-1;
		while(start<=end) {
			int mid = start + (end-start)/2;
			if(arr[mid]==target) {
				ans = mid;
				break;
			}else if(arr[mid]< target){
				end = mid-1;
			}else {
				start = mid +1;
			}
		}
		if (ans == -1){
            System.out.println("Element not found");
        }
        else {
            System.out.println("Found element "+ target+ " at index : "+ ans);
        }
		
		return ans;
	}
}
