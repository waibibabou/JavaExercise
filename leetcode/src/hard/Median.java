package hard;

import org.junit.Test;

public class Median {

    @Test
    public void test1(){
        System.out.println(findMedianSortedArrays(new int[]{1,3,5,7,13},new int[]{2,3,4,5,6}));

    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length==0)return nums2.length%2==0?(nums2[nums2.length/2-1]+nums2[nums2.length/2])/2.0:nums2[nums2.length/2];
        if(nums2.length==0)return nums1.length%2==0?(nums1[nums1.length/2-1]+nums1[nums1.length/2])/2.0:nums1[nums1.length/2];
        int left=0,right=0;
        int index1=0,index2=0;
        int l=nums1.length+nums2.length;
        while(index1+index2<l/2-1){
            if(index1==nums1.length){
                index2++;
            }
            else if(index2==nums2.length){
                index1++;
            }
            else if(nums1[index1]<=nums2[index2]){
                index1++;
            }
            else index2++;
        }
        if(index1>=nums1.length){
            left=nums2[index2];
            right=nums2[index2+1];
        }
        else if(index2>=nums2.length){
            left=nums1[index1];
            right=nums1[index1+1];
        }
        else if(nums1[index1]<=nums2[index2]){
            left=nums1[index1];
            if(index1+1<nums1.length)right=Math.min(nums1[index1+1],nums2[index2]);
            else right=nums2[index2];

        }
        else{
            left=nums2[index2];
            if(index2+1<nums2.length)right=Math.min(nums2[index2+1],nums1[index1]);
            else right=nums1[index1];

        }
        if(l%2==0){
            return (left+right)/2.0;
        }
        return right;
    }



    
}
