package javaTest;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// get current count of consecutive 1
public class HighestRepeatCount {

	public static void main(String[] args) {
		int[] arr = {0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0};

        String joined = Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("joined: " + joined);

        int maxCount = Arrays.stream(joined.split("0"))
                .mapToInt(String::length)
                .max()
                .orElse(0);

        System.out.println("Highest consecutive count of 1's: " + maxCount);
		maxCount = 0;
		int currentCount =0;
		for(int i=0 ;i<arr.length;i++) {
			if(arr[i]==1) {
				currentCount++;
				maxCount = Math.max(maxCount, currentCount);
			}
			else {
				currentCount =0;
			}
		}

        System.out.println("Highest consecutive count of 1's: " + maxCount);

	}

}
