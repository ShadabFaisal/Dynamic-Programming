package PartitionDP;

import java.util.Arrays;

public class PartitionArrayForMaximumSum {

	public static void main(String[] args) {
		int[] arr= {1,15,7,9,2,5,10};
		int k=3;
		System.out.println(maximumSubarray(arr,k));
	}
	
	//Recursive Approach
	
	public static int maximumSubarray(int num[], int k) {
        return f(0,num,k,num.length);
    }
    public static int f(int ind, int[] nums,int k,int n){
        
        if(ind==n)return 0;
        
        int maxAns=Integer.MIN_VALUE,maxi=Integer.MIN_VALUE;
        int len=0;
        for(int j=ind;j<Math.min(n,ind+k);j++){
            len++;
            maxi=Math.max(maxi,nums[j]);
            int sum=(len*maxi)+f(j+1,nums,k,n);
            maxAns=Math.max(maxAns,sum);
        }
        return maxAns;
    }
    
    //Memoization Approach
    
    public static int maximumSubarrayMemoiz(int num[], int k) {
        int n=num.length;
        int[] dp=new int[n];
        Arrays.fill(dp,-1);
        return f(0,num,k,n,dp);
    }
    public static int f(int ind, int[] nums,int k,int n,int[] dp){
        
        if(ind==n)return 0;
        if(dp[ind]!=-1)return dp[ind];
        int maxAns=Integer.MIN_VALUE,maxi=Integer.MIN_VALUE;
        int len=0;
        for(int j=ind;j<Math.min(n,ind+k);j++){
            len++;
            maxi=Math.max(maxi,nums[j]);
            int sum=(len*maxi)+f(j+1,nums,k,n,dp);
            maxAns=Math.max(maxAns,sum);
        }
        return dp[ind]=maxAns;
    }
    
    //Tabulation
    
    public static int maximumSubarrayTab(int num[], int k) {
        int n=num.length;
        int[] dp=new int[n+1];
        dp[n]=0;
        for(int i=n-1;i>=0;i--){
            int maxAns=Integer.MIN_VALUE,maxi=Integer.MIN_VALUE;
            int len=0;
            for(int j=i;j<Math.min(n,i+k);j++){
                len++;
                maxi=Math.max(maxi,num[j]);
                int sum=(len*maxi)+dp[j+1];
                maxAns=Math.max(maxAns,sum);
            }
            dp[i]=maxAns;
        }
        
        return dp[0];
    }


}
