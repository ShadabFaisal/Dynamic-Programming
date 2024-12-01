import java.util.Arrays;

public class UnboundedKnapsack {

	public static void main(String[] args) {
		int wt[] = { 1, 2, 4, 5 };
		int val[] = { 5, 4, 8, 6 };
		int W = 5;
		int n = wt.length;
		System.out.println("The Maximum value of items, thief can steal is " + unboundedKnapsack(n,W,val,wt));
	}

	// Recursive Code

	public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
		return f(n - 1, w, profit, weight);
	}

	public static int f(int ind, int W, int[] val, int[] wt) {
		if (ind == 0)
			return ((int) W / wt[0]) * val[0];

		int nottake = 0 + f(ind - 1, W, val, wt);
		int take = Integer.MIN_VALUE;
		if (wt[ind] <= W)
			take = val[ind] + f(ind, W - wt[ind], val, wt);

		return Math.max(take, nottake);
	}
	
	//Memoization
	
	public static int unboundedKnapsackMemoiz(int n, int w, int[] profit, int[] weight)    {    
        int[][] dp=new int[n][w+1];
        for(int[] row: dp){
            Arrays.fill(row,-1);
        }
        return f(n-1,w,profit,weight,dp);
    }
    public static int f(int ind, int W, int[] val, int[] wt,int[][] dp){
        if(ind==0)
            return ((int) W/wt[0])*val[0];
        if(dp[ind][W]!=-1)return dp[ind][W];
        int nottake=0+f(ind-1,W, val, wt,dp);
        int take=Integer.MIN_VALUE;
        if(wt[ind]<=W)
            take=val[ind]+f(ind,W-wt[ind],val,wt,dp);
        
        return dp[ind][W]=Math.max(take, nottake);
    }
    
    //Tabulation
    
    public static int unboundedKnapsackTabulation(int n, int w, int[] profit, int[] weight)    {    
        int[][] dp=new int[n][w+1];
        
        for(int W=0;W<=w;W++){
            dp[0][W]=((int) W/weight[0])*profit[0];
        }
        for(int ind=1;ind<n;ind++){
            for(int W=0;W<=w;W++){
                 int nottake=0+dp[ind-1][W];
                 int take=Integer.MIN_VALUE;
                 if(weight[ind]<=W)
                     take=profit[ind]+dp[ind][W-weight[ind]];

                   dp[ind][W]=Math.max(take, nottake);
            }
        }
        
        return dp[n-1][w];
    }
    
    //Space Optimisation 1
    
    public static int unboundedKnapsackSpOp1(int n, int w, int[] profit, int[] weight)    {    
        int[] prev=new int[w+1];
        
        for(int W=0;W<=w;W++){
            prev[W]=((int) W/weight[0])*profit[0];
        }
        for(int ind=1;ind<n;ind++){
            int[] cur=new int[w+1];
            for(int W=0;W<=w;W++){
                 int nottake=0+prev[W];
                 int take=Integer.MIN_VALUE;
                 if(weight[ind]<=W)
                     take=profit[ind]+cur[W-weight[ind]];

                   cur[W]=Math.max(take, nottake);
            }
            prev=cur;
        }
        
        return prev[w];
    }
    //Space Optimisation 2
    public static int unboundedKnapsackSpOp2(int n, int w, int[] profit, int[] weight)    {    
        int[] prev=new int[w+1];
        
        for(int W=0;W<=w;W++){
            prev[W]=((int) W/weight[0])*profit[0];
        }
        for(int ind=1;ind<n;ind++){
            for(int W=0;W<=w;W++){
                 int nottake=0+prev[W];
                 int take=Integer.MIN_VALUE;
                 if(weight[ind]<=W)
                     take=profit[ind]+prev[W-weight[ind]];

                   prev[W]=Math.max(take, nottake);
            }
        }
        
        return prev[w];
    }
}
