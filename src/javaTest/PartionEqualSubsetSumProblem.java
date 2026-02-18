package javaTest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PartionEqualSubsetSumProblem 
	{
	    public boolean canPartition(int[] nums) 
	    {
	        int totalSum=0;
	        for(int num:nums)
	            totalSum+=num;
	        
	        if(totalSum%2 == 0)
	        {//check if there is a subset with sum=totalSum/2
	            int target=totalSum/2;
	            int[][] dp=new int[nums.length][target+1];
	            for(int[] row:dp)
	                Arrays.fill(row,-1);
	            return findSumTarget(nums.length-1,target,nums,dp);
	        }
	        return false;
	    }
	    public boolean findSumTarget(int i,int target,int[] nums,int[][] dp)
	    {
	        if(target==0)
	            return true;
	        if(i==0)
	        {
	            if(nums[i]==target)
	                return true;
	            return false;
	        }
	        if(dp[i][target] != -1)
	            return dp[i][target]==1;
	        
	        boolean nottake=findSumTarget(i-1,target,nums,dp);
	        boolean take=false;
	        if(nums[i]<=target)
	            take=findSumTarget(i-1,target-nums[i],nums,dp);
	        dp[i][target]=take||nottake?1:0;
	        return take||nottake;
	    }
	    
	    // java 8 stream and hashSet
	    public boolean canPartitionB(int[] nums) {
	        int totalSum = Arrays.stream(nums).sum();

	        if (totalSum % 2 != 0) {
	            return false;
	        }

	        int targetSum = totalSum / 2;
	        Arrays.sort(nums);

	        Set<Integer> sums = new HashSet<>();
	        sums.add(0);

	        for (int num : nums) {
	            if (num > targetSum) {
	                break;
	            }

	            Set<Integer> newSums = new HashSet<>();

	            for (int val : sums) {
	                int newSum = val + num;

	                if (newSum == targetSum) {
	                    return true;
	                } else if (newSum < targetSum) {
	                    newSums.add(newSum);
	                }
	            }

	            sums.addAll(newSums);
	        }

	        return false;
	    }
	public static void main(String args[]) {
		PartionEqualSubsetSumProblem test = new PartionEqualSubsetSumProblem();
		int[] nums = new int[] {1,2,3,5};
		nums = new int[] {1,2,5};
		nums = new int[] {1,5,11,5};
		System.out.println(test.canPartition(nums));
	}
	}