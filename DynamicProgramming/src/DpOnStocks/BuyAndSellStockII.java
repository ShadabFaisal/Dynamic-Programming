package DpOnStocks;

import java.util.Arrays;

public class BuyAndSellStockII {

	public static void main(String[] args) {
		long[] prices= {7,1,5,3,6,4};
		long max=getMaximumProfit(prices.length,prices);
		System.out.println(max);
	}

	// Recursive Approach
	public static long getMaximumProfit(int n, long[] values) {
		return f(0, 1, values, n);
	}

	static long f(int ind, int buy, long[] values, int n) {
		if (ind == n) {
			return 0;
		}
		long profit = 0;
		if (buy == 1) {
			profit = Math.max(-values[ind] + f(ind + 1, 0, values, n), 0 + f(ind + 1, 1, values, n));
		} else {
			profit = Math.max(values[ind] + f(ind + 1, 1, values, n), 0 + f(ind + 1, 0, values, n));
		}
		return profit;
	}

	// Memoization
	public static long getMaximumProfitMemoiz(int n, long[] values) {

		int[][] dp = new int[n][2];
		for (int[] row : dp)
			Arrays.fill(row, -1);

		return f(0, 1, values, n, dp);
	}

	static long f(int ind, int buy, long[] values, int n, int[][] dp) {
		if (ind == n) {
			return 0;
		}
		long profit = 0;
		if (buy == 1) {
			profit = Math.max(-values[ind] + f(ind + 1, 0, values, n, dp), 0 + f(ind + 1, 1, values, n, dp));
		} else {
			profit = Math.max(values[ind] + f(ind + 1, 1, values, n, dp), 0 + f(ind + 1, 0, values, n, dp));
		}
		return profit;
	}
	
	//Tabulation
	
	 public static long getMaximumProfitTabulation (int n, long[] values) {
	        
	        long[][] dp=new long[n+1][2];
	        dp[n][0]=dp[n][1]=0;
	        
	        for(int ind=n-1;ind>=0;ind--){
	            for(int buy=0;buy<=1;buy++){
	                long profit=0;
	                if(buy==1){
	                    profit=Math.max(-values[ind]+dp[ind+1][0],dp[ind+1][1]);
	                }
	                else{
	                    profit=Math.max(values[ind]+dp[ind+1][1],dp[ind+1][0]);
	                }
	                dp[ind][buy]=profit;
	            }
	        }
	        
	        
	        return dp[0][1];
	    }
	 
	 //SpaceOptimisation
	 
	 public static long getMaximumProfitSpOp (int n, long[] values) {
	        
	        long[] ahead=new long[2];
	        ahead[0]=ahead[1]=0;
	        
	        for(int ind=n-1;ind>=0;ind--){
	             long[] cur=new long[2];
	            for(int buy=0;buy<=1;buy++){
	                long profit=0;
	                if(buy==1){
	                    profit=Math.max(-values[ind]+ahead[0],ahead[1]);
	                }
	                else{
	                    profit=Math.max(values[ind]+ahead[1],ahead[0]);
	                }
	                cur[buy]=profit;
	            }
	            ahead=cur;
	        }
	        
	        
	        return ahead[1];
	    }

}
