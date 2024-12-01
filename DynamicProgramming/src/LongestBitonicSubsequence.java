import java.util.Arrays;

public class LongestBitonicSubsequence {

	public static void main(String[] args) {
		int[] arr= {1,11,2,10,4,5,2,1};
		System.out.println(longestBitonicSequence(arr,arr.length));

	}
	
	public static int longestBitonicSequence(int[] arr, int n) {
        int[] dp=new int[arr.length];
       Arrays.fill(dp,1);
       for(int i=0;i<arr.length;i++){
           for(int prev=0;prev<i;prev++){
               if(arr[prev]<arr[i] && 1+dp[prev] >dp[i]){
                   dp[i]=1+dp[prev];
               }
           }
       }
       int[] dp2=new int[arr.length];
       Arrays.fill(dp2,1);
       int maxi=1;
       for(int i=n-1;i>=0;i--){
           for(int prev=n-1;prev>i;prev--){
               if(arr[prev]<arr[i] && 1+dp2[prev]>dp2[i]){
                   dp2[i]=1+dp2[prev];
               }
           }
           maxi=Math.max(maxi,dp[i]+dp2[i]-1);
       }
       
       return maxi;
   }

}
