package DpOnStrings;

public class PrintLCS {

	public static void main(String[] args) {
		String s="abcde";
		String t="bdgek";
		printLCS(s,t);

	}
	public static void printLCS(String s, String t) {
        int n=s.length();
        int m=t.length();
        int[][] dp=new int[n+1][m+1];
        for(int j=0;j<=m;j++)dp[0][j]=0;
        for(int i=0;i<=n;i++)dp[i][0]=0;
        
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                 if(s.charAt(i-1)==t.charAt(j-1)){
                     dp[i][j]=1+dp[i-1][j-1];
                 }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        int len=dp[n][m];
        int index=len-1;
        String s1=" ";
        char[] ans=new char[len];
        int i=n,j=m;
        while(i>0 && j>0) {
        	if(s.charAt(i-1)==t.charAt(j-1)) {
        		ans[index]=s.charAt(i-1);
        		index--;
        		i--;
        		j--;
        	}
        	else if(dp[i-1][j]>dp[i][j-1]) {
        		i--;
        	}
        	else {
        		j--;
        	}
        }
        
        for(int i1=0;i1<ans.length;i1++) {
        	s1+=ans[i1];
        }
        
        System.out.println(s1);
        }

}
