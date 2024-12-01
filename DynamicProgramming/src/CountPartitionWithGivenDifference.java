import java.util.Arrays;

public class CountPartitionWithGivenDifference {

	public static void main(String[] args) {
		int[] arr= {5,2,6,4};
		int d=3;
		System.out.println(countPartitionsSpOp(arr.length,d,arr));
	}
	static int  mod=1000000007;
	public static int countPartitions(int n, int d, int[] arr) {
        int totsum=0;
        for(int i: arr)totsum+=i;
        
        if(totsum-d<0 || (totsum-d)%2!=0){
            return 0;
        }
        return findWaysMemoiz(arr,(totsum-d)/2);
    }
    public static int findWaysMemoiz(int num[], int tar) {
        int n = num.length;
        int[][] dp = new int[n][tar + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return f(n - 1, tar, num, dp);
    }

    public static int f(int ind, int target, int[] nums, int[][] dp) {
       
        if (ind == 0) {
            if(target==0 && nums[0]==0)return 2;
            if(target==0 || target==nums[0])return 1;
            return 0;
        }
        if (dp[ind][target] != -1)
            return dp[ind][target];
        int notpick = f(ind - 1, target, nums, dp);
        int pick = 0;
        if (nums[ind] <= target) {
            pick = f(ind - 1, target - nums[ind], nums, dp);
        }
        return dp[ind][target] = (pick + notpick)%mod;
    }
    
    //Tabulation
    
	public static int countPartitionsTabulation(int n, int d, int[] arr) {
        int totsum=0;
        for(int i: arr)totsum+=i;
        
        if(totsum-d<0 || (totsum-d)%2!=0){
            return 0;
        }
        return findWaysTabulation(arr,(totsum-d)/2);
    }
   

    public static int findWaysTabulation(int num[], int tar) {
        int n=num.length;
        int[][] dp=new int[n][tar+1];
        for(int[] row: dp){
            Arrays.fill(row,0);
        }
        if(num[0]==0)dp[0][0]=2;
        else dp[0][0]=1;
        
        if(num[0]!=0 && num[0]<=tar)
            dp[0][num[0]]=1;
        
        for(int i=1;i<n;i++){
            for(int sum=0;sum<=tar;sum++){
                int notpick=dp[i-1][sum];
                int pick =0;
                if(num[i]<=sum){
                        pick=dp[i-1][sum-num[i]];
                }
                dp[i][sum]=(pick+notpick)%mod;
            }
        }
        
        return dp[n-1][tar];
    }

    //Space Optimisation
    
    public static int countPartitionsSpOp(int n, int d, int[] arr) {
        int totsum=0;
        for(int i: arr)totsum+=i;
        
        if(totsum-d<0 || (totsum-d)%2!=0){
            return 0;
        }
        return findWaysSpOp(arr,(totsum-d)/2);
    }
   

    public static int findWaysSpOp(int num[], int tar) {
        int n=num.length;
        int[] prev=new int[tar+1];
        
        if(num[0]==0)prev[0]=2;
        else prev[0]=1;
        
        if(num[0]!=0 && num[0]<=tar)
            prev[num[0]]=1;
        
        for(int i=1;i<n;i++){
             int[] cur=new int[tar+1];
            for(int sum=0;sum<=tar;sum++){
                int notpick=prev[sum];
                int pick =0;
                if(num[i]<=sum){
                        pick=prev[sum-num[i]];
                }
                cur[sum]=(pick+notpick)%mod;
            }
            prev=cur;
        }
        
        return prev[tar];
    }
}
