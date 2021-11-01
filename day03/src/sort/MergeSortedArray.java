package sort;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
        Solution solution=new Solution();
        int[] nums1=new int[]{4,5,6,0,0,0};
        solution.merge(nums1,3,new int[]{1,2,3},3);
        System.out.println(Arrays.toString(nums1));
    }
}
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index2=0;
        while(index2!=n){
            int flag=0;
            for(int i=0;i<m;i++){
                if(nums1[i]>nums2[index2]){
                    flag=1;
                    for(int j=m;j>i;j--){
                        nums1[j]=nums1[j-1];
                    }
                    nums1[i]=nums2[index2];
                    break;
                }
            }
            if(flag==0){
                nums1[m]=nums2[index2];
            }
            m++;
            index2++;
        }

        // int index1=0,index2=0,index3=0;
        // int[] a=new int[m+n];
        // while(index1!=m||index2!=n){
        //     if(index1==m){
        //         a[index3++]=nums2[index2++];
        //         continue;
        //     }
        //     if(index2==n){
        //         a[index3++]=nums1[index1++];
        //         continue;
        //     }
        //     if(nums1[index1]<=nums2[index2]){
        //         a[index3++]=nums1[index1++];

        //     }
        //     else{
        //         a[index3++]=nums2[index2++];
        //     }
        // }
        // //nums1=a;
        // for(int i=0;i<m+n;i++){
        //     nums1[i]=a[i];
        // }


    }
}