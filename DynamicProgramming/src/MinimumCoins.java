import java.util.Arrays;

public class MinimumCoins {

	public static void main(String[] args) {
		int[] arr= {5,2,6,4};
		int d=11;
		System.out.println(minimumElementsSpOp(arr,d));
		
	}
	
	//Recursive Approach
	
	public static int minimumElements(int num[], int x) {
        int n=num.length;
        int ans=f(n-1,x,num);
        if(ans>=1000000000)
        	return -1;
        return ans;
    }
    static int f(int ind, int T, int[] nums){
        if(ind==0){
            if(T%nums[0]==0)return T/nums[0];
            else return 1000000000;
        }
        
        int nottake=0+f(ind-1,T,nums);
        int take=Integer.MAX_VALUE;
        if(nums[ind]<=T){
            take=1+f(ind,T-nums[ind],nums);
        }
        return Math.min(take,nottake);
    }
    
    //Memoization
    
    public static int minimumElementsMemoiz(int num[], int x) {
        int n=num.length;
        int[][] dp=new int[n][x+1];
        
        for(int[] row:dp)
            Arrays.fill(row,-1);
        
        int ans=f(n-1,x,num,dp);
        if(ans>=1000000000)
            return -1;
        return ans;
    }
    static int f(int ind, int T, int[] nums,int[][] dp){
        if(ind==0){
            if(T%nums[0]==0)return T/nums[0];
            else return 1000000000;
        }
        if(dp[ind][T]!=-1)return dp[ind][T];
        
        int nottake=0+f(ind-1,T,nums,dp);
        int take=Integer.MAX_VALUE;
        if(nums[ind]<=T){
            take=1+f(ind,T-nums[ind],nums,dp);
        }
        return dp[ind][T]=Math.min(take,nottake);
}
    //Tabulation
    
    public static int minimumElementsTabulation(int num[], int x) {
        int n=num.length;
        int[][] dp=new int[n][x+1];
        
        for(int[] row:dp)
            Arrays.fill(row,0);
        
        for(int t=0;t<=x;t++){
            if(t%num[0]==0)
                dp[0][t]=t/num[0];
            else 
                dp[0][t]=1000000000;;
        }
        for(int ind=1;ind<n;ind++){
            for(int t=0;t<=x;t++){
                    int nottake=0+dp[ind-1][t];
                    int take=Integer.MAX_VALUE;
                    if(num[ind]<=t){
                        take=1+dp[ind][t-num[ind]];
                    }
                dp[ind][t]=Math.min(take,nottake);
            }
        }
        
        
        int ans=dp[n-1][x];
        if(ans>=1000000000)
            return -1;
        return ans;
    }
    
    //Space Optimisation
    public static int minimumElementsSpOp(int num[], int x) {
        int n=num.length;
        int[] prev=new int[x+1];
        for(int t=0;t<=x;t++){
            if(t%num[0]==0)
                prev[t]=t/num[0];
            else 
                prev[t]=1000000000;;
        }
        for(int ind=1;ind<n;ind++){
             int[] cur=new int[x+1];
            for(int t=0;t<=x;t++){
                    int nottake=0+prev[t];
                    int take=Integer.MAX_VALUE;
                    if(num[ind]<=t){
                        take=1+cur[t-num[ind]];
                    }
                cur[t]=Math.min(take,nottake);
            }
            prev=cur;
        }  
        int ans=prev[x];
        if(ans>=1000000000)
            return -1;
        return ans;
    }
    
    

}
