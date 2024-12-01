package DpOnStocks;

import java.util.Arrays;

public class BuyAndSellStockWithTransactionFee {

	public static void main(String[] args) {
		int[] arr = { 3, 3, 5, 0, 3, 1, 4 };
		System.out.println(maximumProfit(arr.length, 1, arr));
	}

	// Recursive Approach

	public static int maximumProfit(int n, int fee, int[] prices) {
		return f(0, 1, prices, fee, n);
	}

	public static int f(int ind, int buy, int[] prices, int fee, int n) {
		if (ind == n)
			return 0;

		if (buy == 1) {
			return Math.max(-prices[ind] + f(ind + 1, 0, prices, fee, n), f(ind + 1, 1, prices, fee, n));
		}
		return Math.max(prices[ind] - fee + f(ind + 1, 1, prices, fee, n), f(ind + 1, 0, prices, fee, n));
	}

	// Memoization

	public static int maximumProfitMemoiz(int n, int fee, int[] prices) {
		int[][] dp = new int[n][2];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		return f(0, 1, prices, fee, n, dp);
	}

	public static int f(int ind, int buy, int[] prices, int fee, int n, int[][] dp) {
		if (ind == n)
			return 0;
		if (dp[ind][buy] != -1)
			return dp[ind][buy];
		if (buy == 1) {
			return dp[ind][buy] = Math.max(-prices[ind] + f(ind + 1, 0, prices, fee, n, dp),
					f(ind + 1, 1, prices, fee, n, dp));
		}
		return dp[ind][buy] = Math.max(prices[ind] - fee + f(ind + 1, 1, prices, fee, n, dp),
				f(ind + 1, 0, prices, fee, n, dp));
	}

	// Tabulation

	public static int maximumProfitTab(int n, int fee, int[] values) {
		int[][] dp = new int[n + 1][2];
		dp[n][0] = dp[n][1] = 0;

		for (int ind = n - 1; ind >= 0; ind--) {
			for (int buy = 0; buy <= 1; buy++) {
				int profit = 0;
				if (buy == 1) {
					profit = Math.max(-values[ind] + dp[ind + 1][0], dp[ind + 1][1]);
				} else {
					profit = Math.max(values[ind] - fee + dp[ind + 1][1], dp[ind + 1][0]);
				}
				dp[ind][buy] = profit;
			}
		}

		return dp[0][1];
	}

}
