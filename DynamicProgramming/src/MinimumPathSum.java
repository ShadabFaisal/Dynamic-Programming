import java.util.Arrays;

public class MinimumPathSum {

	public static void main(String[] args) {
		
		int[][] grid= {{1, 2, 3},
				{4, 5, 4},
				{7, 5, 9}};
		System.out.println(minSumPath(grid));

	}

	// Recursive Code
	public static int minSumPath(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		return f(n - 1, m - 1, grid);
	}

	static int f(int i, int j, int[][] grid) {
		if (i == 0 && j == 0)
			return grid[i][j];
		if (i < 0 || j < 0)
			return 1000000000;
		int up = grid[i][j] + f(i - 1, j, grid);
		int left = grid[i][j] + f(i, j - 1, grid);

		return Math.min(up, left);
	}

	// Memoization

	public static int minSumPathMemoiz(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		return f(n - 1, m - 1, grid, dp);
	}

	static int f(int i, int j, int[][] grid, int[][] dp) {
		if (i == 0 && j == 0)
			return grid[i][j];
		if (i < 0 || j < 0)
			return 1000000000;
		if (dp[i][j] != -1)
			return dp[i][j];
		int up = grid[i][j] + f(i - 1, j, grid, dp);
		int left = grid[i][j] + f(i, j - 1, grid, dp);

		return dp[i][j] = Math.min(up, left);
	}
	// Tabulation

	public static int minSumPathTabulation(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 && j == 0)
					dp[i][j] = grid[i][j];
				else {
					int up = grid[i][j];
					if (i > 0)
						up += dp[i - 1][j];
					else
						up += 1000000000;
					int left = grid[i][j];
					if (j > 0)
						left += dp[i][j - 1];
					else
						left += 1000000000;

					dp[i][j] = Math.min(up, left);
				}
			}
		}
		return dp[n - 1][m - 1];
	}
	
	//Space Optimisation
	
	public static int minSumPathSpOp(int[][] grid) {
    	int n=grid.length;
        int m=grid[0].length;
        int[] prev=new int[m];
        for(int i=0;i<n;i++){
            int[] temp=new int[m];
            for(int j=0;j<m;j++){
                if(i==0 && j==0)temp[j]=grid[i][j];
                else{
                    int up=grid[i][j];
                    if(i>0)up+=prev[j];
                    else up+=1000000000;
                    int left=grid[i][j];
                    if(j>0)left+=temp[j-1];
                    else left+=1000000000;
                    
                    temp[j]=Math.min(up,left);
                }
            }
            prev=temp;
        }
        return prev[m-1];
    }

}
