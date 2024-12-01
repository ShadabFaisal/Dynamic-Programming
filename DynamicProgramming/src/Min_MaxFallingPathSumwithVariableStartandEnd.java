import java.util.Arrays;

public class Min_MaxFallingPathSumwithVariableStartandEnd {

	public static void main(String[] args) {
		
		int matrix[][] = {{1,2,10,4},
                {100,3,2,1},
                {1,1,20,2},
                {1,2,2,1}};
		System.out.println(getMaxPathSumSpOp(matrix));

	}
	
	//Recursive Code
	
	public static int getMaxPathSum(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int maxi=-1000000000;
        for(int j=0;j<m;j++){
            maxi=Math.max(maxi,f(n-1,j,matrix));
        }
        return maxi; 
    }
    public static int f(int i, int j, int[][] mat){
        if(j<0 || j>=mat[0].length){
            return -1000000000;
        }
        if(i==0)return mat[0][j];
        int up=mat[i][j]+f(i-1,j,mat);
        int ld=mat[i][j]+f(i-1,j-1,mat);
        int rd=mat[i][j]+f(i-1,j+1,mat);
        return Math.max(up,Math.max(ld,rd));
    }
    
    //Memoization
    
    public static int getMaxPathSumMemoiz(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int[][] dp=new int[n][m];
        for(int[] row:dp)
            Arrays.fill(row,-1);
        
        int maxi=-1000000000;
        for(int j=0;j<m;j++){
            maxi=Math.max(maxi,f(n-1,j,matrix,dp));
        }
        return maxi; 
    }
    
    public static int f(int i, int j, int[][] mat,int[][] dp){
        if(j<0 || j>=mat[0].length){
            return -1000000000;
        }
        if(i==0)return mat[0][j];
        if(dp[i][j]!=-1)return dp[i][j];
        int up=mat[i][j]+f(i-1,j,mat,dp);
        int ld=mat[i][j]+f(i-1,j-1,mat,dp);
        int rd=mat[i][j]+f(i-1,j+1,mat,dp);
        return dp[i][j]=Math.max(up,Math.max(ld,rd));
    }
    
    
    //Tabulation
    
    public static int getMaxPathSumTabulation(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int[][] dp=new int[n][m];
        
        for(int j=0;j<m;j++){
            dp[0][j]=matrix[0][j];
        }
        
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                int up=matrix[i][j]+dp[i-1][j];
                
                int ld=matrix[i][j];
                if(j-1>=0)ld+=dp[i-1][j-1];
                else ld+=(int)Math.pow(-10,9);
                
                int rd=matrix[i][j];
                if(j+1<m)rd+=dp[i-1][j+1];
                else rd+=(int)Math.pow(-10,9);
                
                dp[i][j]=Math.max(up,Math.max(ld,rd));
            }
        }
        
        
        int maxi=Integer.MIN_VALUE;
        for(int j=0;j<m;j++){
            maxi=Math.max(maxi,dp[n-1][j]);
        }
        return maxi; 
    }   
    
    //Space Optimisation
    public static int getMaxPathSumSpOp(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int[] prev=new int[m];
        for(int j=0;j<m;j++){
            prev[j]=matrix[0][j];
        }
        
        for(int i=1;i<n;i++){
            int[] cur=new int[m];
            for(int j=0;j<m;j++){
                int up=matrix[i][j]+prev[j];
                
                int ld=matrix[i][j];
                if(j-1>=0)ld+=prev[j-1];
                else ld+=(int)Math.pow(-10,9);
                
                int rd=matrix[i][j];
                if(j+1<m)rd+=prev[j+1];
                else rd+=(int)Math.pow(-10,9);
                
                cur[j]=Math.max(up,Math.max(ld,rd));
            }
            prev=cur;
        }
        
        
        int maxi=Integer.MIN_VALUE;
        for(int j=0;j<m;j++){
            maxi=Math.max(maxi,prev[j]);
        }
        return maxi; 
    }   

}
