package DpOnStocks;

public class BuyAndSellStock1 {

	public static void main(String[] args) {
		int[] prices= {7,1,5,3,6,4};
		int max=maxProfit(prices);
		System.out.println(max);

	}
	public static int maxProfit(int[] prices) {
		 int maxProfit=0;
	        int minPrice=prices[0];
	        
	        for(int i=1;i<prices.length;i++){
	            int cost=prices[i]-minPrice;
	            maxProfit=Math.max(maxProfit,cost);
	            minPrice=Math.min(minPrice,prices[i]);
	        }
	        return maxProfit;
    }


}
