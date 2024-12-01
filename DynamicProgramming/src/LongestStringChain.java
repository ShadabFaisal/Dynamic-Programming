import java.util.Arrays;

public class LongestStringChain {

	public static void main(String[] args) {
		String[] arr= {"a", "bc", "ad", "adc", "bcd"};
		System.out.println(longestStrChain(arr.length,arr));
	}
	static boolean comp(String s1,String s2) {
		return s1.length()<s2.length();
	}
	public static int longestStrChain(int n, String[] arr) {
		Arrays.sort(arr, (a,b)-> a.length()-b.length());
        int[] dp=new int[arr.length];
        Arrays.fill(dp,1);
        int maxi=1;
        for(int i=0;i<arr.length;i++){
            for(int prev=0;prev<i;prev++){
                if(checkPossible(arr[i],arr[prev]) && 1+dp[prev]>dp[i]){
                    dp[i]=1+dp[prev];
                }
            }
            maxi=Math.max(maxi,dp[i]);
        }
        return maxi;
	}
    static boolean checkPossible(String s1, String s2){
        if(s1.length() != s2.length()+1)return false;
        
        int first=0,second=0;
        while(first<s1.length()){
            if(second<s2.length() && s1.charAt(first) ==s2.charAt(second)){
                first++;
                second++;
            }
            else{
                first++;
            }
        }
        if(first==s1.length() && second==s2.length()){
            return true;
        }
        return false;
    }
}
