import java.util.Arrays;

public class TriangleFixedStartVariableEnd {

	public static void main(String[] args) {
		int[][] triangle= {{1},{2,3},{3,6,7},{8,9,6,10}};
		System.out.println(minimumPathSum(triangle,4));

	}

	// Recursive Code
	public static int minimumPathSum(int[][] triangle, int n) {
		return f(0, 0, triangle, n);
	}

	static int f(int i, int j, int[][] triangle, int n) {
		if (i == n - 1)
			return triangle[n - 1][j];

		int d = triangle[i][j] + f(i + 1, j, triangle, n);
		int diag = triangle[i][j] + f(i + 1, j + 1, triangle, n);

		return Math.min(d, diag);
	}
	
	// Memoization
	
	public static int minimumPathSumMemoiz(int[][] triangle, int n) {
        int[][] dp=new int[n][n];
        for(int[] row: dp)
            Arrays.fill(row,-1);
        
        return f(0,0,triangle,n,dp);
    }
    static int f(int i,int j,int[][] triangle, int n,int[][] dp){
        if(i==n-1)
            return triangle[n-1][j];
        if(dp[i][j]!=-1)return dp[i][j];
        int d=triangle[i][j]+f(i+1,j,triangle,n,dp);
        int diag=triangle[i][j]+f(i+1,j+1,triangle,n,dp);
        
        return dp[i][j]=Math.min(d,diag);
    }
    
    //Tabulation
    public static int minimumPathSumTabulation(int[][] triangle, int n) {
        int[][] dp=new int[n][n];
        for(int j=0;j<n;j++)
            dp[n-1][j]=triangle[n-1][j];
        
        for(int i=n-2;i>=0;i--){
            for(int j=i;j>=0;j--){
                 int d=triangle[i][j]+dp[i+1][j];
                 int diag=triangle[i][j]+dp[i+1][j+1];
                dp[i][j]=Math.min(d,diag);
            }
        }
        return dp[0][0];
    }
    
    //Space Optimisation
    
    public static int minimumPathSumSpOp(int[][] triangle, int n) {
        int[] front=new int[n];
        for(int j=0;j<n;j++)
            front[j]=triangle[n-1][j];
        
        for(int i=n-2;i>=0;i--){
            int[] cur=new int[n];
            for(int j=i;j>=0;j--){
                 int d=triangle[i][j]+front[j];
                 int diag=triangle[i][j]+front[j+1];
                cur[j]=Math.min(d,diag);
            }
            front=cur;
        }
        return front[0];
    }
    

}
