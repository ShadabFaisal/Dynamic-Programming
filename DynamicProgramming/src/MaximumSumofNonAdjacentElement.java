import java.util.ArrayList;
import java.util.Arrays;

public class MaximumSumofNonAdjacentElement {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(4);
		System.out.println(maximumNonAdjacentSum(list));
		System.out.println(maximumNonAdjacentSumMemoiz(list));
		System.out.println(maximumNonAdjacentSumTabulation(list));
		System.out.println(maximumNonAdjacentSumSpaceOptimised(list));
	}

	// Recursive Approach
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		int n = nums.size();
		return f(n - 1, nums);
	}

	public static int f(int ind, ArrayList<Integer> nums) {
		if (ind == 0)
			return nums.get(ind);
		if (ind < 0)
			return 0;

		int pick = nums.get(ind) + f(ind - 2, nums);
		int notpick = 0 + f(ind - 1, nums);

		return Math.max(pick, notpick);
	}

	// Memoizaton
	public static int maximumNonAdjacentSumMemoiz(ArrayList<Integer> nums) {
		int n = nums.size();
		int[] dp = new int[n];
		Arrays.fill(dp, -1);
		return func(n - 1, nums, dp);
	}

	public static int func(int ind, ArrayList<Integer> nums, int[] dp) {
		if (ind == 0)
			return nums.get(ind);
		if (ind < 0)
			return 0;
		if (dp[ind] != -1)
			return dp[ind];
		int pick = nums.get(ind) + func(ind - 2, nums, dp);
		int notpick = 0 + func(ind - 1, nums, dp);

		return dp[ind] = Math.max(pick, notpick);
	}

	// Tabulation
	public static int maximumNonAdjacentSumTabulation(ArrayList<Integer> nums) {
		int n = nums.size();
		int[] dp = new int[n];
		dp[0] = nums.get(0);
		for (int i = 1; i < n; i++) {
			int take = nums.get(i);
			if (i > 1) {
				take += dp[i - 2];
			}
			int nontake = 0 + dp[i - 1];
			dp[i] = Math.max(take, nontake);
		}
		return dp[n - 1];
	}

	// Space Optimisation
	public static int maximumNonAdjacentSumSpaceOptimised(ArrayList<Integer> nums) {
		int n = nums.size();
		int prev = nums.get(0);
		int prev2 = 0;
		for (int i = 1; i < n; i++) {
			int take = nums.get(i);
			if (i > 1) {
				take += prev2;
			}
			int nontake = 0 + prev;
			int curi = Math.max(take, nontake);
			prev2 = prev;
			prev = curi;
		}
		return prev;
	}

}
