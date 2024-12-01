package DpOnStocks;

import java.util.Arrays;

public class BuyAndSellStockWithCooldown {

	public static void main(String[] args) {
		int[] arr = { 3, 3, 5, 0, 3, 1, 4 };
		System.out.println(stockProfit(arr));
	}
	// Recursive Approach
		public static int f(int ind, int buy, int[] prices) {
			if (ind >= prices.length)
				return 0;

			if (buy == 1) {
				return Math.max(-prices[ind] + f(ind + 1, 0, prices), 0 + f(ind + 1, 1, prices));
			}
			return Math.max(prices[ind] + f(ind + 2, 1, prices), 0 + f(ind + 1, 0, prices));
		}

		public static int stockProfit(int[] prices) {
			return f(0, 1, prices);
		}
		// Memoization

		public static int f(int ind, int buy, int[] prices, int[][] dp) {
			if (ind >= prices.length)
				return 0;
			if (dp[ind][buy] != -1)
				return dp[ind][buy];
			if (buy == 1) {
				return dp[ind][buy] = Math.max(-prices[ind] + f(ind + 1, 0, prices, dp), 0 + f(ind + 1, 1, prices, dp));
			}
			return dp[ind][buy] = Math.max(prices[ind] + f(ind + 2, 1, prices, dp), 0 + f(ind + 1, 0, prices, dp));
		}

		public static int stockProfitMemoiz(int[] prices) {
			int[][] dp = new int[prices.length][2];
			for (int[] row : dp) {
				Arrays.fill(row, -1);
			}
			return f(0, 1, prices, dp);
		}

		// Tabulation

		public static int stockProfitTabulation(int[] prices) {
			int[][] dp = new int[prices.length + 2][2];

			for (int ind = prices.length - 1; ind >= 0; ind--) {
				for (int buy = 0; buy <= 1; buy++) {
					if (buy == 1) {
						dp[ind][buy] = Math.max(-prices[ind] + dp[ind + 1][0], dp[ind + 1][1]);
					} else {
						dp[ind][buy] = Math.max(prices[ind] + dp[ind + 2][1], dp[ind + 1][0]);
					}
				}
			}
			return dp[0][1];
		}

		// SpaceOptimisation
		public static int stockProfitSpOp(int[] prices) {
	        int[][] dp=new int[prices.length+2][2];
	        
	        for(int ind=prices.length-1;ind>=0;ind--){
	                    dp[ind][1]=Math.max(-prices[ind]+dp[ind+1][0],dp[ind+1][1]);

	                    dp[ind][0]=Math.max(prices[ind]+dp[ind+2][1],dp[ind+1][0]);
	        }
	        return dp[0][1];
	    }

}
