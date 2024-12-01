package DpOnStrings;

public class ShortestCommonSupersequence {

	public static void main(String[] args) {
		String s="bleed";
		String t="blue";
		System.out.println(shortestSupersequence(s,t));

	}

	public static String reverse(String t) {
		if (t == null || t.length() <= 1)
			return t;
		return reverse(t.substring(1)) + t.charAt(0);
	}

	public static String shortestSupersequence(String s, String t) {
		int n = s.length();
		int m = t.length();
		int[][] dp = new int[n + 1][m + 1];
		for (int j = 0; j <= m; j++)
			dp[0][j] = 0;
		for (int i = 0; i <= n; i++)
			dp[i][0] = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		int i = n, j = m;
		String ans = "";
		while (i > 0 && j > 0) {
			if (s.charAt(i - 1) == t.charAt(j - 1)) {
				ans += s.charAt(i - 1);
				i--;
				j--;
			} else if (dp[i - 1][j] > dp[i][j - 1]) {
				ans += s.charAt(i - 1);
				i--;
			} else {
				ans += t.charAt(j - 1);
				j--;
			}
		}
		while (i > 0) {
			ans += s.charAt(i - 1);
			i--;
		}
		while (j > 0) {
			ans += t.charAt(j - 1);
			j--;
		}
		return reverse(ans);
	}

}
