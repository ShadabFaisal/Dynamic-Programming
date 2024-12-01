package PartitionDP;

import java.util.Arrays;

public class EvaluateBoolExpToTrue {

	public static void main(String[] args) {
		String exp="T^F|T&F^T|F";
		System.out.println(evaluateExp(exp));

	}
	
	static int mod=1000000007;
	
	//Recursive Approach
    public static int evaluateExp(String exp) {
    return (int)f(0,exp.length()-1,1,exp.toCharArray());
    }
    public static long f(int i, int j, int isTrue,char[] exp){
        if(i>j)return 0;
        if(i==j){
            if(isTrue==1)
                return exp[i]=='T'?1:0;
            else
                return exp[i]=='F'?1:0;
        }
        long ways=0;
        for(int ind=i+1;ind<=j-1;ind+=2){
            long lt=f(i,ind-1,1,exp);
            long lf=f(i,ind-1,0,exp);
            long rt=f(ind+1,j,1,exp);
            long rf=f(ind+1,j,0,exp);
            if(exp[ind]=='&'){
                if(isTrue==1){
                    ways=(ways+(lt*rt)%mod)%mod;
                }
                else{
                    ways=(ways+(lt*rf)%mod+(lf*rt)%mod+(lf*rf)%mod)%mod;
                }
            }
            else if(exp[ind]=='|'){
                if(isTrue==1){
                    ways=(ways+(lt*rt)%mod+(lt*rf)%mod+(lf*rt)%mod)%mod;
                }
                else{
                    ways=(ways+(lf*rf)%mod)%mod;
                }
            }
            else if(exp[ind]=='^'){
                if(isTrue==1){
                    ways=(ways+(lt*rf)%mod+(lf*rt)%mod)%mod;
                }
                else{
                    ways=(ways+(lt*rt)%mod+(lf*rf)%mod)%mod;
                }
            }
        }
        return ways;
    }
    
    //Memoization
    public static int evaluateExpMemoiz(String exp) {
        int n=exp.length();
        long[][][] dp=new long[n][n][2];
        for(long[][] rows:dp){
            for(long[] row:rows){
                Arrays.fill(row,-1);
            }
        }
    return (int)f(0,exp.length()-1,1,exp.toCharArray(),dp);
    }
    public static long f(int i, int j, int isTrue,char[] exp,long[][][] dp){
        if(i>j)return 0;
        if(dp[i][j][isTrue]!=-1)return dp[i][j][isTrue];
        if(i==j){
            if(isTrue==1)
                return exp[i]=='T'?1:0;
            else
                return exp[i]=='F'?1:0;
        }
        long ways=0;
        for(int ind=i+1;ind<=j-1;ind+=2){
            long lt=f(i,ind-1,1,exp,dp);
            long lf=f(i,ind-1,0,exp,dp);
            long rt=f(ind+1,j,1,exp,dp);
            long rf=f(ind+1,j,0,exp,dp);
            if(exp[ind]=='&'){
                if(isTrue==1){
                    ways=(ways+(lt*rt)%mod)%mod;
                }
                else{
                    ways=(ways+(lt*rf)%mod+(lf*rt)%mod+(lf*rf)%mod)%mod;
                }
            }
            else if(exp[ind]=='|'){
                if(isTrue==1){
                    ways=(ways+(lt*rt)%mod+(lt*rf)%mod+(lf*rt)%mod)%mod;
                }
                else{
                    ways=(ways+(lf*rf)%mod)%mod;
                }
            }
            else if(exp[ind]=='^'){
                if(isTrue==1){
                    ways=(ways+(lt*rf)%mod+(lf*rt)%mod)%mod;
                }
                else{
                    ways=(ways+(lt*rt)%mod+(lf*rf)%mod)%mod;
                }
            }
        }
        return dp[i][j][isTrue]=ways;
    }
    

}
