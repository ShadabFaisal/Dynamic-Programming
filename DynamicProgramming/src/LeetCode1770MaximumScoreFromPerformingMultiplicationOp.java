
public class LeetCode1770MaximumScoreFromPerformingMultiplicationOp {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		int[] muls = { 3, 2, 1 };
		System.out.println(maximumScoreBruteForce(nums, muls));
		System.out.println(maximumScoreOptimised(nums, muls));

	}

	static int helper(int[] nums, int[] multipliers, int left, int right, int op) {
		// For Right Pointer

		if (op == multipliers.length) {
			return 0;
		}

		int l = nums[left] * multipliers[op] + helper(nums, multipliers, left + 1, right, op + 1);
		int r = nums[right] * multipliers[op] + helper(nums, multipliers, left, right - 1, op + 1);

		return Math.max(l, r);
	}

	public static int maximumScoreBruteForce(int[] nums, int[] multipliers) {
		// Zero operation done in the beginning
		return helper(nums, multipliers, 0, nums.length - 1, 0);
	}

	public static int maximumScoreOptimised(int[] nums, int[] multipliers) {
		int n = nums.length;
		int m = multipliers.length;

		int[] dp = new int[m + 1];

		for (int i = m - 1; i >= 0; i--) {
			int mul = multipliers[i];
			for (int l = 0; l <= i; l++) {
				int r = n - 1 - (i - l);
				int left = nums[l] * mul + dp[l + 1];
				int right = nums[r] * mul + dp[l];
				dp[l] = Math.max(left, right);
			}
		}
		return dp[0];
	}

}
