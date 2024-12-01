package DpOnStrings;

import java.util.Arrays;

public class EditDistance {

	public static void main(String[] args) {
		String s="horse";
		String t="ros";
		System.out.println(editDistance(s,t));

	}

	// Recursive Approach
	public static int editDistance(String str1, String str2) {
		// Your code goes here
		int n = str1.length();
		int m = str2.length();
		return f(n - 1, m - 1, str1, str2);
	}

	static int f(int i, int j, String s, String t) {
		if (j < 0)
			return i + 1;
		if (i < 0)
			return j + 1;

		if (s.charAt(i) == t.charAt(j)) {
			return f(i - 1, j - 1, s, t);
		}
		return Math.min(1 + f(i, j - 1, s, t), Math.min(1 + f(i - 1, j - 1, s, t), 1 + f(i - 1, j, s, t)));
	}
	// Memoization

	public static int editDistanceMemoiz(String str1, String str2) {
		// Your code goes here
		int n = str1.length();
		int m = str2.length();
		int[][] dp = new int[n][m];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		return f(n - 1, m - 1, str1, str2, dp);
	}

	static int f(int i, int j, String s, String t, int[][] dp) {
		if (j < 0)
			return i + 1;
		if (i < 0)
			return j + 1;
		if (dp[i][j] != -1)
			return dp[i][j];
		if (s.charAt(i) == t.charAt(j)) {
			return dp[i][j] = f(i - 1, j - 1, s, t, dp);
		}
		return dp[i][j] = Math.min(1 + f(i, j - 1, s, t, dp),
				Math.min(1 + f(i - 1, j - 1, s, t, dp), 1 + f(i - 1, j, s, t, dp)));
	}
	
	//Tabulation
	
	public static int editDistanceTabulation(String s, String t) {
        //Your code goes here
        int n=s.length();
        int m=t.length();
        int[][] dp=new int[n+1][m+1];
        for(int i=0;i<=n;i++)dp[i][0]=i;
        for(int j=0;j<=m;j++)dp[0][j]=j;
        
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                 if(s.charAt(i-1)==t.charAt(j-1)){
                     dp[i][j]=dp[i-1][j-1];
                 }
                else{
                     dp[i][j]=1+Math.min(dp[i][j-1],Math.min(dp[i-1][j-1],dp[i-1][j]));
                 }
            }
        }
        return dp[n][m];
    }
	
	//Space Optimisation
	
	 public static int editDistanceSpOp(String s, String t) {
	        //Your code goes here
	        int n=s.length();
	        int m=t.length();
	        int[] prev=new int[m+1];
	        for(int j=0;j<=m;j++)prev[j]=j;
	        
	        
	        for(int i=1;i<=n;i++){
	            int[] cur=new int[m+1];
	            cur[0]=i;
	            for(int j=1;j<=m;j++){
	                 if(s.charAt(i-1)==t.charAt(j-1)){
	                     cur[j]=prev[j-1];
	                 }
	                else{
	                     cur[j]=1+Math.min(prev[j],Math.min(prev[j-1],cur[j-1]));
	                 }
	            }
	            prev=cur;
	        }
	        return prev[m];
	    }

}
