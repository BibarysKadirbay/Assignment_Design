import java.util.Random;

import static java.util.Collections.sort;

public class QuickSort {
    private static final Random rdm = new Random();
    public static void quickSort(int[] arr) {
        if(arr.length <= 1) return;
        quickSort(arr, 0, arr.length-1);
    }
    private static void quickSort(int [] arr, int l, int r) {
        while(l < r){
            int p = rdm.nextInt(l , r);
            int pivot = arr[r];
            arr[r] = arr[p];
            arr[p] = pivot;
            pivot = arr[r];
            int j = l;
            for(int i = l ; i < r ; i++){
                if(arr[i] <= pivot){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    j++;
                }
            }
            int temp = arr[j];
            arr[j] = arr[r];
            arr[r] = temp;
            if(j - l < r - j){
                quickSort(arr, l, j-1);
                l = j + 1;
            }
            else{
                quickSort(arr, j + 1 , r);
                r = j - 1;
            }
        }
    }
}
