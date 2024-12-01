package PartitionDP;

import java.util.Arrays;

public class PalindromePartitioningII {

	public static void main(String[] args) {

		String s="aabb";
		System.out.println(palindromePartitioning(s));
	}
	
	//Recursive Approach
	
	public static int palindromePartitioning(String str) {
        int n=str.length();
        return f(0,n,str)-1;
    }
    public static int f(int i, int n, String str){
        if(i==n)return 0;
        int minCost=Integer.MAX_VALUE;
        for(int j=i;j<n;j++){
            if(isPalindrome(i,j,str)){
                int cost=1+f(j+1,n,str);
                minCost=Math.min(minCost,cost);
            }
        }
        return minCost;
    }
    static boolean isPalindrome(int i,int j,String str){
        while(i<j){
            if(str.charAt(i)!=str.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    
    //Memoization
    
    public static int palindromePartitioningMemoiz(String str) {
        int n=str.length();
        int[] dp=new int[n];
        Arrays.fill(dp,-1);
        return f(0,n,str,dp)-1;
    }
    public static int f(int i, int n, String str,int[] dp){
        if(i==n)return 0;
        if(dp[i]!=-1)return dp[i];
        int minCost=Integer.MAX_VALUE;
        for(int j=i;j<n;j++){
            if(isPalindrome(i,j,str)){
                int cost=1+f(j+1,n,str,dp);
                minCost=Math.min(minCost,cost);
            }
        }
        return dp[i]=minCost;
    }
    
    //Tabulation
    public static int palindromePartitioningTab(String str) {
        int n=str.length();
       int[] dp=new int[n+1];
        dp[n]=0;
        
        for(int i=n-1;i>=0;i--){
            int minCost=Integer.MAX_VALUE;
            for(int j=i;j<n;j++){
                if(isPalindrome(i,j,str)){
                    int cost=1+dp[j+1];
                    minCost=Math.min(minCost,cost);
                }
            }
            dp[i]=minCost;
        }
        return dp[0]-1;
    }
    
    

}
