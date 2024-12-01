import java.util.Arrays;

public class SubsetSumEqualsToTarget {

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4 };
		int k = 4;
		int n = arr.length;

		if (subsetSumToK(n, k, arr))
			System.out.println("Subset with given target found");
		else
			System.out.println("Subset with given target not found");
	}

	// Recursive code
	public static boolean subsetSumToK(int n, int k, int arr[]) {
		return f(n - 1, k, arr);
	}

	static boolean f(int ind, int k, int[] arr) {
		if (k == 0) {
			return true;
		}
		if (ind == 0)
			return arr[0] == k;

		boolean nottake = f(ind - 1, k, arr);
		boolean take = false;

		if (k >= arr[ind])
			take = f(ind - 1, k - arr[ind], arr);

		return take | nottake;
	}

	// Memoization

	public static boolean subsetSumToKmemoiz(int n, int k, int arr[]) {
		int[][] dp = new int[n][k + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		return f(n - 1, k, arr, dp);
	}

	static boolean f(int ind, int k, int[] arr, int[][] dp) {
		if (k == 0) {
			return true;
		}
		if (ind == 0)
			return arr[0] == k;

		if (dp[ind][k] != -1)
			return dp[ind][k] == 0 ? false : true;

		boolean nottake = f(ind - 1, k, arr, dp);
		boolean take = false;

		if (k >= arr[ind])
			take = f(ind - 1, k - arr[ind], arr, dp);

		dp[ind][k] = take || nottake ? 1 : 0;
		return take || nottake;
	}

	// Tabulation

	public static boolean subsetSumToKtabulation(int n, int k, int arr[]) {
		boolean dp[][] = new boolean[n][k + 1];

		for (int i = 0; i < n; i++) {
			dp[i][0] = true;
		}

		if (arr[0] <= k)
			dp[0][arr[0]] = true;

		for (int ind = 1; ind < n; ind++) {
			for (int target = 1; target <= k; target++) {

				boolean notTaken = dp[ind - 1][target];

				boolean taken = false;
				if (arr[ind] <= target)
					taken = dp[ind - 1][target - arr[ind]];

				dp[ind][target] = notTaken || taken;
			}
		}

		return dp[n - 1][k];
	}

	// Space Optimisation
	
	 public static boolean subsetSumToKSpOp(int n, int k, int arr[]){
         boolean prev[]= new boolean[k+1];
         
            prev[0]=true;
        
    if(arr[0]<=k)
        prev[arr[0]] = true;
    
    for(int ind = 1; ind<n; ind++){
        boolean cur[]= new boolean[k+1];
        cur[0]=true;
        for(int target= 1; target<=k; target++){
            
            boolean notTaken = prev[target];
    
            boolean taken = false;
                if(arr[ind]<=target)
                    taken = prev[target-arr[ind]];
        
            cur[target]= notTaken||taken;
        }
        prev=cur;
    }
    
    return prev[k];
    }


}
