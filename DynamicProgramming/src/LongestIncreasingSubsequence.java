import java.util.Arrays;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[] arr= {10,9,2,5,3,7,101,18};
		System.out.println(longestIncreasingSubsequence(arr));

	}
	
	//Recursive Approach
	public static int f(int ind, int prev_ind, int[] nums, int n){
        if(ind==n)return 0;
        
        int len=0+f(ind+1,prev_ind,nums,n);
        if(prev_ind==-1 || nums[ind]>nums[prev_ind]){
            len=Math.max(len,1+f(ind+1,ind,nums,n));
        }
        return len;
    }
	public static int longestIncreasingSubsequence(int arr[]) {
        return f(0,-1,arr,arr.length);
    }
	
	//Memoization
	
	public static int f(int ind, int prev_ind, int[] nums, int n,int[][] dp){
        if(ind==n)return 0;
        if(dp[ind][prev_ind+1]!=-1)return dp[ind][prev_ind+1];
        int len=0+f(ind+1,prev_ind,nums,n,dp);
        if(prev_ind==-1 || nums[ind]>nums[prev_ind]){
            len=Math.max(len,1+f(ind+1,ind,nums,n,dp));
        }
        return dp[ind][prev_ind+1]=len;
    }
	public static int longestIncreasingSubsequenceMemoiz(int arr[]) {
        int[][] dp=new int[arr.length][arr.length+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return f(0,-1,arr,arr.length,dp);
    }
	
	//Tabulation
	
	public static int longestIncreasingSubsequenceTab(int arr[]) {
        int[][] dp=new int[arr.length+1][arr.length+1];
       
        for(int ind=arr.length-1;ind>=0;ind--){
            for(int prev_ind=ind-1;prev_ind>=-1;prev_ind--){
                int len=0+dp[ind+1][prev_ind+1];
                if(prev_ind==-1 || arr[ind]>arr[prev_ind]){
                    len=Math.max(len,1+dp[ind+1][ind+1]);
                }
                dp[ind][prev_ind+1]=len;
            }
        }
        return dp[0][0];
    }
	
	//SpaceOptimisation
	
	public static int longestIncreasingSubsequenceSpOp(int arr[]) {
        int[] next=new int[arr.length+1];
       
        for(int ind=arr.length-1;ind>=0;ind--){
             int[] cur=new int[arr.length+1];
            for(int prev_ind=ind-1;prev_ind>=-1;prev_ind--){
                int len=0+next[prev_ind+1];
                if(prev_ind==-1 || arr[ind]>arr[prev_ind]){
                    len=Math.max(len,1+next[ind+1]);
                }
                cur[prev_ind+1]=len;
            }
            next=cur;
        }
        return next[0];
    }
	
	//SpaceOptimisation 2
	
	public static int longestIncreasingSubsequenceSpOp2(int arr[]) {
        int[] dp=new int[arr.length];
        Arrays.fill(dp,1);
        int maxi=1;
        for(int i=0;i<arr.length;i++){
            for(int prev=0;prev<i;prev++){
                if(arr[prev]<arr[i]){
                    dp[i]=Math.max(1+dp[prev],dp[i]);
                }
            }
            maxi=Math.max(maxi,dp[i]);
        }
        return maxi;
    }
	
	

}
