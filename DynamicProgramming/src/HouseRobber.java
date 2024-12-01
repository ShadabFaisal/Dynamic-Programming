import java.util.ArrayList;

public class HouseRobber {

	public static void main(String[] args) {
		int[] arr= {1,3,2,1};
		System.out.println(houseRobber(arr));
	}
	public static long maximumNonAdjacentSum(ArrayList<Long> nums) {
        int n = nums.size();
        long prev = nums.get(0);
        long prev2 = 0;
        for (int i = 1; i < n; i++) {
            long take = nums.get(i);
            if (i > 1) {
                take += prev2;
            }
            long nontake = 0 + prev;
           long curi = Math.max(take, nontake);
            prev2 = prev;
            prev = curi;
        }
        return prev;
    }

	public static long houseRobber(int[] valueInHouse) {
       
        ArrayList<Long> temp1=new ArrayList<>();
        ArrayList<Long> temp2=new ArrayList<>();
         int n=valueInHouse.length;
        if(n==1)
            return valueInHouse[0];
        for(int i=0;i<n;i++){
            if(i!=0)
                temp1.add((long)(valueInHouse[i]));
            if(i!=n-1)
                temp2.add((long)(valueInHouse[i]));
        }
        return Math.max(maximumNonAdjacentSum(temp1),maximumNonAdjacentSum(temp2));
    }	

}
