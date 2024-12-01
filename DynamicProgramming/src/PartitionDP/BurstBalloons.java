package PartitionDP;

import java.util.ArrayList;
import java.util.Arrays;

public class BurstBalloons {

	public static void main(String[] args) {
		int[] a= {3,4,1,5};
		System.out.println(maxCoins(a));
	}
	
	//Recursive Approach
	
	 public static int maxCoins(int a[]) {
	        ArrayList<Integer> list=new ArrayList<>();
	            for(int i:a){
	                list.add(i);
	            }
	            list.add(0,1);
	            list.add(list.size(),1);
	            return f(1,a.length,list);
	    }
	    static int f(int i, int j, ArrayList<Integer> list){
	            if(i>j)return 0;
	            int maxi=Integer.MIN_VALUE;
	            for(int ind=i;ind<=j;ind++){
	                int coins=list.get(j+1)*list.get(ind)*list.get(i-1)+f(i,ind-1,list)+f(ind+1,j,list);
	                maxi=Math.max(maxi,coins);
	            }
	            return maxi;
	    }
	    
	    //Memoization
	    public static int maxCoinsMemoiz(int a[]) {
	        int n=a.length;
	        ArrayList<Integer> list=new ArrayList<>();
	            for(int i:a){
	                list.add(i);
	            }
	            list.add(0,1);
	            list.add(list.size(),1);
	         int[][] dp=new int[n+1][n+1];
	                for(int[] row:dp){
	                    Arrays.fill(row,-1);
	                }
	            return f(1,a.length,list,dp);
	    }
	    static int f(int i, int j, ArrayList<Integer> list,int[][] dp){
	            if(i>j)return 0;
	            if(dp[i][j]!=-1)return dp[i][j];
	            int maxi=Integer.MIN_VALUE;
	            for(int ind=i;ind<=j;ind++){
	                int coins=list.get(j+1)*list.get(ind)*list.get(i-1)+f(i,ind-1,list,dp)+f(ind+1,j,list,dp);
	                maxi=Math.max(maxi,coins);
	            }
	            return dp[i][j]=maxi;
	    }
	    
	    //Tabulation
	    public static int maxCoinsTab(int a[]) {
	        int n=a.length;
	        ArrayList<Integer> list=new ArrayList<>();
	            for(int i:a){
	                list.add(i);
	            }
	            list.add(0,1);
	            list.add(list.size(),1);
	             int[][] dp=new int[n+2][n+2];
	                   
	                    for(int i=n;i>=1;i--){
	                        for(int j=1;j<=n;j++){
	                            if(i>j)continue;
	                            int maxi=Integer.MIN_VALUE;
	                            for(int ind=i;ind<=j;ind++){
	                                int coins=list.get(j+1)*list.get(ind)*list.get(i-1)+dp[i][ind-1]+dp[ind+1][j];
	                                maxi=Math.max(maxi,coins);
	                            }
	                            dp[i][j]=maxi;
	                        }
	                    }
	                    return dp[1][n];
	    }
	   
	    
	    
	    

}
