package medium;

import org.junit.Test;

public class BinarySearch {

    @Test
    public void test1(){
        System.out.println(binarySearch(new int[]{1,5,7,9,11},7));

    }
    public int binarySearch(int[] nums,int target){
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=(left+right)/2;
            if(nums[mid]>target){
                right=mid-1;
            }
            else if(nums[mid]<target){
                left=mid+1;
            }
            else return mid;
        }
        return -1;
    }

    @Test
    public void test2(){
        System.out.println(binarySearch(new int[]{2,2,5,8,8,10,10,10},10));
    }

    /**
     * 找到数组中第一次出现target的下标并返回
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch1(int[] nums,int target){
        int left=0,right=nums.length-1,ans=0;
        while(left<=right){
            int mid=(left+right)/2;
            if(nums[mid]>target){
                right=mid-1;
                ans=mid;
            }
            else if(nums[mid]<target){
                left=mid+1;
            }
        }
        return ans;
    }



    @Test
    public void test3(){
        System.out.println(binary(new int[]{1,3,8,10,10,17,77},10));
    }
    public int binary(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        while(left<=right){
            int mid=(left+right)/2;
            if(arr[mid]>=target)right=mid-1;
            else if(arr[mid]<target)left=mid+1;
        }
        if(left<arr.length&&arr[left]==target)return left;
        return -1;

    }


    @Test
    public void test4(){
        System.out.println(bin(new int[]{2,4,5,6,7,7,9},3));
    }

    /**
     * 寻找最后一个小于target的数的位置
     * @param arr
     * @param target
     * @return
     */
    public int bin(int[] arr,int target){
        int left=0,right=arr.length-1;
        while (left<=right){
            int mid=left+(right-left)/2;
            if(arr[mid]>=target)right=mid-1;
            else if(arr[mid]<target)left=mid+1;
        }
        return right;
    }
}
