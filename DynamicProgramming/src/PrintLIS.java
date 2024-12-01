import java.util.ArrayList;
import java.util.Arrays;

public class PrintLIS {

	public static void main(String[] args) {
		int[] arr= {5,4,11,1,16,8};
		printLIS(arr);

	}
	public static void printLIS(int arr[]) {
        int[] dp=new int[arr.length];
        Arrays.fill(dp,1);
        int[] hash=new int[arr.length];
        Arrays.fill(hash,1);
        for(int i=0;i<arr.length;i++){
        	hash[i]=i;
            for(int prev=0;prev<i;prev++){
                if(arr[prev]<arr[i] && 1+dp[prev]>dp[i]){
                    dp[i]=1+dp[prev];
                    hash[i]=prev;
                }
            }
        }
        
        int ans=-1;
        int lastindex=-1;
        for(int i=0;i<arr.length;i++) {
        	if(dp[i]>ans) {
        		ans=dp[i];
        		lastindex=i;
        	}
        }
        
        ArrayList<Integer> temp=new ArrayList<>();
        temp.add(arr[lastindex]);
        
        while(hash[lastindex]!=lastindex) {
        	lastindex=hash[lastindex];
        	temp.add(arr[lastindex]);
        }
        
        for(int i=temp.size()-1;i>=0;i--) {
        	System.out.print(temp.get(i)+" ");
        }
        
    }
}
