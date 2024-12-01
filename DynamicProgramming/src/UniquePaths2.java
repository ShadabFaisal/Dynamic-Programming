import java.util.Arrays;

public class UniquePaths2 {

	public static void main(String[] args) {
		int[][] mat = { { 0, 0, 0 }, { 0, -1, 0 }, { 0, 0, 0 } };
		System.out.println(mazeObstacles(mat[0].length, mat.length, mat));
		System.out.println(mazeObstaclesMemoiz(mat[0].length, mat.length, mat));
		System.out.println(mazeObstaclesTabulation(mat[0].length, mat.length, mat));
		System.out.println(mazeObstaclesSpOp(mat[0].length, mat.length, mat));

	}
	// Recursive Code

	static int mazeObstacles(int n, int m, int[][] mat) {
		return f(m - 1, n - 1, mat);
	}

	static int f(int i, int j, int[][] mat) {
		if (i >= 0 && j >= 0 && mat[i][j] == -1)
			return 0;
		if (i == 0 && j == 0)
			return 1;
		if (i < 0 || j < 0)
			return 0;

		int up = f(i - 1, j, mat);
		int left = f(i, j - 1, mat);
		return up + left;
	}

	// Memoization
	static int mazeObstaclesMemoiz(int n, int m, int[][] mat) {
		int[][] dp = new int[n][m];
		for(int[] row: dp)
			Arrays.fill(row, -1);
		
		return f(n - 1, m - 1, mat, dp);
	}

	static int f(int i, int j, int[][] mat, int[][] dp) {
		if (i >= 0 && j >= 0 && mat[i][j] == -1)
			return 0;
		if (i == 0 && j == 0)
			return 1;
		if (i < 0 || j < 0)
			return 0;
		if (dp[i][j] != -1)
			return dp[i][j];

		int up = f(i - 1, j, mat, dp);
		int left = f(i, j - 1, mat, dp);
		return dp[i][j] = (up + left) % 1000000007;
	}

	// Tabulation

	static int mazeObstaclesTabulation(int n, int m, int[][] mat) {
		int[][] dp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (mat[i][j] == -1)
					dp[i][j] = 0;
				else if (i == 0 && j == 0)
					dp[i][j] = 1;
				else {
					int up = 0, left = 0;
					if (i > 0)
						up = dp[i - 1][j];
					if (j > 0)
						left = dp[i][j - 1];

					dp[i][j] = (up + left) % 1000000007;
				}
			}
		}
		return dp[n - 1][m - 1];
	}

	// Space Optimisation

	static int mazeObstaclesSpOp(int n, int m, int[][] mat) {
		int[] prev = new int[m];
		for (int i = 0; i < n; i++) {
			int[] temp = new int[m];
			for (int j = 0; j < m; j++) {
				if (mat[i][j] == -1)
					temp[j] = 0;
				else if (i == 0 && j == 0)
					temp[j] = 1;
				else {
					int up = 0, left = 0;
					if (i > 0)
						up = prev[j];
					if (j > 0)
						left = temp[j - 1];

					temp[j] = (up + left) % 1000000007;
				}
			}
			prev = temp;
		}
		return prev[m - 1];

	}

}
