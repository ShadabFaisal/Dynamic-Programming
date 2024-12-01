import java.util.ArrayList;

public class LISBinarySearch {

	public static void main(String[] args) {
		
		int[] arr= {1,7,8,4,5,6,-1,9};
		System.out.println(lis(arr));
	}
	public static int lis(int arr[]) {
        ArrayList<Integer> temp=new ArrayList<>();
        temp.add(arr[0]);
        int len=1;
        
        for(int i=1;i<arr.length;i++){
            if(arr[i]>temp.get(temp.size()-1)) {
            	temp.add(arr[i]);
            	len++;
            }
            else {
            	int ind=ceilbinaryS(temp,arr[i]);
            	temp.set(ind, arr[i]);
            }
        }
        return len;
    }
	static int ceilbinaryS(ArrayList<Integer> arr, int target) {
		
		int start = 0;
        int end = arr.size() - 1;

        while(start <= end) {
            // find the middle element
//            int mid = (start + end) / 2; // might be possible that (start + end) exceeds the range of int in java
            int mid = start + (end - start) / 2;

            if (target < arr.get(mid)) {
                end = mid - 1;
            } else if (target > arr.get(mid)) {
                start = mid + 1;
            } else {
                // ans found
                return mid;
            }
        }
        return start;
		
	} 

}
