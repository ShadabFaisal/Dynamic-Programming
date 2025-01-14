
public class LeetCode70ClimbingStairs {

	public static void main(String[] args) {
		int n=3;
		System.out.println(climbStairs(n));
	}
	
	//Tabulation
	 public static int climbStairs(int n) {
	        
	       int[] dp=new int[n+1];
	        
	        for(int i=0;i<=n;i++){
	            if(i==0)
	                dp[i]=1;
	            else if(i==1)
	                dp[i]=dp[i-1];
	            else
	                dp[i]=dp[i-1]+dp[i-2];
	        }
	        return dp[n];
	    }

}
