import java.util.Arrays;

public class CountSubsetsWithSumEqualToK {

	public static void main(String[] args) {
		int[] arr = { 3, 2, 7 };
		System.out.println(findWays(arr, 9));
	}

	// Recursive Approach

	public static int findWays(int num[], int tar) {
		int n = num.length;
		return f(n - 1, tar, num);
	}

	public static int f(int ind, int target, int[] nums) {
		if (target == 0)
			return 1;
		if (ind == 0) {
			if (nums[ind] == target)
				return 1;
			else
				return 0;
		}
		int notpick = f(ind - 1, target, nums);
		int pick = 0;
		if (nums[ind] <= target) {
			pick = f(ind - 1, target - nums[ind], nums);
		}
		return pick + notpick;
	}

	// Memoization

	public static int findWaysMemoiz(int num[], int tar) {
		int n = num.length;
		int[][] dp = new int[n][tar + 1];

		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		return f(n - 1, tar, num, dp);
	}

	public static int f(int ind, int target, int[] nums, int[][] dp) {
		if (target == 0)
			return 1;
		if (ind == 0) {
			if (nums[ind] == target)
				return 1;
			else
				return 0;
		}
		if (dp[ind][target] != -1)
			return dp[ind][target];
		int notpick = f(ind - 1, target, nums, dp);
		int pick = 0;
		if (nums[ind] <= target) {
			pick = f(ind - 1, target - nums[ind], nums, dp);
		}
		return dp[ind][target] = pick + notpick;
	}
	
	//Tabulation
	
    public static int findWaysTabulation(int num[], int tar) {
        int n=num.length;
        int[][] dp=new int[n][tar+1];
        for(int[] row: dp){
            Arrays.fill(row,0);
        }
        
        for(int i=0;i<n;i++){
            dp[i][0]=1;
        }
        if(num[0]<=tar)
            dp[0][num[0]]=1;
        
        for(int i=1;i<n;i++){
            for(int sum=0;sum<=tar;sum++){
                int notpick=dp[i-1][sum];
                int pick =0;
                if(num[i]<=sum){
                        pick=dp[i-1][sum-num[i]];
                }
                dp[i][sum]=pick+notpick;
            }
        }
        
        return dp[n-1][tar];
    }

    //Space Optimisation
    
    public static int findWaysSpOp(int num[], int tar) {
        int n=num.length;
        int[] prev=new int[tar+1];
        prev[0]=1;
        
        if(num[0]<=tar)
            prev[num[0]]=1;
        
        for(int i=1;i<n;i++){
            int[] cur=new int[tar+1];
            cur[0]=1;
            for(int sum=0;sum<=tar;sum++){
                int notpick=prev[sum];
                int pick =0;
                if(num[i]<=sum){
                        pick=prev[sum-num[i]];
                }
               cur[sum]=pick+notpick;
            }
            prev=cur;
        }
        
        return prev[tar];
    }
}
