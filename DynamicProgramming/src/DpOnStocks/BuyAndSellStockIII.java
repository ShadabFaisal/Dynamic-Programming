package DpOnStocks;

import java.util.ArrayList;
import java.util.Arrays;

public class BuyAndSellStockIII {

	public static void main(String[] args) {
		int[] arr = { 3, 3, 5, 0, 3, 1, 4 };
		System.out.println(maxProfit(arr,arr.length));
		
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(3);
		list.add(3);
		list.add(5);
		list.add(0);
		list.add(3);
		list.add(1);
		list.add(4);
		System.out.println(maxProfit(list,list.size()));
	}

	// Recursive Approach
	public static int maxProfit(int[] prices, int n) {
		return f(0, 1, 2, prices, n);
	}

	static int f(int ind, int buy, int cap, int[] prices, int n) {
		if (ind == n)
			return 0;
		if (cap == 0)
			return 0;

		if (buy == 1) {
			return Math.max((-prices[ind] + f(ind + 1, 0, cap, prices, n)), 0 + f(ind + 1, 1, cap, prices, n));
		}
		return Math.max(prices[ind] + f(ind + 1, 1, cap - 1, prices, n), 0 + f(ind + 1, 0, cap, prices, n));
	}

	// Memoization

	public static int maxProfit(ArrayList<Integer> prices, int n) {
        int[][][] dp=new int[n][2][3];
        for(int[][] rows:dp){
            for(int[] row: rows){
                Arrays.fill(row,-1);
            }
        }
        
        return f(0,1,2,prices,n,dp);
    }
    static int f(int ind,int buy, int cap, ArrayList<Integer> prices,int n,int[][][] dp){
        if(ind==n)return 0;
        if(cap==0)return 0;
        if(dp[ind][buy][cap]!=-1)return dp[ind][buy][cap];
        
        if(buy==1){
            return dp[ind][buy][cap]=Math.max(-prices.get(ind)+f(ind+1,0,cap,prices,n,dp),
                           0+f(ind+1,1,cap,prices,n,dp));
        }
        return dp[ind][buy][cap]=Math.max(prices.get(ind)+f(ind+1,1,cap-1,prices,n,dp),0+f(ind+1,0,cap,prices,n,dp));
    }
	// Tabulation
    
    public static int maxProfitTabulation(ArrayList<Integer> prices, int n) {
        int[][][] dp=new int[n+1][2][3];
        
        for(int i=0;i<=n;i++){
            for(int buy=0;buy<=1;buy++){
                dp[i][buy][0]=0;
            }
        }
        for(int buy=0;buy<=1;buy++){
            for(int cap=0;cap<=2;cap++){
                dp[n][buy][cap]=0;
            }
        }
        
        
        for(int ind=n-1;ind>=0;ind--){
            for(int buy=0;buy<=1;buy++){
                for(int cap=1;cap<=2;cap++){
                    if(buy==1){
                        dp[ind][buy][cap]=Math.max(-prices.get(ind)+dp[ind+1][0][cap],dp[ind+1][1][cap]);
                    }
                    else{
                        dp[ind][buy][cap]=Math.max(prices.get(ind)+dp[ind+1][1][cap-1],dp[ind+1][0][cap]);
                    } 
                }
            }
        }
        return dp[0][1][2];
    }
    
    //SpaceOptimisation
    
    public static int maxProfitSpOp(ArrayList<Integer> prices, int n) {
        int[][] after=new int[2][3];
        
        for(int i=0;i<=n;i++){
            for(int buy=0;buy<=1;buy++){
                after[buy][0]=0;
            }
        }
        for(int buy=0;buy<=1;buy++){
            for(int cap=0;cap<=2;cap++){
                after[buy][cap]=0;
            }
        }
        
        
        for(int ind=n-1;ind>=0;ind--){
            int[][] cur=new int[2][3];
            for(int buy=0;buy<=1;buy++){
                for(int cap=1;cap<=2;cap++){
                    if(buy==1){
                       cur[buy][cap]=Math.max(-prices.get(ind)+after[0][cap],after[1][cap]);
                    }
                    else{
                       cur[buy][cap]=Math.max(prices.get(ind)+after[1][cap-1],after[0][cap]);
                    } 
                }
            }
            after=cur;
        }
        return after[1][2];
    }

}
