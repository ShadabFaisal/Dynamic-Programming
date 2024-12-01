package DpOnStocks;

public class BuyAndSellStockIV {

	public static void main(String[] args) {
		int[] arr = { 3, 3, 5, 0, 3, 1, 4 };
		System.out.println(maximumProfit(arr,arr.length,3));
	}
	
	public static int maximumProfit(int[] prices, int n, int k)
    {
        // Write your code here.
     int[][] after=new int[2][k+1];
        
        for(int i=0;i<=n;i++){
            for(int buy=0;buy<=1;buy++){
                after[buy][0]=0;
            }
        }
        for(int buy=0;buy<=1;buy++){
            for(int cap=0;cap<=k;cap++){
                after[buy][cap]=0;
            }
        }
        
        
        for(int ind=n-1;ind>=0;ind--){
            int[][] cur=new int[2][k+1];
            for(int buy=0;buy<=1;buy++){
                for(int cap=1;cap<=k;cap++){
                    if(buy==1){
                       cur[buy][cap]=Math.max(-prices[ind]+after[0][cap],after[1][cap]);
                    }
                    else{
                       cur[buy][cap]=Math.max(prices[ind]+after[1][cap-1],after[0][cap]);
                    } 
                }
            }
            after=cur;
        }
        return after[1][k];
    }

}
