package algorithms.sorting;

/**
 * Using Merge sort to compute inversions of an array.
 * 
 * i<j but arr[i] > arr[j] is one inversion.
 * 
 */
public class InversionCount {

    static void print(int[] arr, int l, int r) {
        for(int i=l;i<=r;i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
    /**
     * Returns count of inversions while merging two subarrays
     * @param arr
     * @param l
     * @param mid
     * @param r
     * @return
     */
    private static int merge(int[] arr, int[] temp, int l,int mid, int r) {
        int inversions = 0;
        int i=l,j=mid+1;
        int k=l;
        // System.out.printf("%d,%d,%d\n",l,mid,r);
        while(i<=mid && j<=r) {
            if(arr[i] <= arr[j]) {
                temp[k++] = arr[i];
                i++;
            } else if(arr[i] > arr[j]) {
                // as remaining elements in first subarray will also be greater than ele at j, we will have inversions for all remainining elemnts
                inversions = inversions + (mid-i+1);
                System.out.printf("Inversion (%d,%d): %d\n",arr[i],arr[j], mid-i+1);
                temp[k++] = arr[j];
                j++;
            }
        }
        while(i<=mid) {
            temp[k++] = arr[i++];
        }
        while(j<=r) {
            temp[k++] = arr[j++];
        }
        for(int g=l;g<=r;g++) {
            arr[g] = temp[g];
        }
        return inversions;
    }

    private static int getInversions(int[] arr, int[] temp, int l, int r) {
        if(l==r) {
            return 0;
        }
        if(l<r) {
            int mid = (l+r)/2;
            return getInversions(arr,temp,l,mid) + getInversions(arr,temp,mid+1,r) + merge(arr,temp,l,mid,r);
        }
        return 0;
    }
    public static void main(String[] args) {
        int arr[] = {1,1,1,2,2};
        System.out.println("No of inversions: " + getInversions(arr,new int[arr.length], 0,arr.length-1));
    }
}