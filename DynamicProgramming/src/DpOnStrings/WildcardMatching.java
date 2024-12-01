package DpOnStrings;

import java.util.Arrays;

public class WildcardMatching {

	public static void main(String[] args) {

		String s = "?ay";
		String t = "ray";
		System.out.println(wildcardMatching(s, t));
	}

	// Recursive Approach
	public static boolean wildcardMatching(String pattern, String text) {
		int n = pattern.length();
		int m = text.length();
		return f(n - 1, m - 1, pattern, text);
	}

	public static boolean f(int i, int j, String s, String t) {
		if (i < 0 && j < 0)
			return true;
		if (i < 0 && j >= 0)
			return false;
		if (j < 0 && i >= 0) {
			for (int k = 0; k <= i; k++) {
				if (s.charAt(k) != '*') {
					return false;
				}
				return true;
			}
		}
		if (s.charAt(i) == t.charAt(j) || s.charAt(i) == '?') {
			return f(i - 1, j - 1, s, t);
		}
		if (s.charAt(i) == '*') {
			return f(i - 1, j, s, t) | f(i, j - 1, s, t);
		}
		return false;
	}
	// Memoization

	public static boolean wildcardMatchingMemoiz(String pattern, String text) {
		int n = pattern.length();
		int m = text.length();
		int[][] dp = new int[n][m];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		return f(n - 1, m - 1, pattern, text, dp) == 1 ? true : false;
	}

	public static int f(int i, int j, String s, String t, int[][] dp) {
		if (i < 0 && j < 0)
			return 1;
		if (i < 0 && j >= 0)
			return 0;
		if (j < 0 && i >= 0) {
			for (int k = 0; k <= i; k++) {
				if (s.charAt(k) != '*') {
					return 0;
				}
				return 1;
			}
		}
		if (dp[i][j] != -1)
			return dp[i][j];
		if (s.charAt(i) == t.charAt(j) || s.charAt(i) == '?') {
			return dp[i][j] = f(i - 1, j - 1, s, t, dp);
		}
		if (s.charAt(i) == '*') {
			return dp[i][j] = (f(i - 1, j, s, t, dp) == 1) || (f(i, j - 1, s, t, dp) == 1) ? 1 : 0;
		}
		return 0;
	}
	// Tabulation

	public static boolean wildcardMatchingTabulation(String s, String t) {
		int n = s.length();
		int m = t.length();
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[0][0] = true;
		for (int j = 1; j <= m; j++) {
			dp[0][j] = false;
		}
		for (int i = 1; i <= n; i++) {
			boolean flag = true;
			for (int k = 1; k <= i; k++) {
				if (s.charAt(k - 1) != '*') {
					flag = false;
					break;
				}
			}
			dp[i][0] = flag;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1) || s.charAt(i - 1) == '?') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (s.charAt(i - 1) == '*') {
					dp[i][j] = dp[i - 1][j] | dp[i][j - 1];
				} else {
					dp[i][j] = false;
				}
			}
		}
		return dp[n][m];
	}

	// SpaceOptimisation
	public static boolean wildcardMatchingSpOp(String s, String t) {
		int n = s.length();
		int m = t.length();
		boolean[] prev = new boolean[m + 1];
		prev[0] = true;
		for (int j = 1; j <= m; j++) {
			prev[j] = false;
		}
		for (int i = 1; i <= n; i++) {
			boolean[] cur = new boolean[m + 1];
			boolean flag = true;
			for (int k = 1; k <= i; k++) {
				if (s.charAt(k - 1) != '*') {
					flag = false;
					break;
				}
			}
			cur[0] = flag;
			for (int j = 1; j <= m; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1) || s.charAt(i - 1) == '?') {
					cur[j] = prev[j - 1];
				} else if (s.charAt(i - 1) == '*') {
					cur[j] = prev[j] | cur[j - 1];
				} else {
					cur[j] = false;
				}
			}
			prev = cur;
		}
		return prev[m];
	}

}
