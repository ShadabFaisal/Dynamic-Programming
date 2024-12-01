import java.util.Arrays;

public class GridUniquePaths {

	public static void main(String[] args) {
		System.out.println(uniquePaths(3,3));
		System.out.println(uniquePathsMemoiz(3,3));

	}
	
	// Recursive Solution
	public static int uniquePaths(int m, int n) {
        return f(m-1,n-1);
    }
    public static int f(int i,int j){
        if(i==0 && j==0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        }  
        int up=f(i-1,j);
        int left=f(i,j-1);
        return up+left;
    }
    
    // Memoization
    
	public static int uniquePathsMemoiz(int m, int n) {
        int[][] dp=new int[m][n];
        for(int[] row: dp){
            Arrays.fill(row,-1);
        }
        return f(m-1,n-1,dp);
    }
    public static int f(int i,int j, int[][] dp){
        if(i==0 && j==0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        }
        if(dp[i][j]!=-1)
            return dp[i][j];
        
        int up=f(i-1,j,dp);
        int left=f(i,j-1,dp);
        return dp[i][j]=up+left;
    }
    
    //Tabulation
    public static int uniquePathsTabulation(int m, int n) {
        int[][] dp=new int[m][n];
        dp[0][0]=1; 
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0)
                    dp[i][j]=1;
                else{
                    int up=0,left=0;
                    if(i>0)
                       up=dp[i-1][j];
                    if(j>0)
                       left=dp[i][j-1];
                    
                    dp[i][j]=up+left;
                }
            }
        }
        return dp[m-1][n-1];
    }
    
    // Space Optimisation
    
    public static int uniquePathsSpaceOp(int m, int n) {
        int[] prev=new int[n];
       
        for(int i=0;i<m;i++){
            int[] temp=new int[n];
            for(int j=0;j<n;j++){
                if(i==0 && j==0)
                    temp[j]=1;
                else{
                    int up=0,left=0;
                    if(i>0)
                       up=prev[j];
                    if(j>0)
                       left=temp[j-1];
                    
                    temp[j]=up+left;
                }
            }
            prev=temp;
        }
        return prev[n-1];
    }


}
