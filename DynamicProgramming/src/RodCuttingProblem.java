import java.util.Arrays;

public class RodCuttingProblem {

	public static void main(String[] args) {
		

	}
	
	//Recursive Approach
	
	public static int cutRod(int price[], int n) {
        return f(n-1,n,price);
    }
    static int f(int ind,int n,int[] price){
        if(ind==0)
            return n*price[ind];
        
        int nottake=0+f(ind-1,n,price);
        int take=Integer.MIN_VALUE;
        int rodlength=ind+1;
        if(rodlength<=n)
            take=price[ind]+f(ind,n-rodlength,price);
        
        return Math.max(take,nottake);
    }
    
    //Memoization
    
    public static int cutRodMemoiz(int price[], int n) {
        int[][] dp=new int[n][n+1];
        for(int[] row:dp)
            Arrays.fill(row,-1);
        return f(n-1,n,price,dp);
    }
    static int f(int ind,int n,int[] price, int[][] dp){
        if(ind==0)
            return n*price[ind];
        
        if(dp[ind][n]!=-1)
            return dp[ind][n];
        
        int nottake=0+f(ind-1,n,price,dp);
        int take=Integer.MIN_VALUE;
        int rodlength=ind+1;
        if(rodlength<=n)
            take=price[ind]+f(ind,n-rodlength,price,dp);
        
        return dp[ind][n]=Math.max(take,nottake);
    }
    
    //Tabulation
    
    public static int cutRodTabulation(int price[], int n) {
        int[][] dp=new int[n][n+1];
        for(int N=0;N<=n;N++){
            dp[0][N]=N*price[0];
        }
        for(int ind=1;ind<n;ind++){
            for(int N=0;N<=n;N++){
                 int nottake=0+dp[ind-1][N];
                 int take=Integer.MIN_VALUE;
                 int rodlength=ind+1;
                 if(rodlength<=N)
                     take=price[ind]+dp[ind][N-rodlength];
                
                dp[ind][N]=Math.max(take,nottake);

            }
        }
        return dp[n-1][n];
    }
    
    //Space Optimisation1
    public static int cutRodSpOp1(int price[], int n) {
        int[] prev=new int[n+1];
        for(int N=0;N<=n;N++){
            prev[N]=N*price[0];
        }
        for(int ind=1;ind<n;ind++){
            int[] cur=new int[n+1];
            for(int N=0;N<=n;N++){
                 int nottake=0+prev[N];
                 int take=Integer.MIN_VALUE;
                 int rodlength=ind+1;
                 if(rodlength<=N)
                     take=price[ind]+cur[N-rodlength];
                
                cur[N]=Math.max(take,nottake);

            }
            prev=cur;
        }
        return  prev[n];
    }
    
    //Space Optimisation2
    public static int cutRodSpOp2(int price[], int n) {
        int[] prev=new int[n+1];
        for(int N=0;N<=n;N++){
            prev[N]=N*price[0];
        }
        for(int ind=1;ind<n;ind++){
            for(int N=0;N<=n;N++){
                 int nottake=0+prev[N];
                 int take=Integer.MIN_VALUE;
                 int rodlength=ind+1;
                 if(rodlength<=N)
                     take=price[ind]+prev[N-rodlength];
                
                prev[N]=Math.max(take,nottake);

            }
        }
        return  prev[n];
    }

}
