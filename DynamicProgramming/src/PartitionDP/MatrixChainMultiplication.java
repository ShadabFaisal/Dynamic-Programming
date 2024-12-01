package PartitionDP;

import java.util.Arrays;

public class MatrixChainMultiplication {

	public static void main(String[] args) {

		int[] arr = { 10, 20, 30, 40, 50 };
		System.out.println(matrixMultiplicationTab(arr, arr.length));
	}
	// Recursive Approach

	public static int matrixMultiplication(int[] arr, int N) {
		return f(1, N - 1, arr);
	}

	static int f(int i, int j, int[] arr) {
		if (i == j)
			return 0;
		int mini = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			int steps = arr[i - 1] * arr[k] * arr[j] + f(i, k, arr) + f(k + 1, j, arr);
			mini = Math.min(mini, steps);
		}
		return mini;
	}

	// Memoization

	public static int matrixMultiplicationMemoiz(int[] arr, int N) {

		int[][] dp = new int[N][N];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		return f(1, N - 1, arr, dp);
	}

	static int f(int i, int j, int[] arr, int[][] dp) {
		if (i == j)
			return 0;
		if (dp[i][j] != -1)
			return dp[i][j];
		int mini = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			int steps = arr[i - 1] * arr[k] * arr[j] + f(i, k, arr, dp) + f(k + 1, j, arr, dp);
			mini = Math.min(mini, steps);
		}
		return dp[i][j] = mini;
	}

	// Tabulation

	public static int matrixMultiplicationTab(int[] arr, int N) {

		int[][] dp = new int[N][N];
		for (int i = 1; i < N; i++) {
			dp[i][i] = 0;
		}
		for (int i = N - 1; i >= 1; i--) {
			for (int j = i + 1; j < N; j++) {
				int mini = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					int steps = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];
					mini = Math.min(mini, steps);
				}
				dp[i][j] = mini;
			}
		}
		return dp[1][N - 1];
	}

}
