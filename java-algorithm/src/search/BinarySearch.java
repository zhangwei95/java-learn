package search;

public  class BinarySearch {
	public static void main(String[] arg) {
		int[] nums=new int[44444444];
		for(int i=0;i<44444444;i++){
			nums[i]=i;	
		}
		System.out.println(BinarySearch2(nums,44444445,0,44444443));
		
	}
	static int BinarySearch2(int a[], int value, int low, int high){
	    int mid = low+(high-low)/2;
	    if (low<high) {
	    	if(a[mid]==value)
		        return mid;
	    	else if(a[mid]>value)
		        return BinarySearch2(a, value, low, mid-1);
	    	else
		        return BinarySearch2(a, value, mid+1, high);
		}else
		{
			return -1;
		}
	    
	    
	}
}
