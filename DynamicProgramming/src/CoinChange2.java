import java.util.Arrays;

public class CoinChange2 {

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3 };
		int target = 4;
		System.out.println("The total number of ways is " + countWaysToMakeChange(arr, target));
	}

	// Recursive Code
	public static long countWaysToMakeChange(int denominations[], int value) {
		int n = denominations.length;
		return countWaysToMakeChangeUtil(denominations, n - 1, value);
	}

	static long countWaysToMakeChangeUtil(int[] arr, int ind, int T) {

		if (ind == 0) {
			if (T % arr[0] == 0)
				return 1;
			else
				return 0;
		}

		long notTaken = countWaysToMakeChangeUtil(arr, ind - 1, T);

		long taken = 0;
		if (arr[ind] <= T)
			taken = countWaysToMakeChangeUtil(arr, ind, T - arr[ind]);

		return notTaken + taken;
	}

	// Memoization

	public static long countWaysToMakeChangeMemoiz(int denominations[], int value) {
		int n = denominations.length;
		long dp[][] = new long[n][value + 1];
		for (long row[] : dp)
			Arrays.fill(row, -1);
		return countWaysToMakeChangeUtil(denominations, n - 1, value, dp);
	}

	static long countWaysToMakeChangeUtil(int[] arr, int ind, int T, long[][] dp) {

		if (ind == 0) {
			if (T % arr[0] == 0)
				return 1;
			else
				return 0;
		}

		if (dp[ind][T] != -1)
			return dp[ind][T];

		long notTaken = countWaysToMakeChangeUtil(arr, ind - 1, T, dp);

		long taken = 0;
		if (arr[ind] <= T)
			taken = countWaysToMakeChangeUtil(arr, ind, T - arr[ind], dp);

		return dp[ind][T] = notTaken + taken;
	}

	// Tabulation

	public static long countWaysToMakeChangeTabulation(int denominations[], int value) {
		int n = denominations.length;
		long dp[][] = new long[n][value + 1];
		// Initializing base condition
		for (int i = 0; i <= value; i++) {
			if (i % denominations[0] == 0)
				dp[0][i] = 1;
			// Else condition is automatically fulfilled,
			// as dp array is initialized to zero
		}

		for (int ind = 1; ind < n; ind++) {
			for (int target = 0; target <= value; target++) {
				long notTaken = dp[ind - 1][target];

				long taken = 0;
				if (denominations[ind] <= target)
					taken = dp[ind][target - denominations[ind]];

				dp[ind][target] = notTaken + taken;
			}
		}

		return dp[n - 1][value];
	}

	
	//Space Optimisation
	public static long countWaysToMakeChangeSpOp(int denominations[], int value){
        int n=denominations.length;
      long prev[]=new long[value+1];
    //Initializing base condition
    for(int i=0;i<=value;i++){
        if(i%denominations[0]==0)
            prev[i]=1;
        // Else condition is automatically fulfilled,
        // as dp array is initialized to zero
    }
    
    for(int ind=1; ind<n;ind++){
        long cur[]=new long[value+1];
        for(int target=0;target<=value;target++){
            long notTaken = prev[target];
            
            long taken = 0;
            if(denominations[ind]<=target)
                taken = cur[target-denominations[ind]];
                
            cur[target] = notTaken + taken;
        }
        prev=cur;
    }
    
    return prev[value];
	}
	
}
