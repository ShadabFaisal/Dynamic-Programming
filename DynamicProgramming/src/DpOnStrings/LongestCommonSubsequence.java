package DpOnStrings;

import java.util.Arrays;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		String s="acd";
		String t="aed";
		System.out.println(lcs(s,t));
	}
	//Recursive Approach
	public static int f(int i, int j, String s, String t){
        if(i<0 || j<0){
            return 0;
        }
        if(s.charAt(i)==t.charAt(j)){
            return 1+f(i-1,j-1,s,t);
        }
        return 0+Math.max(f(i-1,j,s,t),f(i,j-1,s,t));
    }

	public static int lcs(String s, String t) {
        int n=s.length();
        int m=t.length();
        return f(n-1,m-1,s,t);
    }
	//Memoization
	public static int f(int i, int j, String s, String t,int[][] dp){
        if(i<0 || j<0){
            return 0;
        }
        if(dp[i][j]!=-1)
            return dp[i][j];
        if(s.charAt(i)==t.charAt(j)){
            return dp[i][j]=1+f(i-1,j-1,s,t,dp);
        }
        return dp[i][j]=0+Math.max(f(i-1,j,s,t,dp),f(i,j-1,s,t,dp));
    }

	public static int lcsMemoiz(String s, String t) {
        int n=s.length();
        int m=t.length();
        int[][] dp=new int[n][m];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return f(n-1,m-1,s,t,dp);
    }
	//Tabulation
	
	public static int lcsTabulation(String s, String t) {
        int n=s.length();
        int m=t.length();
        int[][] dp=new int[n+1][m+1];
        for(int j=0;j<=m;j++)dp[0][j]=0;
        for(int i=0;i<=n;i++)dp[i][0]=0;
        
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                 if(s.charAt(i-1)==t.charAt(j-1)){
                     dp[i][j]=1+dp[i-1][j-1];
                 }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }
	//Space Optimisation
	public static int lcsSpOp(String s, String t) {
        int n=s.length();
        int m=t.length();
        int[] prev=new int[m+1];
        for(int j=0;j<=m;j++)prev[j]=0;
        
        
        for(int i=1;i<=n;i++){
            int[] cur=new int[m+1];
            for(int j=1;j<=m;j++){
                 if(s.charAt(i-1)==t.charAt(j-1)){
                     cur[j]=1+prev[j-1];
                 }
                else{
                    cur[j]=Math.max(prev[j],cur[j-1]);
                }
            }
            prev=cur;
        }
        return prev[m];
    }

	

}
