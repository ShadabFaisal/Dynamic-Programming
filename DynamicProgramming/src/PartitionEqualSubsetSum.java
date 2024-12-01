
public class PartitionEqualSubsetSum {

	public static void main(String[] args) {
		int[] arr= {2,3,3,3,4,5};
		System.out.println(canPartition(arr,arr.length));
	}
	
	//Space Optimisation
	public static boolean canPartition(int[] arr, int n) {

        int totalsum=0;
        for(int i=0;i<n;i++){
            totalsum+=arr[i];
        }
        if(totalsum%2!=0)
            return false;
        int target=totalsum/2;
        return subsetSumToKSpOp(n, target,arr);
    }
    public static boolean subsetSumToKSpOp(int n, int k, int arr[]){
           boolean prev[]= new boolean[k+1];
          if(arr[0]<=k)
              prev[0]=true;

            if(arr[0]<=k)
                prev[arr[0]] = true;

            for(int ind = 1; ind<n; ind++){
                boolean cur[]= new boolean[k+1];
                cur[0]=true;
                for(int target= 1; target<=k; target++){

                    boolean notTaken = prev[target];

                    boolean taken = false;
                        if(arr[ind]<=target)
                            taken = prev[target-arr[ind]];

                    cur[target]= notTaken||taken;
                }
                prev=cur;
            }

            return prev[k];
            }


}
