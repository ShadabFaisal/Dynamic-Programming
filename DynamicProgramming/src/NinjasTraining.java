import java.util.Arrays;

public class NinjasTraining {

	public static void main(String[] args) {
		int[][] points = { { 10, 40, 70 }, { 20, 50, 80 }, { 30, 60, 90 } };
		System.out.println(ninjaTraining(points.length, points));
		System.out.println(ninjaTrainingMemoiz(points.length, points));
		System.out.println(ninjaTrainingTabulation(points.length, points));
		System.out.println(ninjaTrainingSpopt(points.length, points));
	}

	// Recursive Approach
	public static int f(int day, int last, int points[][]) {
		if (day == 0) {
			int maxi = 0;
			for (int task = 0; task < 3; task++) {
				if (task != last) {
					maxi = Math.max(maxi, points[0][task]);
				}
			}
			return maxi;
		}
		int maxi = 0;
		for (int task = 0; task < 3; task++) {
			if (task != last) {
				int point = points[day][task] + f(day - 1, task, points);
				maxi = Math.max(maxi, point);
			}
		}
		return maxi;
	}

	public static int ninjaTraining(int n, int points[][]) {
		return f(n - 1, 3, points);
	}

	// Memoization
	public static int f(int day, int last, int[][] points, int[][] dp) {

		if (dp[day][last] != -1)
			return dp[day][last];

		if (day == 0) {
			int maxi = 0;
			for (int i = 0; i <= 2; i++) {
				if (i != last)
					maxi = Math.max(maxi, points[0][i]);
			}
			return dp[day][last] = maxi;
		}

		int maxi = 0;
		for (int i = 0; i <= 2; i++) {
			if (i != last) {
				int activity = points[day][i] + f(day - 1, i, points, dp);
				maxi = Math.max(maxi, activity);
			}

		}

		return dp[day][last] = maxi;
	}

	public static int ninjaTrainingMemoiz(int n, int[][] points) {

		int dp[][] = new int[n][4];
		for (int[] row : dp)
			Arrays.fill(row, -1);

		return f(n - 1, 3, points, dp);
	}

	// Tabulation
	public static int ninjaTrainingTabulation(int n, int[][] points) {

		int dp[][] = new int[n][4];

		dp[0][0] = Math.max(points[0][1], points[0][2]);
		dp[0][1] = Math.max(points[0][0], points[0][2]);
		dp[0][2] = Math.max(points[0][0], points[0][1]);
		dp[0][3] = Math.max(points[0][1], Math.max(points[0][0], points[0][2]));
		for (int day = 1; day < n; day++) {
			for (int last = 0; last < 4; last++) {
				dp[day][last] = 0;
				for (int i = 0; i <= 2; i++) {
					if (i != last) {
						int activity = points[day][i] + dp[day - 1][i];
						dp[day][last] = Math.max(dp[day][last], activity);
					}
				}
			}
		}

		return dp[n - 1][3];
	}

	// Space Optimisation

	public static int ninjaTrainingSpopt(int n, int[][] points) {

		int prev[] = new int[4];

		prev[0] = Math.max(points[0][1], points[0][2]);
		prev[1] = Math.max(points[0][0], points[0][2]);
		prev[2] = Math.max(points[0][0], points[0][1]);
		prev[3] = Math.max(points[0][1], Math.max(points[0][0], points[0][2]));

		for (int day = 1; day < n; day++) {
			int[] temp = new int[4];
			for (int last = 0; last < 4; last++) {
				temp[last] = 0;
				for (int i = 0; i <= 2; i++) {
					if (i != last) {
						temp[last] = Math.max(temp[last], points[day][i] + prev[i]);
					}
				}
			}
			prev = temp;
		}
		return prev[3];
	}

}
