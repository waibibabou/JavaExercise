package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] a=new int[]{5,2,1,4,1,1,8};
        QuickSort quickSort=new QuickSort();
        quickSort.QSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));

    }
    public void QSort(int[] a,int low,int high){
        if(low<high){
            int partition=Partition(a,low,high);
            QSort(a,low,partition-1);
            QSort(a,partition+1,high);
        }

    }
    public int Partition(int[] a,int low,int high){
        int temp=a[low];
        while(low<high){
            while(low<high&&a[high]>=temp)high--;
            a[low]=a[high];
            while(low<high&&a[low]<=temp)low++;
            a[high]=a[low];

        }
        a[low]=temp;
        return low;

    }


}
