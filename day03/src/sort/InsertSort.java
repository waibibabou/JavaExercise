package sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] a=new int[]{5,3,1,4,2};
//        for(int i=1;i<a.length;i++){
//            if(a[i]<a[i-1]){//需要插入
//                int temp=a[i];
//                int j=i-1;
//                while(j>=0&&a[j]>temp){
//                    a[j+1]=a[j];
//                    j--;
//                }
//                a[j+1]=temp;
//            }
//        }

        for(int i=1;i<a.length;i++){
            if(a[i]<a[i-1]){
                int temp=a[i],j=i-1;
                while(j>=0&&a[j]>temp){
                    a[j+1]=a[j];
                    j--;
                }
                a[j+1]=temp;
            }
        }


        System.out.println(Arrays.toString(a));


    }

}
