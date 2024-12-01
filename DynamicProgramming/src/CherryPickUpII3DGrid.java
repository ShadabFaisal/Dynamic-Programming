import java.util.Arrays;

public class CherryPickUpII3DGrid {

	public static void main(String[] args) {
		int matrix[][] = { { 2, 3, 1, 2 }, { 3, 4, 2, 2 }, { 5, 6, 3, 5 } };
		int n = matrix.length;
		int m = matrix[0].length;

		System.out.println(maximumChocolatesSpOp(n, m, matrix));
	}

	// Recursive Approach
	public static int maximumChocolates(int r, int c, int[][] grid) {
		return f(0, 0, c - 1, r, c, grid);
	}

	public static int f(int i, int j1, int j2, int r, int c, int[][] grid) {
		if (j1 < 0 || j2 < 0 || j1 >= c || j2 >= c) {
			return -100000000;
		}
		if (i == r - 1) {
			if (j1 == j2)
				return grid[i][j1];
			else
				return grid[i][j1] + grid[i][j2];
		}
		// Explore all paths of Alice and Bob simultaneously
		int maxi = 0;
		for (int dj1 = -1; dj1 <= 1; dj1++) {
			for (int dj2 = -1; dj2 <= 1; dj2++) {
				int value = 0;
				if (j1 == j2) {
					value = grid[i][j1];
				} else {
					value = grid[i][j1] + grid[i][j2];
				}
				value += f(i + 1, j1 + dj1, j2 + dj2, r, c, grid);
				maxi = Math.max(maxi, value);
			}
		}
		return maxi;
	}
	// Memoization

	public static int maximumChocolatesMemoiz(int r, int c, int[][] grid) {
		int[][][] dp = new int[r][c][c];
		for (int row1[][] : dp) {
			for (int row2[] : row1) {
				Arrays.fill(row2, -1);
			}
		}

		return f(0, 0, c - 1, r, c, grid, dp);
	}

	public static int f(int i, int j1, int j2, int r, int c, int[][] grid, int[][][] dp) {

		if (j1 < 0 || j2 < 0 || j1 >= c || j2 >= c) {
			return -100000000;
		}
		if (i == r - 1) {
			if (j1 == j2)
				return grid[i][j1];
			else
				return grid[i][j1] + grid[i][j2];
		}
		if (dp[i][j1][j2] != -1)
			return dp[i][j1][j2];

		// Explore all paths of Alice and Bob simultaneously

		int maxi = 0;
		for (int dj1 = -1; dj1 <= 1; dj1++) {
			for (int dj2 = -1; dj2 <= 1; dj2++) {
				int value = 0;
				if (j1 == j2) {
					value = grid[i][j1];
				} else {
					value = grid[i][j1] + grid[i][j2];
				}
				value += f(i + 1, j1 + dj1, j2 + dj2, r, c, grid, dp);
				maxi = Math.max(maxi, value);
			}
		}
		return dp[i][j1][j2] = maxi;
	}
	
	//Tabulation
	public static int maximumChocolatesTabulation(int n, int m, int[][] grid) {
        int[][][] dp=new int[n][m][m];
        for (int row1[][]: dp) {
              for (int row2[]: row1) {
                Arrays.fill(row2, -1);
              }
        }
        //Base case
        for(int j1=0;j1<m;j1++){
            for(int j2=0;j2<m;j2++){
                if(j1==j2)
                    dp[n-1][j1][j2]=grid[n-1][j1];
                else
                    dp[n-1][j1][j2]=grid[n-1][j1]+grid[n-1][j2];
            }
        }
        
        //Explore all the paths
        
        for(int i=n-2;i>=0;i--){
            for(int j1=0;j1<m;j1++){
                for(int j2=0;j2<m;j2++){
                    int maxi=-100000000;
                    for(int dj1=-1;dj1<=1;dj1++){
                        for(int dj2=-1;dj2<=1;dj2++){
                            int value=0;
                            if(j1==j2){
                                value=grid[i][j1];
                            }
                            else{
                                value=grid[i][j1]+grid[i][j2];
                            }
                            if(j1+dj1>=0 && j1+dj1<m && j2+dj2>=0 && j2+dj2<m)
                                value+=dp[i+1][j1+dj1][j2+dj2];
                            else
                                value+=-100000000;
                            
                            maxi=Math.max(maxi,value);
                        }
                    } 
                    dp[i][j1][j2]=maxi;
                }
            }
        }
		return dp[0][0][m-1];
	}
	
	//Space Optimisation
	
	public static int maximumChocolatesSpOp(int n, int m, int[][] grid) {
        
        int[][] front=new int[m][m];
        
        //Base case
        for(int j1=0;j1<m;j1++){
            for(int j2=0;j2<m;j2++){
                if(j1==j2)
                    front[j1][j2]=grid[n-1][j1];
                else
                    front[j1][j2]=grid[n-1][j1]+grid[n-1][j2];
            }
        }
        
        //Explore all the paths
        
        for(int i=n-2;i>=0;i--){
             int[][] cur=new int[m][m];
            for(int j1=0;j1<m;j1++){
                for(int j2=0;j2<m;j2++){
                    int maxi=-100000000;
                    for(int dj1=-1;dj1<=1;dj1++){
                        for(int dj2=-1;dj2<=1;dj2++){
                            int value=0;
                            if(j1==j2){
                                value=grid[i][j1];
                            }
                            else{
                                value=grid[i][j1]+grid[i][j2];
                            }
                            if(j1+dj1>=0 && j1+dj1<m && j2+dj2>=0 && j2+dj2<m)
                                value+=front[j1+dj1][j2+dj2];
                            else
                                value+=-100000000;
                            
                            maxi=Math.max(maxi,value);
                        }
                    } 
                    cur[j1][j2]=maxi;
                }
            }
            front=cur;
        }
		return front[0][m-1];
	}
	

}
