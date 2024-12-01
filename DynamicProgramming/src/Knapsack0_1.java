import java.util.Arrays;

public class Knapsack0_1 {

	public static void main(String[] args) {
		int wt[] = { 1, 2, 4, 5 };
		int val[] = { 5, 4, 8, 6 };
		int W = 5;

		int n = wt.length;

		System.out.println("The Maximum value of items, thief can steal is " + knapsackSpOp2(wt, val, n, W));

	}

	// Recursive Code
	static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
		return f(n - 1, maxWeight, weight, value);
	}

	static int f(int ind, int W, int[] wt, int[] val) {
		if (ind == 0) {
			if (wt[ind] <= W)
				return val[0];
			else
				return 0;
		}
		int nottake = 0 + f(ind - 1, W, wt, val);
		int take = Integer.MIN_VALUE;
		if (wt[ind] <= W) {
			take = val[ind] + f(ind - 1, W - wt[ind], wt, val);
		}
		return Math.max(nottake, take);
	}

	// Memoization

	static int knapsackMemoiz(int[] weight, int[] value, int n, int maxWeight) {
		int[][] dp = new int[n][maxWeight + 1];
		for (int[] row : dp)
			Arrays.fill(row, -1);

		return f(n - 1, maxWeight, weight, value, dp);
	}

	static int f(int ind, int W, int[] wt, int[] val, int[][] dp) {
		if (ind == 0) {
			if (wt[ind] <= W)
				return val[ind];
			else
				return 0;
		}
		if (dp[ind][W] != -1)
			return dp[ind][W];
		int nottake = 0 + f(ind - 1, W, wt, val, dp);
		int take = Integer.MIN_VALUE;
		if (wt[ind] <= W) {
			take = val[ind] + f(ind - 1, W - wt[ind], wt, val, dp);
		}
		return dp[ind][W] = Math.max(nottake, take);
	}

	// Tabulation
	static int knapsackTabulation(int[] wt, int[] val, int n, int maxWeight) {
		int[][] dp = new int[n][maxWeight + 1];
		for (int i = wt[0]; i <= maxWeight; i++)
			dp[0][i] = val[0];

		for (int i = 1; i < n; i++) {
			for (int W = 0; W <= maxWeight; W++) {
				int nottake = 0 + dp[i - 1][W];
				int take = Integer.MIN_VALUE;
				if (wt[i] <= W) {
					take = val[i] + dp[i - 1][W - wt[i]];
				}
				dp[i][W] = Math.max(take, nottake);
			}
		}

		return dp[n - 1][maxWeight];
	}
	
	//Space Optimisation 1
	
	static int knapsackSpOp1(int[] wt, int[] val, int n, int maxWeight) {
        int[] prev=new int[maxWeight+1];
        for(int i=wt[0];i<=maxWeight;i++)
            prev[i]=val[0];
        
        for(int i=1;i<n;i++){
            int[] cur=new int[maxWeight+1];
            for(int W=0;W<=maxWeight;W++){
                int nottake=0+prev[W];
                int take=Integer.MIN_VALUE;
                if(wt[i]<=W){
                    take=val[i]+prev[W-wt[i]];
                }
                cur[W]=Math.max(take,nottake);
            }
            prev=cur;
        }
        
        return prev[maxWeight];
    }
	
	//Space Optimisation 2
	
	static int knapsackSpOp2(int[] wt, int[] val, int n, int maxWeight) {
        int[] prev=new int[maxWeight+1];
        for(int i=wt[0];i<=maxWeight;i++)
            prev[i]=val[0];
        
        for(int i=1;i<n;i++){
            for(int W=maxWeight;W>=0;W--){
                int nottake=0+prev[W];
                int take=Integer.MIN_VALUE;
                if(wt[i]<=W){
                    take=val[i]+prev[W-wt[i]];
                }
                prev[W]=Math.max(take,nottake);
            }
        }
        
        return prev[maxWeight];
    }
    
	
	

}
