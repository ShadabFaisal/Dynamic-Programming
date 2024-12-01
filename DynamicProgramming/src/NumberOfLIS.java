import java.util.Arrays;

public class NumberOfLIS {

	public static void main(String[] args) {

		int[] arr= {1,3,5,4,7};
		System.out.println(findNumberOfLIS(arr.length,arr));
	}
public static int findNumberOfLIS(int n, int[] arr) {
		
        int[] dp=new int[n];
        int[] cnt=new int[n];
        Arrays.fill(dp,1);
        Arrays.fill(cnt,1);
        int maxi=1;
        for(int i=0;i<n;i++){
            for(int prev=0;prev<i;prev++){
                if(arr[prev]<arr[i] && 1+dp[prev]>dp[i]){
                    dp[i]=1+dp[prev];
                    cnt[i]=cnt[prev];
                }
                else if(arr[prev]<arr[i] && 1+dp[prev]==dp[i]){
                    cnt[i]+=cnt[prev];
                }
            }
            maxi=Math.max(maxi,dp[i]);
        }
        int c=0;
        for(int i=0;i<n;i++){
            if(dp[i]==maxi){
                c+=cnt[i];
            }
        }
        return c;
	}
	

}
