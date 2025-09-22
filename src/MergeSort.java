public class MergeSort {
    private static final int CUTOFF = 16;
    public static void mergeSort(int[] arr, int l ,  int r){
        int n = r - l + 1;
        if(n <= CUTOFF){
            for (int i = l; i < r; i++) {
                for (int j = i + 1; j <= r; j++){
                    if (arr[i] > arr[j]){
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
            return;
        }
        int mid = (l + r) >> 1;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr , l , r);
    }
    private static void merge(int[] arr , int l , int r){
        int mid = (l + r) >> 1;
        int [] temp = new int[r - l + 1];
        int pos = 0 ;
        int i = l , j = mid + 1;
        while(i <= mid && j <= r){
            if(arr[i] <= arr[j]){
                temp[pos++] = arr[i++];
            }else{
                temp[pos++] = arr[j++];
            }
        }
        while(i <= mid){
            temp[pos++] = arr[i++];
        }
        while(j <= r){
            temp[pos++] = arr[j++];
        }
        for(int k = 0; k < temp.length; k++){
            arr[l + k] = temp[k];
        }
    }
}
