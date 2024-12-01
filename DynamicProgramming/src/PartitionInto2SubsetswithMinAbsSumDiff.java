
public class PartitionInto2SubsetswithMinAbsSumDiff {

	public static void main(String[] args) {
		int[] arr= {3,2,7};
		System.out.println(minSubsetSumDifference(arr,arr.length));

	}
	
	//Tabulation
	
	public static int minSubsetSumDifference(int[] arr, int n) {

        int totsum=0;
        for(int i=0;i<n;i++)totsum+=arr[i];
        int k=totsum;
        
        boolean dp[][] = new boolean[n][k + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (arr[0] <= k)
            dp[0][arr[0]] = true;

        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {

                boolean notTaken = dp[ind - 1][target];

                boolean taken = false;
                if (arr[ind] <= target)
                    taken = dp[ind - 1][target - arr[ind]];

                dp[ind][target] = notTaken || taken;
            }
        }
        int mini=1000000000;
        for(int s1=0;s1<=totsum/2;s1++){
            if(dp[n-1][s1]==true){
                mini=Math.min(mini,Math.abs((totsum-s1)-s1));
            }
        }
        return mini;
	}
	
	//Space Optimisation
	
	public static int minSubsetSumDifferenceSpOp(int[] arr, int n) {

        int totsum=0;
        for(int i=0;i<n;i++)totsum+=arr[i];
        int k=totsum;
        
        boolean prev[]= new boolean[k+1];
         
            prev[0]=true;
        
    if(arr[0]<=k)
        prev[arr[0]] = true;
    
    for(int ind = 1; ind<n; ind++){
        boolean cur[]= new boolean[k+1];
        cur[0]=true;
        for(int target= 1; target<=k; target++){
            
            boolean notTaken = prev[target];
    
            boolean taken = false;
                if(arr[ind]<=target)
                    taken = prev[target-arr[ind]];
        
            cur[target]= notTaken||taken;
        }
        prev=cur;
    }
        int mini=1000000000;
        for(int s1=0;s1<=totsum/2;s1++){
            if(prev[s1]==true){
                mini=Math.min(mini,Math.abs((totsum-s1)-s1));
            }
        }
        return mini;
	}


}
