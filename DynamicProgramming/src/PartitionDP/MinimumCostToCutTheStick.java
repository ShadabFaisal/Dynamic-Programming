package PartitionDP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MinimumCostToCutTheStick {

	public static void main(String[] args) {
		int[] arr= {1,3,4,5};
		System.out.println(cost(7,4,arr));
		

	}
	//Recursive Approach
	
	public static int cost(int n, int c, int cuts[]) {
	    ArrayList<Integer> list=new ArrayList<>();
	        for(int i:cuts){
	            list.add(i);
	        }
	        list.add(0,0);
	        list.add(list.size(),n);
	        Collections.sort(list);
	        return f(1,c,list);
	    }
	    static int f(int i, int j, ArrayList<Integer> list){
	        if(i>j)return 0;
	        int mini=Integer.MAX_VALUE;
	        for(int ind=i;ind<=j;ind++){
	            int cost=list.get(j+1)-list.get(i-1)+f(i,ind-1,list)+f(ind+1,j,list);
	            mini=Math.min(mini,cost);
	        }
	        return mini;
	    }
	    //Memoization
	    
	    public static int costMemoiz(int n, int c, int cuts[]) {
	        ArrayList<Integer> list=new ArrayList<>();
	            for(int i:cuts){
	                list.add(i);
	            }
	            list.add(0,0);
	            list.add(list.size(),n);
	            Collections.sort(list);
	            int[][] dp=new int[c+1][c+1];
	            for(int[] row:dp){
	                Arrays.fill(row,-1);
	            }
	            return f(1,c,list,dp);
	        }
	        static int f(int i, int j, ArrayList<Integer> list,int[][] dp){
	            if(i>j)return 0;
	            if(dp[i][j]!=-1)return dp[i][j];
	            int mini=Integer.MAX_VALUE;
	            for(int ind=i;ind<=j;ind++){
	                int cost=list.get(j+1)-list.get(i-1)+f(i,ind-1,list,dp)+f(ind+1,j,list,dp);
	                mini=Math.min(mini,cost);
	            }
	            return dp[i][j]=mini;
	        }
	        
	        //Tabulation
	        
	        public static int costTab(int n, int c, int cuts[]) {
	            ArrayList<Integer> list=new ArrayList<>();
	                for(int i:cuts){
	                    list.add(i);
	                }
	                list.add(0,0);
	                list.add(list.size(),n);
	                Collections.sort(list);
	                int[][] dp=new int[c+2][c+2];
	               
	                for(int i=c;i>=1;i--){
	                    for(int j=1;j<=c;j++){
	                        if(i>j)continue;
	                        int mini=Integer.MAX_VALUE;
	                        for(int ind=i;ind<=j;ind++){
	                            int cost=list.get(j+1)-list.get(i-1)+dp[i][ind-1]+dp[ind+1][j];
	                            mini=Math.min(mini,cost);
	                        }
	                        dp[i][j]=mini;
	                    }
	                }
	                return dp[1][c];
	            }
	        
	        
	    

}
